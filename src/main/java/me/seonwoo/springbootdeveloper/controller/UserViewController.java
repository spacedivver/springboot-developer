package me.seonwoo.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 로그인, 회원 가입 경로 접근 시 뷰 파일을 연결하는 컨트롤러
@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 반환
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html 반환
    }

}