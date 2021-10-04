package com.UserContentMicroservice.UserContent.UserContentService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.UserContentMicroservice.UserContent.Model.User;
import com.UserContentMicroservice.UserContent.UserContentData.UserContentEntity;
import com.UserContentMicroservice.UserContent.UserContentData.UserContentRepository;

public class UserContentServiceTest {

	
	private UserContentRepository repo = mock(UserContentRepository.class);
	
	private User user;
	
	private void generateUser() {
		user = new User();
		user.setUsername("testUser");
		user.setFirstName("first");
		user.setLastName("last");
		user.setAge(100);
		user.setOccupation("occupation");
	}
	
	@Test
	public void testAddUserContent() {
		UserContentEntity userContentEntity = new UserContentEntity();
		generateUser();
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.save(userContentEntity)).thenReturn(userContentEntity);
		
		service.addUserContent(user);
		Assertions.assertDoesNotThrow(() -> repo.save(userContentEntity));
		verify(repo, times(1)).save(userContentEntity);
	}
	
	@Test
	public void testAddUserContentThrowsException() {
		UserContentEntity userContentEntity = new UserContentEntity();
		generateUser();
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.save(userContentEntity)).thenThrow(EntityNotFoundException.class);
		
		service.addUserContent(user);
		Assertions.assertThrows(EntityNotFoundException.class, () -> repo.save(userContentEntity));
		verify(repo, times(1)).save(userContentEntity);
	}
	
	@Test
	public void testRetrieveFirstName() {
		
		generateUser();		
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.findByUsername(user.getUsername())).thenReturn(user);
		
		String firstName = service.retrieveFirstName(user.getUsername());
		
		Assertions.assertEquals(user.getFirstName(), firstName);
		verify(repo, times(1)).findByUsername(user.getUsername());
	}
	
	@Test
	public void testRetrieveFirstNameThrowsException() {
		
		generateUser();		
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.findByUsername(user.getUsername())).thenThrow(EntityNotFoundException.class);
		
		String firstName = service.retrieveFirstName(user.getUsername());
		
		Assertions.assertEquals(null, firstName);
		Assertions.assertThrows(EntityNotFoundException.class, () -> repo.findByUsername(user.getUsername()));
		verify(repo, times(2)).findByUsername(user.getUsername());
	}

	@Test
	public void testRetrieveLastName() {
		
		generateUser();		
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.findByUsername(user.getUsername())).thenReturn(user);
		
		String lastName = service.retrieveLastName(user.getUsername());
		
		Assertions.assertEquals(user.getLastName(), lastName);
		verify(repo, times(1)).findByUsername(user.getUsername());
	}
	
	@Test
	public void testRetrieveLastNameThrowsException() {
		
		generateUser();		
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.findByUsername(user.getUsername())).thenThrow(EntityNotFoundException.class);
		
		String lastName = service.retrieveLastName(user.getUsername());
		
		Assertions.assertEquals(null, lastName);
		Assertions.assertThrows(EntityNotFoundException.class, () -> repo.findByUsername(user.getUsername()));
		verify(repo, times(2)).findByUsername(user.getUsername());
	}
	
	@Test
	public void testCalculateDogAge() {
		
		generateUser();
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.findByUsername(user.getUsername())).thenReturn(user);
		
		int dogAge = service.calculateDogAge(user.getUsername());
		
		Assertions.assertTrue(dogAge % 7 == 0 && dogAge / 7 == user.getAge());
		verify(repo, times(1)).findByUsername(user.getUsername());
	}
	
	@Test
	public void testCalculateDogAgeThrowsException() {
		generateUser();
		UserContentService service = new UserContentServiceImpl(repo);
		
		when(repo.findByUsername(user.getUsername())).thenThrow(EntityNotFoundException.class);
		
		int dogAge = service.calculateDogAge(user.getUsername());
		
		Assertions.assertTrue(dogAge < 0);
		Assertions.assertThrows(EntityNotFoundException.class, () -> repo.findByUsername(user.getUsername()));
		verify(repo, times(2)).findByUsername(user.getUsername());
		
	}
}
