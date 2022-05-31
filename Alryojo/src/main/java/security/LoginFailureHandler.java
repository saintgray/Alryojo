package security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;


public class LoginFailureHandler implements AuthenticationFailureHandler {

	
	
	
	
	// 인증에 실패했을 시 처리하는 Spring 의 AuthenticationFailureHandler 클래스의 onAuthenticationFailure 메소드를 오버라이딩 하여 커스터마이징한다.
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		// Spring 에서 발생한 예외의 종류는 크게 4가지가 있다.
		// 1. BadCredentialsException : 아이디와 비밀번호가 서로 일치하지 않을 경우 발생하는 예외
		// 2. UserNameNotFoundException : 입력한 아이디가 데이터베이스에 없을 경우 발생하는 예외
		// 3. DisabledException : 인증에는 성공했으나 Authentication 의  isEnabled 값이 false 일 경우
		// 4. SessionAuthenticationException : 이미 인증이 된 아이디일 경우
		String msg=null;
		
		
		String forwarding="/member/loginform";
		if(request.getParameter("intercepted").equals("true")) {
			forwarding=forwarding.concat("?interecpted=true");
		}

		
	  
		
		System.out.println("called onAuthenticationFailure");
		System.out.println(exception.getMessage());
		
		if(exception instanceof UsernameNotFoundException) {
			
			System.out.println("UserNotFound");
			System.out.println("exceptionmsg >>> ".concat(exception.getMessage()));
			msg=exception.getMessage();
			
			
			
			
		}else if(exception instanceof DisabledException){
			System.out.println("errortype = Disabled");
			
			msg="*잠긴 계정입니다. 고객센터에 문의하세요";
		
		}else if(exception instanceof BadCredentialsException) {
			
			msg="*아이디 혹은 비밀번호를 잘못 입력하셨습니다";
			
		}else if(exception instanceof SessionAuthenticationException) {
			
			forwarding="redirect:/";
			
		}
		
		Map<String,String> info = new HashMap<String, String>();
		info.put("insertedEmail", request.getParameter("username"));
		info.put("msg",msg);
		
		request.setAttribute("error", info);
		
		request.getRequestDispatcher(forwarding).forward(request, response);
		
		
		
	}
	
	

}
