package com.UserContentMicroservice.UserContent.UserContentData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.UserContentMicroservice.UserContent.Model.User;

@Repository
public interface UserContentRepository extends CrudRepository<UserContentEntity, Long>{

	public User findByUsername(String username);


}
