package me.seonwoo.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.seonwoo.springbootdeveloper.dto.AddUserRequest;
import me.seonwoo.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    // 회원 가입 요청을 받으면 서비스 메서드를 이용해 사용자를 지정한 뒤, 로그인 페이지로 이동하는 signup() 작성
    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request); // 회원 가입 메서드 호출
        // 회원 가입이 완료된 이후에 로그인 페이지로 이동
        // redirect: 접두사를 붙여 회원 가입 처리가 끝나면 강제로 /login URL에 해당하는 화면으로 이동
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}