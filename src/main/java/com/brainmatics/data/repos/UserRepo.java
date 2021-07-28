package com.brainmatics.data.repos;

import com.brainmatics.data.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    public User findByEmail(String email); //derived name query spring jpa

}
