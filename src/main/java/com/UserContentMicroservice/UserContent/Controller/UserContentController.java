package com.UserContentMicroservice.UserContent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.UserContentMicroservice.UserContent.JwtAuthUtil;
import com.UserContentMicroservice.UserContent.Model.User;
import com.UserContentMicroservice.UserContent.UserContentService.UserContentService;

import lombok.AllArgsConstructor;

@RestController
public class UserContentController {

	private UserContentService userContentService;
	private JwtAuthUtil jwtAuthUtil;

	@Autowired
	public UserContentController(UserContentService userContentService, JwtAuthUtil jwtAuthUtil) {
		this.jwtAuthUtil = jwtAuthUtil;
		this.userContentService = userContentService;
	}

	@RequestMapping(value = "/addContent", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserContent(@RequestBody User userContent) {

		try {
			userContentService.addUserContent(userContent);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public ResponseEntity<String> retrieveFirstName(@RequestHeader HttpHeaders httpHeaders) {
		String token = httpHeaders.get("Authorization").get(0).replace("Bearer ", "");
		String username = jwtAuthUtil.getUsernameFromToken(token);

		String firstName = userContentService.retrieveFirstName(username);

		return new ResponseEntity<String>(firstName, HttpStatus.OK);
	}

	@RequestMapping(value = "/last", method = RequestMethod.GET)
	public ResponseEntity<String> retrieveLastName(@RequestHeader HttpHeaders httpHeaders) {
		String token = httpHeaders.get("Authorization").get(0).replace("Bearer ", "");
		String username = jwtAuthUtil.getUsernameFromToken(token);

		String lastName = userContentService.retrieveLastName(username);

		return new ResponseEntity<String>(lastName, HttpStatus.OK);
	}

	@RequestMapping(value = "/dogAge", method = RequestMethod.GET)
	public ResponseEntity<Integer> getDogAge(@RequestHeader HttpHeaders httpHeaders) {

		String token = httpHeaders.get("Authorization").get(0).replace("Bearer ", "");
		String username = jwtAuthUtil.getUsernameFromToken(token);

		int dogAge = userContentService.calculateDogAge(username);

		return new ResponseEntity<Integer>(dogAge, HttpStatus.OK);
	}
}
