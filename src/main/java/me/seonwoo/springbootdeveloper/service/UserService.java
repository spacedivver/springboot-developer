package me.seonwoo.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.seonwoo.springbootdeveloper.domain.User;
import me.seonwoo.springbootdeveloper.dto.AddUserRequest;
import me.seonwoo.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// AddUserRequest 객체를 인수로 받는 회원 정보 추가 메서드
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 암호화,
                // 패스워드 저장 시 시큐리티 설정하여 패스워드 인코딩용으로 등록한 빈을 사용해 암호화한 후 저장
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}