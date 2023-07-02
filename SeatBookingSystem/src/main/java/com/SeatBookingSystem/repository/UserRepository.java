package com.SeatBookingSystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SeatBookingSystem.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //List <User> findByUsername(String username);
    Optional <User> findById(Long userId);

    Optional <User> findOneByUsername(String username);
    // Optional<User> findById(Long userId);

    //  List<User> findByUsername(String username);

}
