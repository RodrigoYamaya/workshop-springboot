package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.UserRequestDto;
import com.RodriSolution.course.model.dtos.UserResponseDto;
import com.RodriSolution.course.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/lista")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(value = "id") long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);

    }

    @Transactional
    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserRequestDto userDto) {
        UserResponseDto userSave = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }

    @Transactional
    @DeleteMapping("user/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        userService.deletarUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user com o ID " + id + " deletado com sucesso.");
    }

    @Transactional
    @PutMapping("user/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid UserRequestDto userDto) {
        UserResponseDto userUpdate = userService.update(userDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }
}

