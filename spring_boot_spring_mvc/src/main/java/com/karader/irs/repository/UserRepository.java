package com.karader.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karader.irs.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
