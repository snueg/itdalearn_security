package com.itdaLearn.config.auth;

import com.itdaLearn.config.model.UserModel;
import com.itdaLearn.config.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    // 중오 loginForm에 input부분에서 name이 username2라면 동작은 하지만 값이 매칭이 안됨
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userEntity = userRepository.findByUsername(username);
        if (userEntity != null) { // null이 아니면 회원이라는 뜻이기 때문에
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}

// 처음 페이지에서 user로 가려햇을 때 login을 하도록하는데 거기서 회원가입을 하고 로그인을 하면 내가 원했던 페이지 user페이지로 보내준다