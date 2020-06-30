package com.karader.irs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karader.irs.entity.UserEntity;
import com.karader.irs.exception.UserIdAlreadyPresentException;
import com.karader.irs.model.User;
import com.karader.irs.repository.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	UserRepository repository;

	public void registerUser(User user) throws UserIdAlreadyPresentException {
		boolean exist = repository.existsById(user.getUserId());
		if (exist)
			throw new UserIdAlreadyPresentException("USER_EXIST");

		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setName(user.getUserId());
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
		repository.saveAndFlush(userEntity);
		
	}
}
