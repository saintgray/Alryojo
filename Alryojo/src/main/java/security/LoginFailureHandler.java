package security;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;


public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		// Spring 에서 발생한 예외의 종류는 크게 4가지가 있다.
		// 1. BadCredentialsException : 아이디와 비밀번호가 서로 일치하지 않을 경우 발생하는 예외
		// 2. UserNameNotFoundException : 입력한 아이디가 데이터베이스에 없을 경우 발생하는 예외
		// 3. DisabledException : 인증에는 성공했으나 Authentication 의  isEnabled 값이 false 일 경우
		// 4. SessionAuthenticationException : 이미 인증이 된 아이디일 경우
		String msg=null;
		logger.info("+ called onAuthenticationFailure");
		logger.info(exception.getMessage());		
		if(exception instanceof UsernameNotFoundException) {
			logger.info("+ errorType = UsernameNotFoundException");
			msg="* 아이디 혹은 비밀번호가 틀립니다";
		}else if(exception instanceof DisabledException){
			logger.info("+ errorType = DisabledException");
			System.out.println("errortype = Disabled");
			msg="* 잠긴 계정입니다. 고객센터에 문의하세요";
		
		}else if(exception instanceof BadCredentialsException) {
			msg="* 아이디 혹은 비밀번호가 틀립니다";
		}else if(exception instanceof SessionAuthenticationException) {
			// when does sessionAuthenticationException triggered?
		}
		response.setHeader("msg", URLEncoder.encode(msg,"UTF-8"));
	}
}
