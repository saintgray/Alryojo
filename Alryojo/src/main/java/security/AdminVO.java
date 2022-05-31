package security;

public class AdminVO {
	
	private String admin_idx;
	private String admin_email;
	private String admin_password;
	private String admin_nm;
	private String role;
	private String photo;
	
	
	public AdminVO() {
		
	}


	public AdminVO(String admin_idx, String admin_email, String admin_password, String admin_nm, String role,
			String photo) {
		this.admin_idx = admin_idx;
		this.admin_email = admin_email;
		this.admin_password = admin_password;
		this.admin_nm = admin_nm;
		this.role = role;
		this.photo = photo;
	}


	public String getAdmin_idx() {
		return admin_idx;
	}


	public void setAdmin_idx(String admin_idx) {
		this.admin_idx = admin_idx;
	}


	public String getAdmin_email() {
		return admin_email;
	}


	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}


	public String getAdmin_password() {
		return admin_password;
	}


	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}


	public String getAdmin_nm() {
		return admin_nm;
	}


	public void setAdmin_nm(String admin_nm) {
		this.admin_nm = admin_nm;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}



	
	
	
	
	
	
	
	
	

}
