package com.example.demo.phone.repository;

import com.example.demo.phone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * packageName    : com.pip.picks.user.repository
 * fileName       : UsersRepository
 * author         : TaeJeongPark
 * date           : 2023-09-04
 * description    : 사용자 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        TaeJeongPark       최초 생성
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByPhoneNum(String phone);

}
