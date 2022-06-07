package security.vo;

public class MemberVO {
	
	private String m_idx;
	private String m_email;
	private String m_password;
	private String m_nm;
	private String role;
	private String m_photo;
	private String m_blacklist;
	private String m_regdate;
	private String m_quitdate;
	public MemberVO() {
		
	}
	public MemberVO(String m_idx, String m_email, String m_password, String m_nm, String role, String m_photo,
			String m_blacklist, String m_regdate, String m_quitdate) {
		this.m_idx = m_idx;
		this.m_email = m_email;
		this.m_password = m_password;
		this.m_nm = m_nm;
		this.role = role;
		this.m_photo = m_photo;
		this.m_blacklist = m_blacklist;
		this.m_regdate = m_regdate;
		this.m_quitdate = m_quitdate;
	}
	public String getM_idx() {
		return m_idx;
	}
	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_nm() {
		return m_nm;
	}
	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getM_photo() {
		return m_photo;
	}
	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}
	public String getM_blacklist() {
		return m_blacklist;
	}
	public void setM_blacklist(String m_blacklist) {
		this.m_blacklist = m_blacklist;
	}
	public String getM_regdate() {
		return m_regdate;
	}
	public void setM_regdate(String m_regdate) {
		this.m_regdate = m_regdate;
	}
	public String getM_quitdate() {
		return m_quitdate;
	}
	public void setM_quitdate(String m_quitdate) {
		this.m_quitdate = m_quitdate;
	}
	
	
	



	
	

	
	

}
