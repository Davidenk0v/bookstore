package com.bookstore.gateway.services;

import com.bookstore.gateway.clients.UserClient;
import com.bookstore.gateway.dtos.UserDto;
import com.bookstore.gateway.dtos.http.AuthRequest;
import com.bookstore.gateway.dtos.http.AuthResponse;
import com.bookstore.gateway.dtos.http.UserRequest;
import com.bookstore.gateway.jwt.JWTUtilityServiceImpl;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JWTUtilityServiceImpl jwtUtilityService;



//    public ResponseEntity<?> login(AuthRequest login) throws Exception {
//
//        Optional<UserEntity> userOptional = userRepository.findByEmail(login.getEmail());
//        //Primero comprobamos si el user existe en la base de datos
//        if (userOptional.isEmpty()) {
//            return new ResponseEntity<>("Usuario no registrado", HttpStatus.NOT_FOUND);
//        }
//
//        //Si existe comprobamos si la contraseña introducida es correcta
//        if (verifyPassword(login.getPassword(), userOptional.get().getPassword())) {
//            String token = jwtUtilityService.generateToken(userOptional.get().getIdUser());
//            String refreshToken = jwtUtilityService.generateRefreshToken(userOptional.get().getIdUser());
//
//            return new ResponseEntity<>(new AuthResponse(token, refreshToken), HttpStatus.OK);
//
//        }
//        return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
//    }


//    public ResponseEntity<?> refresh(RefreshRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException {
//        if(jwtUtilityService.validateToke(request.getRefreshToken())){
//            String token = jwtUtilityService.generateToken(jwtUtilityService.getUserIdFromToken(request.getRefreshToken()));
//            return new ResponseEntity<>(new AuthResponse(token, request.getRefreshToken()), HttpStatus.OK);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
//    }


    public ResponseEntity<?> register(UserDto userDTO) throws Exception {

        UserRequest request = userClient.getAllUsers();

        List<UserDto> allUsers = request.getData();
        if(allUsers != null) {
            for (UserDto repetUser : allUsers) {
                if (Objects.equals(repetUser.getUsername(), userDTO.getUsername())) {
                    return new ResponseEntity<>("Ya existe un usuario con ese Username", HttpStatus.BAD_REQUEST);
                }

                if (userClient.getUserByEmail(userDTO.getEmail()).getStatus() == HttpStatus.OK) {
                    return new ResponseEntity<>("Ya existe un usuario con ese email", HttpStatus.BAD_REQUEST);
                }
            }
        for (UserDto repetUser : allUsers) {
            if (Objects.equals(repetUser.getUsername(), userDTO.getUsername())) {
                return new ResponseEntity<>("Ya existe un usuario con ese Username", HttpStatus.BAD_REQUEST);
            }

            if (userClient.getUserByEmail(userDTO.getEmail()).getStatus() == HttpStatus.OK) {
                return new ResponseEntity<>("Ya existe un usuario con ese email", HttpStatus.BAD_REQUEST);
            }
        }
        }
        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));

            userClient.saveUser(userDTO);
            UserRequest userOptional = userClient.getUserByEmail(userDTO.getEmail());
            UserDto user = userOptional.getData().get(0);
            String jwt = jwtUtilityService.generateToken(user.getId());
            Map<String, String> token = new HashMap<>();
            token.put("token", jwt);

            return new ResponseEntity<>(token, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    //Metodo para verificar si las contraseñas son iguales
    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
