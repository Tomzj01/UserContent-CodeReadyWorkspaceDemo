package com.UserContentMicroservice.UserContent.UserContentController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.UserContentMicroservice.UserContent.JwtAuthUtil;
import com.UserContentMicroservice.UserContent.Controller.UserContentController;
import com.UserContentMicroservice.UserContent.Model.User;
import com.UserContentMicroservice.UserContent.UserContentService.UserContentService;

public class UserContentControllerTest {

	private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0VXNlciIsIm5hbWUiOiJ0ZXN0IHVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9.shenysU0w6za56jUvdrjuu9nS8hxkusvhuajOx6UQrA";
	UserContentService service = mock(UserContentService.class);
	JwtAuthUtil jwtAuthUtil = mock(JwtAuthUtil.class);
	
	@Test
	public void testAddUserContent() {
		UserContentController controller = new UserContentController(service, jwtAuthUtil);
		User user = new User();
		
		ResponseEntity<Void> expected = new ResponseEntity<Void>(HttpStatus.CREATED);
		

		when(jwtAuthUtil.getUsernameFromToken(JWT_TOKEN)).thenReturn("testUser");

		ResponseEntity<Void> response = controller.addUserContent(user);

		Assertions.assertEquals(expected, response);
		verify(service, times(1)).addUserContent(user);
		
	}
	
	@Test
	public void testRetrieveFirstName() {
		UserContentController controller = new UserContentController(service, jwtAuthUtil);

		ResponseEntity<String> expected = new ResponseEntity<String>("first", HttpStatus.OK);

		when(jwtAuthUtil.getUsernameFromToken(JWT_TOKEN)).thenReturn("testUser");
		when(service.retrieveFirstName("testUser")).thenReturn("first");

		ResponseEntity<String> response = controller.retrieveFirstName(generateHeaders());

		Assertions.assertEquals(expected, response);
		verify(jwtAuthUtil, times(1)).getUsernameFromToken(JWT_TOKEN);
		verify(service, times(1)).retrieveFirstName("testUser");

	}

	@Test
	public void testRetrieveLastName() {
		UserContentController controller = new UserContentController(service, jwtAuthUtil);

		ResponseEntity<String> expected = new ResponseEntity<String>("first", HttpStatus.OK);

		when(jwtAuthUtil.getUsernameFromToken(JWT_TOKEN)).thenReturn("testUser");
		when(service.retrieveLastName("testUser")).thenReturn("first");

		ResponseEntity<String> response = controller.retrieveLastName(generateHeaders());

		Assertions.assertEquals(expected, response);
		verify(jwtAuthUtil, times(1)).getUsernameFromToken(JWT_TOKEN);
		verify(service, times(1)).retrieveLastName("testUser");
	}

	@Test
	public void testGetDogAge() {

		UserContentController controller = new UserContentController(service, jwtAuthUtil);

		ResponseEntity<Integer> expected = new ResponseEntity<Integer>(7, HttpStatus.OK);

		when(jwtAuthUtil.getUsernameFromToken(JWT_TOKEN)).thenReturn("testUser");
		when(service.calculateDogAge("testUser")).thenReturn(7);

		ResponseEntity<Integer> response = controller.getDogAge(generateHeaders());

		Assertions.assertEquals(expected, response);
		verify(jwtAuthUtil, times(1)).getUsernameFromToken(JWT_TOKEN);
		verify(service, times(1)).calculateDogAge("testUser");
	}

	private HttpHeaders generateHeaders() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", JWT_TOKEN);
		return headers;
	}

}
