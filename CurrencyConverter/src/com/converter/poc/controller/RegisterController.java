package com.converter.poc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.converter.poc.entity.States;
import com.converter.poc.entity.User;
import com.converter.poc.service.StatesService;
import com.converter.poc.service.UserService;
import com.converter.poc.validation.RegisterationAndLoginValidation;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private RegisterationAndLoginValidation validator = new RegisterationAndLoginValidation();
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatesService statesService;
	
	
	@GetMapping("/form")
	public String shorRegisterationForm(Model theModel)
	{
		User user = new User();
		theModel.addAttribute("userDetails", user);
		theModel.addAttribute("theCountryOptions",statesService.findAllCountry());
		return "register-user";
	}
	
	@RequestMapping(value="/processRegisteration", method = RequestMethod.POST)
	public String registerUser( Model theModel,@Valid @ModelAttribute("userDetails") User userDetails , BindingResult result)
	{	
		
		theModel.addAttribute("userDetails", userDetails);
		theModel.addAttribute("theCountryOptions",statesService.findAllCountry());
		validator.validate(userDetails, result);
		boolean userRegistered = false;
		int count=0;
		if(!result.hasErrors())
		{ count++;
			userRegistered	 = userService.registerUser(userDetails);
		}
		if(!userRegistered && count>0)
		{
			result.rejectValue("email", "email.exists","emailAlready exists");
		}
		if(result.hasErrors())
		{
			
			return "register-user";
		}
		
		
		return "login";
	}
	
	@RequestMapping(value="/stateList", method = RequestMethod.GET)
	public @ResponseBody Set<States> getStates(@RequestParam(value = "countryName", required = true) String countryName)
	{
		return statesService.findByCountryName(countryName);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

	  dataBinder.setDisallowedFields(new String[] {"userId"});

	  dataBinder.setRequiredFields(new String[] {"firstName", "lastName", "email", "password","userAddress.stateName","userAddress.countryCode"});

	  dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	  dateFormat.setLenient(false);
	  dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
