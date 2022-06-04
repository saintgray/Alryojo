package security;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private JwtUtil jwtUtil;
	
	public TokenProvider() {}
	
	@Autowired
	public TokenProvider(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public void createAuthToken(UserDetails details, HttpServletResponse resp){

		String token = jwtUtil.generateToken(details);
		jwtUtil.setTokenCookie(resp, token);
	}

}
