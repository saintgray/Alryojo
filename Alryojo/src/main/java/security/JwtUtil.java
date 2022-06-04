package security;
// 토큰 관련 유틸 클래스

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

public class JwtUtil {
	
	@Value("${jwt.key}")
	private String secret;
	@Value("${jwt.token.expiresInMillisec}")
	private long expiration;
	@Value("${jwt.token.cookie.expiresInsec}")
	private int cookieExpiration;
	@Value("${jwt.token.cookie.name}")
	private String cookieName;
	
	private static final String CLAIM_KEY_USERNAME="sub";
	private static final String ClAIM_KEY_CREATED="iat";
	
	private Clock clock= DefaultClock.INSTANCE;
	
	// 토큰 생성
	public String generateToken(UserDetails details) {
		final Date tokenCreatedDate=clock.now();
		final Date expirationDate=new Date(tokenCreatedDate.getTime()+expiration);
		
		return Jwts.builder()
					.setClaims(new HashMap<String,Object>())
					.setSubject(details.getUsername().toString())
					.setIssuedAt(tokenCreatedDate)
					.setExpiration(expirationDate)
					.signWith(SignatureAlgorithm.HS512, secret)
					.compact();		
	}
	
	// 토큰 쿠키에 저장
	public void setTokenCookie(HttpServletResponse resp, String token) {
		Cookie tokenCookie = new Cookie(cookieName, token);
		tokenCookie.setMaxAge(cookieExpiration);
		resp.addCookie(tokenCookie);
	}
	
	
	// 토큰으로 부터 유저 아이디 추출
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	// 토큰 유효성 검사
	public boolean tokenIsValidated(String token, UserDetails userDetails) {
		return (getUsernameFromToken(token).equals(userDetails.getUsername()) && ! getClaimFromToken(token, Claims::getExpiration).before(new Date()));
	}

}
