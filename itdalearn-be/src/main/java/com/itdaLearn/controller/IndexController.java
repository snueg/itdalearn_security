package com.itdaLearn.config.controller;

import com.itdaLearn.config.model.UserModel;
import com.itdaLearn.config.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // View를 리턴하겠다
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index() {
        return "index"; //src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    // 스프링 시큐리티 해당주소를 낚아채버림 - SecurityConfig 파일 생성 후 작동안함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join") // joinForm에서 값을 post값을 받음
    public String join(UserModel userModel) {
        System.out.println(userModel);
        userModel.setRole("ROLE_USER");
        String rawPassword = userModel.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userModel.setPassword(encPassword);
        userRepository.save(userModel); // 회원가입이 잘되지만 이 한 줄로 이렇게 하면 안됨 비밀번호: 1234 => 시큐리티로
        // 로그인을 할 수 없음 이유는 패스워드가 암호화가 안되었기 때문!
        return "redirect:/loginForm"; // /loginForm 함수 호출
    }






    @GetMapping("/signin")
    public String memberSignIn(@RequestParam("inputId") String inputId, @RequestParam("inputPw") String inputPw) {
        System.out.println(inputId);
        System.out.println(inputPw);

        return "" + inputId + inputPw;
    }


}
