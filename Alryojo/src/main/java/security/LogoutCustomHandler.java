package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class LogoutCustomHandler implements LogoutHandler{
//public class LogoutCustomHandler{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		logger.info("+ logout handler called....");
		logger.info("+ Current Authentication : {}",authentication);
		try {
			SecurityContextHolder.getContext().setAuthentication(null);
		}catch(Exception e) {
			logger.info("+ Internal Server Error occured");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
}
