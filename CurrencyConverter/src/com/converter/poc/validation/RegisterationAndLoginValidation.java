package com.converter.poc.validation;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.converter.poc.entity.User;
import com.converter.poc.service.UserService;
import com.converter.poc.service.UserServiceImpl;

@Component
public class RegisterationAndLoginValidation implements  Validator
{
	
		private Pattern pattern;
		private Matcher matcher;
		
	  private String STRING_PATTERN_ALPHABETS = "[a-zA-Z]+"; 
	 private String STRING_PATTERN_EMAIL = "[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})";
		  
		  public void validate(Object object, Errors errors) {
			  
			  User user = (User) object;
			
		    
		 
		  if(user.getPassword()!=null && user.getConfirmPassword()!=null && !(user.getConfirmPassword().equals(user.getPassword()))){
			  errors.rejectValue("confirmPassword" , "password.mismatch");
		  		}
		  
		  if(user.getPassword()!=null && user.getPassword().length()<8){
				errors.reject("password", "password.min.length");
				}
		  
		  if(user.getEmail()!=null)
		  {
			  pattern = Pattern.compile(STRING_PATTERN_EMAIL);
			  matcher = pattern.matcher(user.getEmail());
			  if(!matcher.matches())
			  {
				  errors.rejectValue("email", "email.invalid");
			  }
		  }
		  if (user.getFirstName() != null) {  
	    	   pattern = Pattern.compile(STRING_PATTERN_ALPHABETS);  
	    	   matcher = pattern.matcher(user.getFirstName());  
	    	   if (!matcher.matches()) {  
	    	    errors.rejectValue("firstName", "name.invalid",  
	    	      "Enter a valid name");  
	    	   }  
	    	  }  
	    
	    if	(user.getLastName() != null) {  
	    	   pattern = Pattern.compile(STRING_PATTERN_ALPHABETS);  
	    	   matcher = pattern.matcher(user.getLastName());  
	    	   if (!matcher.matches()) {  
	    	    errors.rejectValue("lastName", "name.invalid",  
	    	      "Enter a valid name");  
	    	   }  
	    	  }  
	   	   
	    if (user.getUserAddress().getCity() != null) {  
	    	   pattern = Pattern.compile(STRING_PATTERN_ALPHABETS);  
	    	   matcher = pattern.matcher(user.getUserAddress().getCity());  
	    	   if (!matcher.matches()) {  
	    	    errors.rejectValue("city", "city.invalid",  
	    	      "Enter a valid city");  
	    	   }  
	    	  } 
		  
		  }
			 	
		@Override
		public boolean supports(Class<?> arg0) {
			  return User.class.equals(arg0);
		}

	

}
