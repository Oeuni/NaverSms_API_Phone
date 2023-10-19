package com.example.demo.phone.controller;

import com.example.demo.common.entity.Msg;
import com.example.demo.phone.dto.ConfirmCodeDto;
import com.example.demo.phone.dto.PhoneNmDto;
import com.example.demo.phone.dto.SmsResponseDto;
import com.example.demo.phone.dto.PhoneNmChangeDto;
import com.example.demo.phone.service.SmsService;
import com.example.demo.phone.utills.RedisUtill;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/auth")
public class SmsController {

    private final SmsService smsService;

    // 인증번호 전송
    @PostMapping("/number")
    public ResponseEntity<Msg> sendSms(@RequestBody PhoneNmDto phoneNmDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        // 유효성 검사
        boolean result = smsService.checkPhoneNm(phoneNmDto);
        if(result) {
            SmsResponseDto responseDto = smsService.sendSms(phoneNmDto);
            return ResponseEntity.ok().body(new Msg("인증번호 전송에 성공했습니다.", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("휴대폰 번호가 올바르지 않습니다.", false));
        }
    }

    // 인증번호 확인
    @PostMapping("/verify")
    public ResponseEntity<Msg> verifySmsCode(@RequestBody ConfirmCodeDto confirmCodeDto) {
        boolean result = smsService.verifySmsCode(confirmCodeDto);
        return ResponseEntity.ok().body(new Msg(result ? "휴대폰 인증에 성공했습니다." : "인증 번호가 올바르지 않습니다.", result));
        //SmsResponseDto responseDto = smsService.verifySmsCode(confirmCodeDto);
        //return responseDto;
    }

    // 휴대폰 번호 변경 후 저장
    @PostMapping("/save")
    public ResponseEntity<Msg> changePhoneNm(@RequestBody PhoneNmChangeDto phoneNmChangeDto) {

            boolean result = smsService.savePhoneNm(phoneNmChangeDto);   // DB 중복검사 후 Save
            if(result) {
                return ResponseEntity.ok().body(new Msg("휴대폰 번호 저장에 성공했습니다.", true));
            }
            else {
                return ResponseEntity.ok().body(new Msg("이미 존재하는 번호입니다.", false));
            }
    }
}