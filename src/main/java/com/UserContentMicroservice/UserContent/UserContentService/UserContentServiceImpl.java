package com.UserContentMicroservice.UserContent.UserContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserContentMicroservice.UserContent.Model.User;
import com.UserContentMicroservice.UserContent.UserContentData.UserContentEntity;
import com.UserContentMicroservice.UserContent.UserContentData.UserContentRepository;

@Service
public class UserContentServiceImpl implements UserContentService {

	private UserContentRepository repo;

	@Autowired
	public UserContentServiceImpl(UserContentRepository repo) {
		// TODO Auto-generated constructor stub
		this.repo = repo;
	}

	@Override
	public void addUserContent(User user) {
		// TODO Auto-generated method stub
		try {
			repo.save(mapToEntity(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String retrieveFirstName(String username) {
		// TODO Auto-generated method stub
		try {
			User user = repo.findByUsername(username);
			return user.getFirstName();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String retrieveLastName(String username) {
		// TODO Auto-generated method stub
		try {
			User user = repo.findByUsername(username);
			return user.getLastName();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int calculateDogAge(String username) {
		// TODO Auto-generated method stub
		try {
			User user = repo.findByUsername(username);
			return user.getAge() * 7;
		} catch (Exception e) {
			return -1;
		}
	}

	private UserContentEntity mapToEntity(User user) {

		UserContentEntity entity = new UserContentEntity();

		entity.setUsername(user.getUsername());
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setAge(user.getAge());
		entity.setOccupation(user.getOccupation());

		return entity;
	}

}
