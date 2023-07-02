package com.SeatBookingSystem.controller;


import com.SeatBookingSystem.DTO.SeatResponse;
import com.SeatBookingSystem.entity.Seat;
import com.SeatBookingSystem.entity.User;
import com.SeatBookingSystem.exception.UserNameAlreadyExistsException;
import com.SeatBookingSystem.repository.UserRepository;
import com.SeatBookingSystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceimpl;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws UserNameAlreadyExistsException {
        User user1 =userServiceimpl.addUser(user);
        log.info("Added a new User ");
        log.info(String.valueOf(user1));
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public void modifyUser(@RequestParam(value="userId")Long id1, @RequestBody User user)
    {
        userServiceimpl.updateUser(id1,user);
    }

    @DeleteMapping("/delete/{userId}")
    public void removeUser(@PathVariable Long userId){
        userServiceimpl.deleteUser(userId);
    }


    @GetMapping("/viewall")
    public List<User> viewAllUsers(){
        return userServiceimpl.viewAllUsers();
    }

    @GetMapping("/dashboard/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public SeatResponse showDetails(@PathVariable long userId){

        return userServiceimpl.getSeatByUserId(userId);
    }



    @PostMapping("/login")
    public String authenticateUser(@RequestBody User user){
        log.info("{} ",user.getUserId());
        return userServiceimpl.authenticateUser(user);
    }




}
