package security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

// 로그아웃 성공시 처리해야할 작업이 있다면 LogoutSuccessHandler 인터페이스를 구현하여 이 클래스 내에서 처리하도록 한다.

//public class LogoutSuccessCustomHandler implements LogoutSuccessHandler{
public class LogoutSuccessCustomHandler{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		try {
			SecurityContextHolder.getContext().setAuthentication(null);
			response.setHeader("Location", "/");
			logger.info("+ logout successfully");
		}catch(Exception e) {
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}
