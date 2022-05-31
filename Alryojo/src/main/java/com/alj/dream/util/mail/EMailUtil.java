package com.alj.dream.util.mail;


import com.alj.dream.member.domain.Emailinfos;


public class EMailUtil {
	
	
	
	public static String getUserEmail(Emailinfos email) {
		
		return email.getM_email_prefix().concat("@").concat(email.getM_email_suffix());
		
	}
	


}
