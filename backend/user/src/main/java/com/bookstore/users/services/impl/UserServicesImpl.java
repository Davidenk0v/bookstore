package com.bookstore.users.services.impl;

import com.bookstore.users.models.dtos.UserDto;
import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserDtoResponse;
import com.bookstore.users.models.http.response.UserReponse;
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

    @Autowired
    private KafkaService kafkaService;

    @Override
    public UserReponse saveUser(User user) {
        try {
            userRepository.save(user);
            UserReponse userReponseDto = new UserReponse();
            userReponseDto.setMessage("User created");
            userReponseDto.setStatus(HttpStatus.CREATED);
            userReponseDto.setData(List.of(userMapper.entityToResponseDto(user)));
            kafkaService.sendMessage("User created with id: " + user.getId());
            return userReponseDto;
        }catch (Exception e){
            UserReponse userReponseDto = new UserReponse();
            userReponseDto.setMessage("Error creating user");
            userReponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            kafkaService.sendMessage("Error creating user with id: " + user.getId());
            return userReponseDto;
        }
    }

    @Override
    public ResponseEntity<UserReponse> getUser(Long id) {
        UserReponse userReponseDto = new UserReponse();
        Optional<User> optinalUser = userRepository.findById(id);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDtoResponse userDto = userMapper.entityToResponseDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return ResponseEntity.ok(userReponseDto);
        }
        userReponseDto.setMessage("User not found");
        userReponseDto.setStatus(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<UserReponse> getUserByEmail(String email) {
        UserReponse userReponseDto = new UserReponse();
        Optional<User> optinalUser = userRepository.findByEmail(email);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDtoResponse userDto = userMapper.entityToResponseDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return ResponseEntity.ok(userReponseDto);
        }
        return new ResponseEntity<>(userReponseDto, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<UserReponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        UserReponse userReponseDto = new UserReponse();
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


    //METODOS PARA COMUNICACIÓN CON LOS SERVICIOS
    @Override
    public UserReponse findUserById(Long id) {
        UserReponse userReponseDto = new UserReponse();
        Optional<User> optinalUser = userRepository.findById(id);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDto userDto = userMapper.entityToDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return userReponseDto;
        }
        userReponseDto.setMessage("User not found");
        userReponseDto.setStatus(HttpStatus.NOT_FOUND);
        return userReponseDto;
    }

    @Override
    public UserReponse findUserByEmail(String email) {
        UserReponse userReponseDto = new UserReponse();
        Optional<User> optinalUser = userRepository.findByEmail(email);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDto userDto = userMapper.entityToDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return userReponseDto;
        }
        return userReponseDto;
    }

    @Override
    public UserReponse findUserByUsername(String username) {
        UserReponse userReponseDto = new UserReponse();
        Optional<User> optinalUser = userRepository.findByUsername(username);
        if(optinalUser.isPresent()){
            userReponseDto.setMessage("User found");
            userReponseDto.setStatus(HttpStatus.OK);
            UserDto userDto = userMapper.entityToDto(optinalUser.get());
            userReponseDto.setData(List.of(userDto));
            return userReponseDto;
        }
        return userReponseDto;
    }

    @Override
    public UserReponse findAllUsers() {
        List<User> users = userRepository.findAll();
        UserReponse userReponseDto = new UserReponse();
        if(users.isEmpty()){
            userReponseDto.setMessage("No users found");
            userReponseDto.setStatus(HttpStatus.NOT_FOUND);
            return userReponseDto;
        }
        userReponseDto.setMessage("Users found");
        userReponseDto.setStatus(HttpStatus.OK);
        userReponseDto.setData(userMapper.entityListToResponseDtoList(users));
        return userReponseDto;
    }

}
