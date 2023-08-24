package com.itdaLearn.config.repository;

import com.itdaLearn.config.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository가 들고 있음.
// @Repository라는 어노테이션이 없어도 IoC되요 이유는 JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    // findBy 규창 -> Username 문법
    // select * from user where username = 1? // 이게 호춣 (?엔 username이 파라미터 값으로)
    public UserModel findByUsername(String username); // 이게 더 궁금하면 Jpa Query methods 함수

//    public UserModel findByEmail(); // 이렇게 하면
    // select * from user where email = ? 이렇게 실행됨
}
