package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.services.UserAccountService;

@Controller
public class SecurityController {

//	@Autowired
//	UserAccountRepository accountRepo;
	
	@Autowired
	UserAccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);

		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) { //The order in the parenthesis matters. model should be first
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountService.save(user);
		// WARNING!!! be sure to add csrf token on the register.html file or any file with forms right before the buttons 
		return "redirect:/";
	}
}
