package com.icia.project.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.ToString;

@ToString
public class MemberDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private String mbirth;
	private String memail;
	private String maddress;
	private String mphone;
	private MultipartFile mfile;
	private String mfilename;
	private String kakaoId;
	private String naverId;

	

	public MemberDTO(String mid, String mpassword, String mname, String mbirth, String memail, String maddress,
			String mphone, MultipartFile mfile, String mfilename, String kakaoId, String naverId) {
		super();
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mbirth = mbirth;
		this.memail = memail;
		this.maddress = maddress;
		this.mphone = mphone;
		this.mfile = mfile;
		this.mfilename = mfilename;
		this.kakaoId = kakaoId;
		this.naverId = naverId;
	}



	@Override
	public String toString() {
		return "MemberDTO [mid=" + mid + ", mpassword=" + mpassword + ", mname=" + mname + ", mbirth=" + mbirth
				+ ", memail=" + memail + ", maddress=" + maddress + ", mphone=" + mphone + ", mfile=" + mfile
				+ ", mfilename=" + mfilename + ", kakaoId=" + kakaoId + ", naverId=" + naverId + "]";
	}



	public String getMid() {
		return mid;
	}



	public void setMid(String mid) {
		this.mid = mid;
	}



	public String getMpassword() {
		return mpassword;
	}



	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}



	public String getMname() {
		return mname;
	}



	public void setMname(String mname) {
		this.mname = mname;
	}



	public String getMbirth() {
		return mbirth;
	}



	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}



	public String getMemail() {
		return memail;
	}



	public void setMemail(String memail) {
		this.memail = memail;
	}



	public String getMaddress() {
		return maddress;
	}



	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}



	public String getMphone() {
		return mphone;
	}



	public void setMphone(String mphone) {
		this.mphone = mphone;
	}



	public MultipartFile getMfile() {
		return mfile;
	}



	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}



	public String getMfilename() {
		return mfilename;
	}



	public void setMfilename(String mfilename) {
		this.mfilename = mfilename;
	}



	public String getKakaoId() {
		return kakaoId;
	}



	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}



	public String getNaverId() {
		return naverId;
	}



	public void setNaverId(String naverId) {
		this.naverId = naverId;
	}



	public MemberDTO() {
		super();
	}

}
