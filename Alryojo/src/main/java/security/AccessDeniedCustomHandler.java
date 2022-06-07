package security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedCustomHandler implements AccessDeniedHandler {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.info("+ access denied...");
		
//		서버는 사용자가 권한이 없는 경우 응답하는 통상적인 403 에러를 응답하지 않는 것이 좋다.
//		403 에러 자체가 특정 리소스를 요청하는 url 에 대해 서버가 그 url Mapping 을 가지고 있다는 반증이기도 하므로
//		굳이 서버에 그 요청을 받는 정보가 있음을 알려줄 필요가 없다. 대신 500 에러로 응답하여 
//		좀 더 범용적인 서버 에러 코드로 응답하도록 설정하는 것이 바람직하다
//		response.setStatus(HttpStatus.FORBIDDEN.value());
		// >> NOT RECOMMEND
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		// >> RECOMMEND
		response.setHeader("Location", "/");
		// 리다이렉션시킬 Location 정보가 필요하다면 분기하여 설정해 주도록 한다.
	}
}
