package com.systemmanagement.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "";
	}
	
	@GetMapping("/admin")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public String adminAccess() {
		return "Admin Page";
	}
	
	@GetMapping("/farmer")
	@PreAuthorize("hasRole('FARMER')")
	public String farmerAccess() {
		return "Farmer Page";
	}

	/*@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}*/

	@GetMapping("/dealer")
	@PreAuthorize("hasRole('DEALER')")
	public String dealerAccess() {
		return "Dealer Page";
	}
}

