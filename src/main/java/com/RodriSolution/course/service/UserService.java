package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.mapper.UserMapper;
import com.RodriSolution.course.model.dtos.UserRequestDto;
import com.RodriSolution.course.model.dtos.UserResponseDto;
import com.RodriSolution.course.model.entities.User;
import com.RodriSolution.course.model.enums.UserRole;
import com.RodriSolution.course.repositories.ProductRepository;
import com.RodriSolution.course.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDto findById(Long id) {
       User user = userRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("user com ID " + id + " não encontrado."));
        return userMapper.toDto(user);
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public void deletarUser(long id) {
        if(!userRepository.existsById(id)) {
            throw new RecursoNaoEncontrado("user com o ID " + id + " não encontrado");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto update(UserRequestDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("user com o ID " + id + " não encontrado"));

        user.setName(userDto.name());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());

        if (userDto.password() != null && !userDto.password().isBlank()) {
            String encryptedPassword = passwordEncoder.encode(userDto.password());
            user.setPassword(encryptedPassword);
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

}


