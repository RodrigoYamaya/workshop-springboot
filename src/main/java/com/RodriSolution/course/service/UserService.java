package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.mapper.UserMapper;
import com.RodriSolution.course.model.dtos.UserRequestDto;
import com.RodriSolution.course.model.dtos.UserResponseDto;
import com.RodriSolution.course.model.entities.User;
import com.RodriSolution.course.repositories.ProductRepository;
import com.RodriSolution.course.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private ProductRepository productRepository;


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
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userRequestDto)));
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
        user.setPassword(userDto.password());
        user.setPhone(userDto.phone());
        User userUpdate = userRepository.save(user);
        return userMapper.toDto(userUpdate);
    }

}
