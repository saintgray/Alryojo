package security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

public class JwtRequestFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

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

		String username = null;
		String jwtToken = null;

		final String tokenHeader = request.getHeader("Authorization");
		int respStatus = HttpStatus.OK.value();

		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			logger.info("+ Bearer token validated...");
			jwtToken = tokenHeader.substring(7);

			try {
				username = jwtUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.info("\n\n + illegalArgumentException occured... \n\n");
				respStatus = HttpStatus.UNAUTHORIZED.value();
			} catch (ExpiredJwtException e) {
				logger.info("\n\n + token was expired already..... \n\n");
				respStatus = HttpStatus.UNAUTHORIZED.value();
			} catch (MalformedJwtException e) {
				logger.info("\n\n + token is not well-formed \n\n");
				respStatus = HttpStatus.UNAUTHORIZED.value();
			}

		} else {
			logger.warn("JWT Token does not begin with Bearer String");
			respStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails details = accountService.loadUserByUsername(username);
			
			if (details == null) {
				respStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
			} else {
				if (jwtUtil.tokenIsValidated(jwtToken, details)) {
					logger.info("token is validated");
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							details.getUsername(), "", details.getAuthorities());
//					추가적으로 권한이나 인증 정보가 필요할 시에는 setDetails 메소드를 사용하여 권한에 함께 넣어주도록 한다.
//					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);

					logger.info("SecurityContextHolder Authentication : {}",
							SecurityContextHolder.getContext().getAuthentication());
				}
			}

		}
		logger.info("+ respStatus : {}" , String.valueOf(respStatus));
		if(respStatus==HttpStatus.OK.value()) {
			filterChain.doFilter(request, response);
		}else {
			response.sendError(respStatus);
		}
		
	}
}
