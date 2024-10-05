package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.UserCredentialsEntity;
import com.example.service.JwtService;
import com.example.service.UserCredentialsService;

@RestController
@RequestMapping("/api/auth")
public class UserCredentialsController {
	@Autowired
	JwtService jwtService;
	@Autowired
	private UserCredentialsService userCredService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public UserCredentialsEntity register(@RequestBody UserCredentialsEntity user) {
		return userCredService.register(user);
	}

	@GetMapping("/validate/token")
	public boolean validateToken(@RequestParam String token) {
		return userCredService.verifyToken(token);
	}

	@PostMapping("/validate/user")
	public String getToken(@RequestBody UserCredentialsEntity user) {
		System.out.println("user : " + user);
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		System.out.println("authenticated?? : " + authenticate.isAuthenticated());
		UserCredentialsEntity fullUser = userCredService.findUserByName(user.getName());
		if (authenticate.isAuthenticated()) {
			return userCredService.generateToken(user.getName(), fullUser.getRole());
		}
		return null;
	}

	@GetMapping("/validate/role")
	public String getRole(@RequestParam String token){
		return jwtService.getRole(token);
	}
}
