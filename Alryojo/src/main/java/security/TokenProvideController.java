package security;

import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TokenProvideController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AccountService accountService;
	private AuthenticationManager authManager;
	private JwtUtil jwtUtil;

	@Autowired
	public TokenProvideController(AccountService accountService, AuthenticationManager authManager, JwtUtil jwtUtil) {
		this.accountService = accountService;
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping(value = "${jwt.login.uri}")
	public ResponseEntity<?> createAuthToken(	@RequestBody Map<String, Object> signInInfo, 
												HttpServletResponse resp) throws AuthenticationException {
		
		authManager.authenticate(new UsernamePasswordAuthenticationToken(signInInfo.get("username").toString(),
				signInInfo.get("password").toString()));
		UserDetails details = accountService.loadUserByUsername(signInInfo.get("username").toString());
		String token = jwtUtil.generateToken(details);
		jwtUtil.setTokenCookie(resp, token);
		
		return ResponseEntity.ok().build();
		
	}
}
