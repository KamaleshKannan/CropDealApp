package com.systemmanagement.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemmanagement.model.Role;
import com.systemmanagement.model.RoleType;
import com.systemmanagement.model.Users;
import com.systemmanagement.payload.request.LoginRequests;
import com.systemmanagement.payload.request.SignupRequests;
import com.systemmanagement.payload.response.JwtResponses;
import com.systemmanagement.payload.response.MessageResponses;
import com.systemmanagement.repository.RoleRepository;
import com.systemmanagement.repository.UsersRepository;
import com.systemmanagement.security.jwt.JwtUtils;
import com.systemmanagement.security.services.UsersDetailsImpl;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsersRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequests loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UsersDetailsImpl userDetails = (UsersDetailsImpl) authentication.getPrincipal();
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponses(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), Collections.singletonList(userDetails.getRole())));
	}

	@RequestMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequests signUpRequest) {
		System.out.println("signUpRequest" + signUpRequest);
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponses("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponses("Error: Email is already in use!"));
		}

		// Create new user's account
		Users user = new Users(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		String role = "";


		for (String roleTemp : strRoles) {
			switch (roleTemp) {
			case "ROLE_ADMIN":
				role = "ROLE_ADMIN";
				break;
			case "ROLE_FARMER":
				Role farmerRole = new Role();
				farmerRole.setName(RoleType.ROLE_FARMER);
				roles.add(farmerRole);
				role = "ROLE_FARMER";
				break;
			default:
				Role dealerRole = new Role();
				dealerRole.setName(RoleType.ROLE_DEALER);
				roles.add(dealerRole);
				role = "ROLE_DEALER";
				break;
			}
		}

		user.setRoles(roles);
		user.setRole(role);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponses("User registered successfully!"));
	}

	@PostMapping("/addfarmer")
	public ResponseEntity<?> registerFarmer(@Valid @RequestBody SignupRequests signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponses("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponses("Error: Email is already in use!"));
		}

		
//		if (strRoles == null) {
//		Role userRole = new Role();
//		userRole.setName(RoleType.ROLE_USER);
//		roles.add(userRole);
//		role = "ROLE_USER";
//	} else {
//		for (String role : strRoles) {
//			switch (role) {
//			case "ROLE_ADMIN":
//				Role adminRole = new Role();
//				adminRole.setName(RoleType.ROLE_ADMIN);
//				roles.add(adminRole);
//				role = "ROLE_ADMIN";
//				break;
//			case "ROLE_FARMER":
//				Role farmerRole = new Role();
//				farmerRole.setName(RoleType.ROLE_FARMER);
//				roles.add(farmerRole);
//				role = "ROLE_FARMER";
//				break;
//			default:
//				Role dealerRole = new Role();
//				dealerRole.setName(RoleType.ROLE_DEALER);
//				roles.add(dealerRole);
//				role = "ROLE_DEALER";
//				break;
//			}
//		}
//	}

		// Create new user's account
		Users user = new Users(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			/*
			 * Role userRole = roleRepository.findByName(RoleType.ROLE_DEALER)
			 * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			 * roles.add(userRole);
			 */
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "farmer":
					Role modRole = roleRepository.findByName(RoleType.ROLE_FARMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleType.ROLE_DEALER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		//user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponses("Farmer registered successfully!"));
	}
}
