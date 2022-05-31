package com.alj.dream.admin.domain;

public class Admin {
	
	private String admin_nm;
	private String photo;
	private String admin_email;
	private String admin_password;
	
	public Admin() {
		
	}

	public Admin(String admin_nm, String photo, String admin_email, String admin_password) {
		this.admin_nm = admin_nm;
		this.photo = photo;
		this.admin_email = admin_email;
		this.admin_password = admin_password;
	}

	public String getAdmin_nm() {
		return admin_nm;
	}

	public void setAdmin_nm(String admin_nm) {
		this.admin_nm = admin_nm;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
	
	
	
	
	

}
