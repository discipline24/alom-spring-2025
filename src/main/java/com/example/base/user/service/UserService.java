package com.example.base.user.service;

import com.example.base.user.domain.User;
import com.example.base.user.dto.LoginDto;
import com.example.base.user.dto.SignupDto;
import com.example.base.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User joinUser(SignupDto signupDto) {
        // todo : Builder 패턴 리팩터링
        User joinUser = new User();
        joinUser.setEmail(signupDto.email());
        joinUser.setPassword(signupDto.password());
        joinUser.setNickname(signupDto.nickname());

        return userRepository.save(joinUser);
    }

    public User loginUser(LoginDto loginDto) {

        String loginEmail = loginDto.email();
        Optional<User> optionalUser = userRepository.findByEmail(loginEmail);

        // todo : GlobalException 리팩터링
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("데이터베이스에 해당 이메일이 없습니다.");
        }
        User user = optionalUser.get();

        // 비밀번호 검증 로직
        String loginPassword = loginDto.password();
        if (!loginPassword.equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 사용자 객체 반환
        return user;
    }
}