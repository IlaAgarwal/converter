package com.converter.poc.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.converter.poc.entity.History;
import com.converter.poc.entity.JsonObject;
import com.converter.poc.entity.User;
import com.converter.poc.service.HistoryService;
import com.converter.poc.service.UserService;
import com.converter.poc.validation.CurrencyDateValidation;


@Controller

public class CurrencyController {

	private CurrencyDateValidation validator = new CurrencyDateValidation();
	@Autowired
	private HistoryService currencyService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/history")
	public String showHistory(Model theModel,Principal principal,HttpSession session) {
		

		if(principal!=null)
		{
		User user = userService.findByEmail(principal.getName());
		List<History> historyList = currencyService.getUserHistory(user.getUserId());
		session.setAttribute("userId", (int)user.getUserId());
		theModel.addAttribute("history", historyList);
		History userHistory = new History();
		theModel.addAttribute("currency", userHistory);
		
		return "show-history";
		}
		else 
		{
			session.invalidate();
			return "403";
		}
	}
	
	@PostMapping("convertCurrency")
	public String convertCurrency(@Valid @ModelAttribute("currency") History userHistory,BindingResult result, Model theModel, HttpSession session)
	{
		
		List<History> historyList = currencyService.getUserHistory((int) session.getAttribute("userId"));
		theModel.addAttribute("history", historyList);
		validator.validate(userHistory, result);
		if(result.hasErrors())
		{
			return "show-history";
		}
		JsonObject jsonObject = currencyService.callCurrencyLayerAPI(userHistory.getQueryDate());
		if((jsonObject.getSuccess()!=null && jsonObject.getSuccess().equalsIgnoreCase("false")))
		{
			theModel.addAttribute("errorMessage",jsonObject.getError().getInfo());
			theModel.addAttribute("currency", userHistory);
			return "show-history";
		}
		else{
		userHistory= currencyService.saveJsonObjectToHistory(jsonObject);
		theModel.addAttribute("currency", userHistory);
		currencyService.saveConversionHistory(userHistory, (int) session.getAttribute("userId"));
		}
		return "show-history";
	}
	
 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        sdf.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
}


