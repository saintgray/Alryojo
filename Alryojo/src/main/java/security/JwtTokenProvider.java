package security;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenProvider{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private JwtUtil jwtUtil;
	
	public JwtTokenProvider() {}
	
	@Autowired
	public JwtTokenProvider(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public String createAuthToken(UserDetails details, HttpServletResponse resp){
		logger.info("+ create Jwt Token...");
		String token = jwtUtil.generateToken(details);
		return token;
	}


}
