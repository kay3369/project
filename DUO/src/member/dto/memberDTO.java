package member.dto;

import java.sql.Date;

public class memberDTO {
	private String id;
	private String pwd;
	private String nick_name;
	private String email;
	private String address;
	private String gender;
	private String birth;
	private String phone;
	private int point;
	private String photo;
	private int admin;
	
	//setter, getter, toString
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "memberDTO [id=" + id + ", pwd=" + pwd + ", nick_name=" + nick_name + ", email=" + email + ", address="
				+ address + ", gender=" + gender + ", birth=" + birth + ", phone=" + phone + ", point=" + point
				+ ", photo=" + photo + ", admin=" + admin + "]";
	}


	
	
}
