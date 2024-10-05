package com.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.UserCredentialsEntity;

@Repository
public interface UserCredentialsDao extends JpaRepository<UserCredentialsEntity, Integer>{
public Optional<UserCredentialsEntity> findByName(String name);
}
