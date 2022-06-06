package security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;


public class JwtRequestFilter extends OncePerRequestFilter{

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	private AccountService accountService;
	private JwtUtil jwtUtil;
	
	
	
	@Autowired
	public JwtRequestFilter(AccountService accountService, JwtUtil jwtUtil) {
		this.accountService = accountService;
		this.jwtUtil = jwtUtil;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username=null;
		String jwtToken=null;
		
		final String tokenHeader=request.getHeader("Authorization");
		logger.info("+ authorization header value" + tokenHeader);
		if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")) {
			logger.info("+ Bearer token validated...");
			jwtToken=tokenHeader.substring(7);
			logger.info("+ token value = " + jwtToken);
			try {
				username=jwtUtil.getUsernameFromToken(jwtToken);
				logger.info("+ username : "+ username);
			}catch(IllegalArgumentException e) {
				logger.info("\n\n + illegalArgumentException occured... \n\n");
			}catch(ExpiredJwtException e) {
				logger.info("\n\n + token was expired already..... \n\n");
			}
		}else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		logger.info("+ Authentication is null? "+String.valueOf(SecurityContextHolder.getContext().getAuthentication()==null));
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			logger.info("+ this user has loggged in and has authentication");
			UserDetails details= accountService.loadUserByUsername(username);
			logger.info("+ user deatils debug : username = "+details.getUsername());
			if(jwtUtil.tokenIsValidated(jwtToken, details)) {
				logger.info("token is validated");
				
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword(),details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
