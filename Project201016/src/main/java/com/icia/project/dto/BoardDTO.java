package com.icia.project.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	private int bnumber;
	private String bwriter;
	private int bpassword;
	private String btitle;
	private String bcontents;
	private Date bdate;
	private int bhits;
	

	private MultipartFile bfile;
	private String bfilename;
	


	public BoardDTO(int bnumber, String bwriter, int bpassword, String btitle, String bcontents, Date bdate, int bhits,
			MultipartFile bfile, String bfilename) {
		super();
		this.bnumber = bnumber;
		this.bwriter = bwriter;
		this.bpassword = bpassword;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.bdate = bdate;
		this.bhits = bhits;
		this.bfile = bfile;
		this.bfilename = bfilename;
	}



	@Override
	public String toString() {
		return "BoardDTO [bnumber=" + bnumber + ", bwriter=" + bwriter + ", bpassword=" + bpassword + ", btitle="
				+ btitle + ", bcontents=" + bcontents + ", bdate=" + bdate + ", bhits=" + bhits + ", bfile=" + bfile
				+ ", bfilename=" + bfilename + "]";
	}



	public int getBnumber() {
		return bnumber;
	}



	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
	}



	public String getBwriter() {
		return bwriter;
	}



	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}



	public int getBpassword() {
		return bpassword;
	}



	public void setBpassword(int bpassword) {
		this.bpassword = bpassword;
	}



	public String getBtitle() {
		return btitle;
	}



	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}



	public String getBcontents() {
		return bcontents;
	}



	public void setBcontents(String bcontents) {
		this.bcontents = bcontents;
	}



	public Date getBdate() {
		return bdate;
	}



	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}



	public int getBhits() {
		return bhits;
	}



	public void setBhits(int bhits) {
		this.bhits = bhits;
	}



	public MultipartFile getBfile() {
		return bfile;
	}



	public void setBfile(MultipartFile bfile) {
		this.bfile = bfile;
	}



	public String getBfilename() {
		return bfilename;
	}



	public void setBfilename(String bfilename) {
		this.bfilename = bfilename;
	}



	public BoardDTO() {
		super();
	}
	
	

}
