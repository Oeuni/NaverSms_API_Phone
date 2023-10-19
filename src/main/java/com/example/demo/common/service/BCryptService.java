package com.example.demo.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.pip.picks.common.service
 * fileName       : BCryptService
 * author         : TaeJeongPark
 * date           : 2023-09-20
 * description    : BCrypt 암호화
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-20        TaeJeongPark       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BCryptService {

    // BCrypt 암호화
//    public static String encodeBcrypt(String planeText, int strength) {
//
//        return new BCryptPasswordEncoder(strength).encode(planeText);
//
//    }
//
//    // BCrypt 암호화 비교
//    public static boolean matchesBcrypt(String planeText, String hashValue, int strength) {
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
//
//        return passwordEncoder.matches(planeText, hashValue);
//
//    }

}
