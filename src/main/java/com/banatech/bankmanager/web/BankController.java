package com.banatech.bankmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banatech.bankmanager.metier.IBankMetier;

@Controller
public class BankController {
	
	@Autowired
	private IBankMetier bankMetier;
	
	@RequestMapping(value="/operation")
	public String index() {
		return "comptes";
	}

}
