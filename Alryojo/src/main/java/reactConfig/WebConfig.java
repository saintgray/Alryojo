package reactConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	
	// WebMvcConfigurer 인터페이스를 구현한 WebConfig 클래스
	// addCorsMappings 메소드를 오버라이딩하여 React 가 사용하는 도메인인 http://localhost:3000 에서 들어오는
	// 요청을 허락하도록 설정한다
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowCredentials(false);
	}
	
	
	
	// 단 Spring security 를 사용하고 있을 경우 security 의 filter 영역에서 먼저 걸리기 때문에
	// CORS 설정이 되어 있어도 해결이 되지 않는다.
	// 그 원인은 클라이언트가 요청시 예비 요청인 Preflight Request 를 보내어 실제 요청이 전송하기에 안전한지 확인을 한다.
	// 즉, 이 예비 요청에 대한 응답이 200으로 떨어져야 본 요청을 진행할 수 있는데, security filter 에서 이 예비요청에 대한
	// 응답을 401 로 내려주기 때문에 본 요청이 전송되지 않는 것이다.
	
	
	// security 단에서도 react 가 사용하는 도메인에 관한 요청을 허락하도록 설정해주어야 하는데

	
	

}
