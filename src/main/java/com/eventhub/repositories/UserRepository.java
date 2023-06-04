package com.eventhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
