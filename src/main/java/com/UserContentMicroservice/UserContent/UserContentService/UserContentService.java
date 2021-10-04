package com.UserContentMicroservice.UserContent.UserContentService;

import com.UserContentMicroservice.UserContent.Model.User;

public interface UserContentService {
	
	public void addUserContent(User user);
	
	public String retrieveFirstName(String username);
	
	public String retrieveLastName(String username);
	
	public int calculateDogAge(String username);	

}
