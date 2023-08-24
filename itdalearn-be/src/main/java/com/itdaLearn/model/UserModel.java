package com.itdaLearn.config.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

// ORM - Object Relation Mapping

@Data
@Entity
public class UserModel {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    //h2 DB에서는 잘 작동하던 IDENTITY가 오라클로 넘어가니 알수없는 오류들을 뿜어내며 테이블이 생성되지 못하고있었는데
    // 이를 해결하기 위해 제너레이션 타입을 바꿔주었더니 해결되었다.
    private int id;
    private String username;
    private String password;
    private String email;
    private String role; //ROLE_USER, ROLE_ADMIN
    @CreationTimestamp
    private Timestamp createDate;
}

// 여태까지 안되었던 이유 cos와 security라는 사용자를 만들어주엇지만 테이블을 만들 수 있는 권한을 주지 않았고 클래스 이름이 User였기 때문이고
// 또한 GeneratedValue의 값을 IDENTITY로 해놓으니 오라클에서 버그를 일으킴