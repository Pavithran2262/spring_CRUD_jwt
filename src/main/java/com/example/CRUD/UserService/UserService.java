package com.example.CRUD.UserService;

import com.example.CRUD.ApiResponse.ApiResponse;
import com.example.CRUD.User.UserEntity;
import com.example.CRUD.UserDto.UserDto;
import com.example.CRUD.UserRepo.UserRepository;
import com.example.CRUD.jwt_tokens.TokenGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private TokenGeneration tokenGeneration;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApiResponse apiResponse;

    public ApiResponse add(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        try {
            userEntity.setEmail(userDto.getEmail());
            userEntity.setName(userDto.getName());
            userEntity.setPassword(userDto.getPassword());
            userRepository.save(userEntity);
            apiResponse.setMessage("Signup Successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(userEntity);
        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
        return apiResponse;
    }
//    public ResponseEntity<ApiResponse> addd(UserDto userDto) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail(userDto.getEmail());
//        userEntity.setName(userDto.getName());
//        userRepository.save(userEntity);
//        apiResponse.setMessage("Signup Successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(userEntity);
//        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
//    }

    public ApiResponse getAll() {
        List<UserEntity> userEntity = userRepository.findAll();
        apiResponse.setMessage("Get detail Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userEntity);
        return apiResponse;
    }


    public ApiResponse getById(int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        apiResponse.setMessage("Get by id Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userEntity);
        return apiResponse;
    }

    public ApiResponse updateName(UserDto userDto) {
        UserEntity userEntity = userRepository.updateBy(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userRepository.save(userEntity);
        apiResponse.setMessage("Name and Email Updated Sucessfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userEntity);
        return apiResponse;
    }

    public ApiResponse updateEmail(UserDto userDto) {
        UserEntity userEntity = userRepository.findByName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userRepository.save(userEntity);
        apiResponse.setMessage("Email Updated Sucessfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userEntity);
        return apiResponse;
    }

    public ApiResponse deleteUser(int id) {
        userRepository.deleteById(id);
        apiResponse.setMessage("User Deleted Sucessfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData("Deleted id = " + id);
        return apiResponse;
    }


    public ApiResponse login(UserDto userDto) {
        UserEntity userEntity = userRepository.findByNameAndPassword(userDto.getName(), userDto.getPassword());

        if (userEntity == null) {
            apiResponse.setMessage("Incorrect Username or password");
            apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            apiResponse.setData(null);
            return apiResponse;
        } else {
            String token = tokenGeneration.generationMethod(userDto);
            Map<String, Object> tokenMap = new HashMap<>();
            tokenMap.put("token ", token);

            apiResponse.setMessage("Login Successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(tokenMap);
            return apiResponse;
        }
    }
}
