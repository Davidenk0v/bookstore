package com.bookstore.users.services.impl;

import com.bookstore.users.models.dtos.UserDto;
import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserReponseDto;
import com.bookstore.users.repositories.UserRepository;
import com.bookstore.users.services.UserServices;
import com.bookstore.users.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<UserReponseDto> getUser(Long id) {
        UserReponseDto userReponseDto = new UserReponseDto();
        Optional<User> optinalUser = userRepository.findById(id);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDto userDto = userMapper.entityToDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return ResponseEntity.ok(userReponseDto);
        }
        return new ResponseEntity<>(userReponseDto, HttpStatus.NOT_FOUND);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserReponseDto> getUserByEmail(String email) {
        UserReponseDto userReponseDto = new UserReponseDto();
        Optional<User> optinalUser = userRepository.findByEmail(email);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDto userDto = userMapper.entityToDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return ResponseEntity.ok(userReponseDto);
        }
        return new ResponseEntity<>(userReponseDto, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<UserReponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        UserReponseDto userReponseDto = new UserReponseDto();
        if(users.isEmpty()){
            userReponseDto.setMessage("No users found");
            userReponseDto.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(userReponseDto, HttpStatus.NOT_FOUND);
        }
        userReponseDto.setMessage("Users found");
        userReponseDto.setStatus(HttpStatus.OK);
        userReponseDto.setData(userMapper.entityListToResponseDtoList(users));
        return ResponseEntity.ok(userReponseDto);
    }

}
