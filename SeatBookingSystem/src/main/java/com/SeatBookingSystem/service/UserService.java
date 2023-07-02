package com.SeatBookingSystem.service;

import com.SeatBookingSystem.DTO.SeatResponse;
import com.SeatBookingSystem.entity.User;
import com.SeatBookingSystem.exception.UserNameAlreadyExistsException;

import java.util.List;

public interface UserService {

    User addUser(User user) throws UserNameAlreadyExistsException;
    void updateUser(Long id, User user);
    void deleteUser(Long userId);
    List<User> viewAllUsers();

    SeatResponse getSeatByUserId(long userId);
    String authenticateUser(User user);
}
