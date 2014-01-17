package com.gp.ultimateemailidengine.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainFormat {
	private Pattern pattern;
	private Matcher matcher;
	
	//regex that determines the right domain format
	private static final String EMAIL_PATTERN = 
		"^@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public DomainFormat(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	//returns true if domain is valid format:@domain.com
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}
