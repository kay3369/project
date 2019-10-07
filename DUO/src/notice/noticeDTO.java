package notice;

import java.sql.Date;

public class noticeDTO {
	private int n_no;
	private String n_title;
	private String n_detail;
	private String n_writer;
	private Date n_date;
	private int n_count;
	private int n_admin;
	private int rn; //가상 필드 값
	
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_detail() {
		return n_detail;
	}
	public void setN_detail(String n_detail) {
		this.n_detail = n_detail;
	}
	public String getN_writer() {
		return n_writer;
	}
	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}
	public Date getN_date() {
		return n_date;
	}
	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}
	public int getN_count() {
		return n_count;
	}
	public void setN_count(int n_count) {
		this.n_count = n_count;
	}
	public int getN_admin() {
		return n_admin;
	}
	public void setN_admin(int n_admin) {
		this.n_admin = n_admin;
	}
	@Override
	public String toString() {
		return "noticeDTO [n_no=" + n_no + ", n_title=" + n_title + ", n_detail=" + n_detail + ", n_writer=" + n_writer
				+ ", n_date=" + n_date + ", n_count=" + n_count + ", n_admin=" + n_admin + "]";
	}
	
	
}
