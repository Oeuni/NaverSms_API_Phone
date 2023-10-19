package com.example.demo.phone.service;

import com.example.demo.common.boilerplate.Boilerplate;
import com.example.demo.phone.dto.*;
import com.example.demo.phone.entity.Users;
import com.example.demo.phone.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class SmsService {

    private final UsersRepository usersRepository;

    LocalDateTime requestTime = LocalDateTime.now();    // 요청 시간
    LocalDateTime expirationTime = requestTime.plusMinutes(3);  // 3분 후 시간
    String smsConfirmNum;

    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    // API 요청에 대한 서명 생성
    public String getSignature(String time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(time)
                .append(newLine)
                .append(accessKey)
                .toString();

        // HMAC 바이트 배열을 생성하기 위해 HMAC-SHA256을 사용하여 비밀 키로 인코딩
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        // 원시 HMAC는 Base64로 인코딩되어 문자열로 반환
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }



    // SMS 메시지 전송
    public SmsResponseDto sendSms(PhoneNmDto phoneNmDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String time = Long.toString(System.currentTimeMillis());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time);
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", getSignature(time)); // signature 서명

        List<PhoneNmDto> phoneNm = new ArrayList<>();
        phoneNm.add(phoneNmDto);

        smsConfirmNum = createSmsKey();  // 인증번호 생성

        SmsRequestDto request = SmsRequestDto.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phone)
                .content("[ㅇㅇㅇ의 IntelliJ] 인증번호 [" + smsConfirmNum + "]를 입력해주세요")
                .messages(phoneNm)
                .build();

        // Set smsConfirmNum in SmsRequestDto
        //쌓은 바디를 json형태로 반환
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        // jsonBody와 헤더 조립
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        //restTemplate로 post 요청 보내고 오류가 없으면 202코드 반환
        SmsResponseDto smsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDto.class);
        //SmsResponseDto responseDto = new SmsResponseDto(smsConfirmNum);
        //assert smsResponseDto != null;
        smsResponseDto.setSmsConfirmNum(smsConfirmNum);



        return smsResponseDto;
    }



    // 인증코드 만들기
    public static String createSmsKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        SmsResponseDto response = new SmsResponseDto();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }



    // 유효시간 3분, 인증번호 일치 여부 검증
    public boolean verifySmsCode(ConfirmCodeDto userCode) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("요청 시간 : " + requestTime);
        System.out.println("3분 뒤 시간 : " + expirationTime);
        System.out.println("현재시간 : " + currentTime);
        System.out.println(currentTime.isBefore(expirationTime));
        System.out.println(currentTime.isAfter(expirationTime));

        if (currentTime.isBefore(expirationTime) && smsConfirmNum.equals(userCode.getAuthenticationCode())) {
            return true;
        } else {
            return false;
        }
    }






    // 휴대폰 번호 유효성 검사
    public boolean checkPhoneNm(PhoneNmDto phoneNmDto) {

        String phoneToSave = phoneNmDto.getTo();

        if (StringUtils.hasText(phoneToSave) && Boilerplate.phoneNumValidation(phoneToSave)) {
            log.info("휴대폰번호 " + phoneToSave + " 유효성 검사에 통과했습니다.");
            return true;
        } else {
            log.info("휴대폰번호 " + phoneToSave + " 유효성 검사에 통과하지 못했습니다.");
            return false;
        }
    }


    // 휴대폰 번호 저장
    public boolean savePhoneNm(PhoneNmChangeDto phoneNmChangeDto) {
        String userId = phoneNmChangeDto.getUserId();
        Long userIdAsLong = Long.parseLong(userId);

        //사용자가 존재하지 않을 수 있는 경우 처리(Optional)
        Optional<Users> userOptional = usersRepository.findById(userIdAsLong);

        //테이블 내에서 휴대폰 번호 검색
        String phoneNumber = phoneNmChangeDto.getTo();
        Users phone = usersRepository.findByPhoneNum(phoneNumber);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (phone != null) {    // 휴대폰 번호가 검색 됐다면
                return false;       // 누군가 사용중, 휴대폰 번호 변경 불가능
            } else {
                user.setPhoneNum(phoneNumber);
                usersRepository.save(user);

                log.info("==========>> 휴대폰번호 " + phoneNumber + "저장 성공");
                return true;
            }
        }
        else {
            return false;
        }

    }
}



