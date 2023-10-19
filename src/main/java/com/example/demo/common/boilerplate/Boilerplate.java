package com.example.demo.common.boilerplate;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * packageName    : com.pip.picks.common.boilerplate
 * fileName       : Boilerplate
 * author         : TaeJeongPark
 * date           : 2023/08/20
 * description    : 보일러플레이트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/20        TaeJeongPark       최초 생성
 */
public class Boilerplate {

    /*
     * 포맷 변경 메서드
     */

    // 천단위 구분 기호 생성
    public static String setComma(int num) {

        DecimalFormat df = new DecimalFormat("###,###");
        String money = df.format(num);

        return money;

    }

    // 휴대폰 번호 하이픈 생성
    public static String setHyphen(String str) {

        String result;
        result = str.substring(0, 3) + "-" + str.substring(3, 7) + "-" + str.substring(7);

        if(phoneNumHyphenValidation(result)) {
            return result;
        } else {
            return null;
        }

    }


    /*
     * 유효성 검사 메서드
     */

    // 이름 유효성 검사 : 한글 2자 이상 20자 이하
    public static boolean nameValidation(String str) {

        return Pattern.matches("^[가-하]{2,20}$", str);

    }

    // 휴대폰번호 유효성 검사(하이픈 없음) : 숫자 10자 이상 11자 이하
    public static boolean phoneNumValidation(String str) {

        return Pattern.matches("^[0-9]{10,11}$", str);

    }

    // 휴대폰번호 유효성 검사(하이픈 있음)
    public static boolean phoneNumHyphenValidation(String str) {

        return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$\t", str);

    }

    // 간편 비밀번호 유효성 검사 : 숫자 6자
    public static boolean pwValidation(String str) {

        return Pattern.matches("^[0-9]{6}$", str);

    }

    // 운송장 번호 유효성 검사 : 숫자, 특수문자(!@#$%^&+=,.-) 조합 1자 이상 30자 이하
    public static boolean shippingNumValidation(String str) {

        return Pattern.matches("^[0-9!@#$%^&+=,.-]{1,30}$", str);

    }

    // 도로명주소, 지번주소, 상세주소 유효성 검사 : 한글, 영문, 숫자, 특수문자(!@#$%^&+=,.-) 조합 1자 이상 40자 이하
    public static boolean addressValidation(String str) {

        return Pattern.matches("^[가-힣a-zA-Z0-9!@#$%^&+=,.-]{1,40}$", str);

    }

    // 수신자명 유효성 검사 : 한글, 영문, 숫자, 특수문자(!@#$%^&+=,.-) 조합 1자 이상 20자 이하
    public static boolean shippingNameValidation(String str) {

        return Pattern.matches("^[가-힣a-zA-Z0-9!@#$%^&+=,.-]{1,20}$", str);

    }

    // 배송메모 유효성 검사 : 한글, 영문, 숫자, 특수문자(!@#$%^&+=,.-) 조합 1자 이상 50자 이하
    public static boolean shippingMemoValidation(String str) {

        return Pattern.matches("^[가-힣a-zA-Z0-9!@#$%^&+=,.-]{1,50}$", str);

    }


    /*
     * 기능 메서드
     */

    // 숫자 6자리 인증번호 생성
    public static int certificationNum() {

        return ThreadLocalRandom.current().nextInt(100000, 1000000);

    }

    // 알파벳 + 숫자 조합 랜덤 문자열(추천인 코드) 생성
    public static String randomString() {

        String str = "";
        int num = 0;

        for (int i = 0; i < 6; i++) {

            num = (int) (Math.random() * 2);

            if (num == 0) {
                str += (char) ((Math.random() * 26) + 97);
            } else {
                str += (int) (Math.random() * 10);
            }

        }

        return str;

    }

}
