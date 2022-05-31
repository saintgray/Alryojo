package security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;


public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	
	// AuthenticationSuccessHandler 인터페이스를 구현한 LoginSuccessHandler 클래스
	// 로그인에 성공시 성공한 허가증에 있는 GrantedAuthority 콜렉션에 ROLE_ADMIN 이라는 권한이 있다면
	// 바로 관리자 페이지로 이동하며, 그렇지 않다면 알려드림 서비스의 메인 페이지로 이동한다.
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		AccountDetails logininfo= (AccountDetails)	authentication.getPrincipal();
		
		Iterator<GrantedAuthority> itr= logininfo.getAuthorities().iterator();
		

		RedirectStrategy rs=new DefaultRedirectStrategy();
		RequestCache rc=new HttpSessionRequestCache();
		
		
		String redirectUrl="/";
		String msg=null;
		
		
		
		
		
		// 로그인에 성공시 기본으로 이동활 화면은 홈 화면
		// 1. 인증이 필요한 페이지를 사용하려다 로그인페이지로 들어와서 로그인에 성공한 케이스		
		// 2. 직접 로그인 창을 클릭해서 로그인에 성공한 케이스
		System.out.println("getRequest(req,resp)...");
		System.out.println(rc.getRequest(request, response));
		if(rc.getRequest(request, response)==null) {
			
			// 2번 경우
			redirectUrl="/";
			
					
		}else {
			// 1번 경우
			redirectUrl=rc.getRequest(request, response).getRedirectUrl();
			
			// 문제점 : 예를들어 관리자 메인페이지 url 을 직접 입력해서들어가려다 로그인 창으로 들어왔다고 하면
			// ReqeustCache 의 DefaultSavedRequest에는 http://localhost:8080/mnm/admin/manage 가 저장된다.
			// 이때, 로그인을 하지 않고 다시 홈 화면으로 돌아가 직접 로그인 화면을 눌러 로그인을 할 경우 1번 경우에 해당 되지만
			// RequestCache 가 비워지질 않아서 rc.getRequest(request, response) != null 이기 때문에
			// 관리자 페이지로 들어가려고 함
			
		}
		
		// 로그인한 사람이 관리자라면 관리자 메인페이지로 이동한다.
		while(itr.hasNext()) {
			if(itr.next().getAuthority().equals("ROLE_ADMIN")) {
				redirectUrl="/admin/manage";
				break;
			}
		}
		
		// 회원인데 탈퇴했을 경우
		if(!redirectUrl.equals("/admin/manage")) {
			
			if(logininfo.getM_quitdate()!=null) {
				System.out.println("called by loginsuccessHandler >> 탈퇴한 유저입니다");
				msg="*탈퇴한 계정으로는 로그인 할 수 없습니다";
				msg=URLEncoder.encode(msg, "UTF-8");
				
				// 세션 무효화
				request.getSession().invalidate();
				// 쿠키삭제
				for(Cookie cookie:request.getCookies()) {
					if(cookie.getName().equals("remember-me")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}else if(cookie.getName().equals("JSESSIONID")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					
				}
				
				
				SecurityContext context = SecurityContextHolder.getContext();
				context.setAuthentication((Authentication)null);
				SecurityContextHolder.clearContext();
				
				
				
				// loginform 에 보여줄 메시지
				
				
				String insertedEmail=logininfo.getUsername();
				redirectUrl="/loginfailed?msg=".concat(msg).concat("&insertedEmail=".concat(insertedEmail));
				
				// 캐시 비움 막음
				String intercepted=request.getParameter("intercepted");
				System.out.println("intercepted...");
				System.out.println(intercepted);
				if(intercepted!=null){
						redirectUrl=redirectUrl.concat("&intercepted=true");
		    	};
				
				
				

			}	
		}	
		
		
		System.out.println("보내는 페이지:".concat(redirectUrl));
		rs.sendRedirect(request, response, redirectUrl);
		
		
		
		
	}
	
	
	

}
