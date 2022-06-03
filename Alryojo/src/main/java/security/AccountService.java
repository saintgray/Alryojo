package security;

import java.util.LinkedList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

	private SqlSessionTemplate stp;

	// 계정 잠금, 만료와 관련된 User 클래스의 파라미터들.
	// 생성자의 파라미터 기본값은 true 이며, 회원 테이블의 role 컬럼을 만든 것처럼 아래 4개의 컬럼을 회원의 컬럼에 추가하여
	// 조건에 따라 다른 값을 넣어 줄 수 도 있다.
	// boolean enabled =true;
	// boolean accountNonExpired=true;
	// boolean credentialsNonExpired=true;
	// boolean accountNonLocked=true;

	public AccountService() {
	}

	@Autowired
	public AccountService(SqlSessionTemplate stp) {
		this.stp = stp;
	}

	private static List<GrantedAuthority> getRoleList(String roles) {
		List<GrantedAuthority> roleList = new LinkedList<GrantedAuthority>();
		for (String role : roles.split(",")) {
			roleList.add(new SimpleGrantedAuthority(role));
		}

		System.out.println(roleList);
		return roleList;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		boolean enabled = true;

		System.out.println("username = " + username);

		AccountDetails userDetails = null;

		if (username.substring(username.indexOf("@")).equalsIgnoreCase("@aljdream.com")) {
			AdminVO vo = stp.selectOne("selectbyadminname", username);
			if (vo == null) {
				throw new UsernameNotFoundException("BadCredentialException throws");
			}
			userDetails = new AccountDetails(vo.getAdmin_email(), vo.getAdmin_password(), true, true, true, true,
					getRoleList(vo.getRole()), vo.getPhoto(), vo.getAdmin_nm(), vo.getAdmin_idx());

		} else {
			MemberVO vo = stp.selectOne("selectbyusername", username);

			if (vo == null) {
				throw new UsernameNotFoundException("*아이디 혹은 비밀번호가 틀립니다");
			} else if (vo.getM_blacklist().equals("Y")) {
				enabled = false;
			}
			userDetails = new AccountDetails(vo.getM_email(), vo.getM_password(), enabled, true, true, true,
					getRoleList(vo.getRole()), vo.getM_photo(), vo.getM_nm(), "mentee", vo.getM_idx(),
					vo.getM_quitdate());
		}
		
		return userDetails;
	}
}
