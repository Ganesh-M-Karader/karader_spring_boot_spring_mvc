package com.karader.irs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.karader.irs.exception.UserIdAlreadyPresentException;
import com.karader.irs.model.User;
import com.karader.irs.service.RegistrationService;

@Controller
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private Environment environment;

	private String command = "command";
	private String register = "register";

	@GetMapping(value = "/register")
	public ModelAndView register(Model model) {
		return new ModelAndView(register, command, new User());
	}

	@PostMapping("registerUser")
	public ModelAndView addCustomer(@Valid @ModelAttribute("command") User user, BindingResult bindingResult,
			ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			new ModelAndView(register, command, user);
		} else {
			try {
				registrationService.registerUser(user);
				modelAndView = new ModelAndView(register, command, user);
				modelAndView.addObject("successMessage", environment.getProperty("SUCCESS"));
			} catch (UserIdAlreadyPresentException e) {
				modelAndView = new ModelAndView(register);
				modelAndView.addObject(command, user);
				modelAndView.addObject("message", environment.getProperty(e.getMessage()));
			}
		}
		return modelAndView;
	}
}
