package com.converter.poc.validation;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.converter.poc.entity.History;
import com.converter.poc.entity.User;

@Component
public class CurrencyDateValidation implements Validator{
		  public void validate(Object object, Errors errors) {
			
			  History history = (History) object;
			
		    if(history.getQueryDate()!=null && history.getQueryDate().after(new Date()))
		    {
		    	errors.rejectValue("queryDate","queryDate.invalid");
		    }
		   
		  }

		@Override
		public boolean supports(Class<?> arg0) {
			  return User.class.equals(arg0);
		}
}
