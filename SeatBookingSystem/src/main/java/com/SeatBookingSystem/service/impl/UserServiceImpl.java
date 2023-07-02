package com.SeatBookingSystem.service.impl;

import com.SeatBookingSystem.DTO.SeatResponse;
import com.SeatBookingSystem.entity.Seat;
import com.SeatBookingSystem.entity.User;
import com.SeatBookingSystem.exception.UserNameAlreadyExistsException;
import com.SeatBookingSystem.exception.UserNotFoundException;
import com.SeatBookingSystem.repository.UserRepository;
import com.SeatBookingSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) throws UserNameAlreadyExistsException {
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        String encryptedPwd = bcrypt.encode(user.getPassword());
        if(userRepository.findOneByUsername(user.getUsername()).isPresent()){
            throw new UserNameAlreadyExistsException("User name already exists");
        }
        user.setPassword(encryptedPwd);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUser(Long id,User user) {
        Optional<User> user1=userRepository.findById(id);
        if(user1.isPresent()) {
            user1.get().setUsername(user.getUsername());
            user1.get().setPassword(user.getPassword());
        }
        else throw new IllegalStateException("User: "+id+ " does not exists!");
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> viewAllUsers() {

        return userRepository.findAll();

    }

    @Override
    public SeatResponse getSeatByUserId(long userId) {

        Seat seat=  userRepository.findById(userId).get().getSeat();
        SeatResponse response = new SeatResponse();
        response.setSeatId(seat.getId());
        response.setUserId(userId);
        response.setFloor(seat.getFloor().getFloorNum());
        response.setUsername(seat.getUserId().getUsername());
        response.setBookingStatus(true);
        response.setBookingStartDate(seat.getBookingStartDate());
        response.setBookingEndDate(seat.getBookingEndDate());

        return response;
    }


    @Override
    public String authenticateUser(User user) throws UserNotFoundException {
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        Optional<User> opUser=userRepository.findOneByUsername(user.getUsername());
        //Optional<User> opUserId=userRepository.findById(user.getUserId());
        if(opUser.isPresent())
        {
            User dbUser=opUser.get();
            if(bcrypt.matches(user.getPassword(),dbUser.getPassword())){
                return "User Authenticated";
            }else{
                return "Incorrect Password";
            }
        }
        throw new UserNotFoundException("No user is found for this username");
    }




}
