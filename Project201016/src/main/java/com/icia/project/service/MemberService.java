package com.icia.project.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dao.MemberDAO;
import com.icia.project.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private HttpSession session;

	private ModelAndView mav;
//회원가입 서비스
	public ModelAndView memberJoin(MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile =member.getMfile();
		String mfilename =mfile.getOriginalFilename();
		mfilename =System.currentTimeMillis()+"_"+mfilename;
		String savePath="D:\\source\\spring2\\Project201016\\src\\main\\webapp\\resources\\uploadfile\\"+mfilename;
		
		if(!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}
		member.setMfilename(mfilename);
	
		int joinResult = memberDAO.memberJoin(member);
		if (joinResult > 0) {
			mav.setViewName("memberv/MemberLogin");
		} else {
			mav.setViewName("memberv/MemberJoinFail");
		}
		return mav;

	}
//로그인서비스
	public ModelAndView memberLogin(MemberDTO member) {
		mav = new ModelAndView();

		String loginId = memberDAO.memberLogin(member);
		if (loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("memberv/MemberMain");
		} else {
			mav.setViewName("memberv/LoginFail");
		}
		return mav;
	}
//회원목록서비스
	public ModelAndView memberList() {
		mav=new ModelAndView();
		List<MemberDTO> memberList = memberDAO.memberList();
		mav.addObject("memberList",memberList);
		mav.setViewName("memberv/MemberList");
		return mav;
	}
//회원상세조회서비스
	public ModelAndView memberView(String mid) {
		mav= new ModelAndView();
		MemberDTO memberView = memberDAO.memberView(mid);
		mav.addObject("memberView",memberView);
		mav.setViewName("memberv/MemberView");
		return mav;
	}
//회원 삭제서비스
	public ModelAndView memberDelete(String mid) {
		mav= new ModelAndView();
		int deleteResult =memberDAO.memberDelete(mid);
		if(deleteResult >0 ) {
			mav.setViewName("redirect:/memberlist");
		}else {
			mav.setViewName("memberv/MemberDeleteFail");
		}
		
		return mav;
	}
//
	public String idOverlap(String mid) {
		String checkResult = memberDAO.idOverlap(mid);
		String resultMsg = null;
		if(checkResult == null) {
			resultMsg = "OK";
		}else {
			resultMsg = "NO";
		}
		return resultMsg;
	}

	public MemberDTO memberViewAjax(String mid) {
		MemberDTO memberView = memberDAO.memberView(mid);
		return memberView;
	}

	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		String loginId = memberDAO.kakaoLogin(kakaoId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("memberv/MemberMain");
		return mav;
	}


	public ModelAndView naverLogin(String profile) throws ParseException {
		mav = new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String)userInfo.get("id");
		String loginId = memberDAO.naverLogin(naverId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("memberv/MemberMain");
		return mav;
	}
//회원정보수정
	public ModelAndView memberUpdate() {
		mav = new ModelAndView();
		String mid = (String)session.getAttribute("loginId");
		MemberDTO updateView = memberDAO.memberView(mid);
		mav.addObject("updateView", updateView);
		mav.setViewName("memberv/MemberUpdate");
		return mav;
	}
	
//수정처리
	public ModelAndView memberUpdateProcess(MemberDTO member) {
		mav = new ModelAndView();
		int updateResult = memberDAO.memberUpdate(member);
		if(updateResult > 0)
			mav.setViewName("memberv/MemberMain");
		else 
			mav.setViewName("memberv/MemberUpdateFail");
		return mav;
		
	}

}
