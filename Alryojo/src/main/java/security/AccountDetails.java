package security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class AccountDetails extends User{
	
	private String m_idx;
	private String admin_idx;
	private String photo;
	private String name;
	private String m_type;
	private String m_quitdate;
	
	
	

	public AccountDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String photo, String name, String m_type,String m_idx,String m_quitdate) {
		
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.photo = photo;
		this.name=name==null?username.substring(0,username.indexOf('@')):name;
		this.m_type=m_type;
		this.m_idx=m_idx;
		this.m_quitdate=m_quitdate;
		
	
		
	}
	
	// 관리자의 AccountDetails 객체 생성
	public AccountDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String photo, String name, String admin_idx) {
		
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.photo = photo;
		this.name=name;
		this.admin_idx=admin_idx;
	}
	
	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	
	

	public String getM_idx() {
		return m_idx;
	}

	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}

	public String getAdmin_idx() {
		return admin_idx;
	}

	public void setAdmin_idx(String admin_idx) {
		this.admin_idx = admin_idx;
	}
	
	

	public String getM_quitdate() {
		return m_quitdate;
	}

	public void setM_quitdate(String m_quitdate) {
		this.m_quitdate = m_quitdate;
	}

	@Override
	public String toString() {
		
		return "이메일>>"+getUsername()+"\n사진명>>"+getPhoto()+"\n이름>>"+getName()+"\n사진명>>"+getPhoto()+"\n권한"+getAuthorities();
	}
	
	
	
	
	

}
