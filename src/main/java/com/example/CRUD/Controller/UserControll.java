package com.example.CRUD.Controller;

import com.example.CRUD.ApiResponse.ApiResponse;
import com.example.CRUD.UserDto.UserDto;
import com.example.CRUD.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserControll {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/add")
    public ApiResponse userAdd(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }
    @PostMapping(value = "/login")
    public ApiResponse userLogin(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }
    @GetMapping(value = "/getall")
    public ApiResponse userGet(){
        return userService.getAll();
    }
    @GetMapping(value = "/getbyid/{id}")
    public ApiResponse userGetById(@PathVariable("id") int id){
        return userService.getById(id);
    }
    @PutMapping(value = "/updatebyid")
    public ApiResponse userUpdate(@RequestBody UserDto userDto){
        return userService.updateName(userDto);
    }
    @PatchMapping(value = "/patch")
    public ApiResponse userPatch(@RequestBody UserDto userDto){
        return userService.updateEmail(userDto);
    }
    @DeleteMapping(value = "/delete")
    public ApiResponse userDelete(@PathVariable int id){
        return userService.deleteUser(id);
    }

}
