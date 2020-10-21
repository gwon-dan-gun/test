package com.icia.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.project.api.KakaoJoinApi;
import com.icia.project.api.KakaoLoginApi;
import com.icia.project.api.NaverJoinApi;
import com.icia.project.api.NaverLoginApi;
import com.icia.project.dto.MemberDTO;
import com.icia.project.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoJoinApi kakaoJoinApi;
	
	@Autowired
	private KakaoLoginApi kakaoLoginApi;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private NaverLoginApi naverLoginApi;
	
	private ModelAndView mav;
	
	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	@RequestMapping(value="/membermain")
	public String membermain() {
		return "memberv/MemberMain";
		
	}
	@RequestMapping(value="/memberjoinform")
	public String joinForm() {
		return "memberv/MemberJoin";
	}
	@RequestMapping(value="/memberloginform")
	public String loginForm() {
		return "memberv/MemberLogin";
	}
	//회원가입
	@RequestMapping(value="/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		System.out.println(member.toString());
		mav = memberService.memberJoin(member);
		
		return mav;
	}
	//로그인
	@RequestMapping(value="/memberlogin")
	public ModelAndView memberLogin(@ModelAttribute MemberDTO member) {
		mav = memberService.memberLogin(member);
		return mav;
	}
	//회원목록
	@RequestMapping(value="/memberlist")
	public ModelAndView memberList() {
		mav=memberService.memberList();
		return mav;
		
	}
	//상세조회
	@RequestMapping(value="/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid) {
		mav=memberService.memberView(mid);
		return mav;
		
	}
	//회원삭제
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav=memberService.memberDelete(mid);
		return mav;
		
	}
	//회원정보수정
	@RequestMapping(value="/memberupdate")
	public ModelAndView memberUpdate() {
		mav = memberService.memberUpdate();
		return mav;
	}
	
	// 수정 처리
	@RequestMapping(value="/memberupdateprocess")
	public ModelAndView memberUpdateProcess(@ModelAttribute MemberDTO member) {
		mav = memberService.memberUpdateProcess(member);
		return mav;
	}

	// 아이디 중복확인
	@RequestMapping(value="/idoverlap")
	public @ResponseBody String idOverlap(@RequestParam("mid") String mid) {
		System.out.println("전달받은 값"+mid);
		String resultMsg = memberService.idOverlap(mid);
		return resultMsg;
	}
	
	// ajax 이용한 상세조회 
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("전달받은 값"+mid);
		MemberDTO memberView = memberService.memberViewAjax(mid);
		return memberView;
	}
	
	// 로그아웃
	@RequestMapping(value="/memberlogout")
	public String memberLogout() {
		session.invalidate();
		return "memberv/MemberLogin";
	}
	// 카카오 서버 로그인 
		@RequestMapping(value="/kakaojoin")
		public ModelAndView kakaoJoin(HttpSession session) {
			String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("kakaoUrl", kakaoUrl);
			mav.setViewName("KakaoLogin");
			return mav;
		}
		
		// 카카오 서버 인증 통과 후 처리 
		@RequestMapping(value="/kakaojoinok")
		public ModelAndView kakaoJoinOK(@RequestParam("code") String code,
				HttpSession session) {
			JsonNode token = kakaoJoinApi.getAccessToken(code);
			JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
			System.out.println("profile "+profile);
			// profile에 담긴 id 값을 가지고 MemberJoin.jsp로 이동 
			String kakaoId = profile.get("id").asText();
			mav = new ModelAndView();
			mav.addObject("kakaoId", kakaoId);
			mav.setViewName("memberv/MemberJoin");
			return mav;
		}
		
		@RequestMapping(value="/kakaologin")
		public ModelAndView kakaoLogin(HttpSession session) {
			String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("kakaoUrl", kakaoUrl);
			mav.setViewName("KakaoLogin");
			return mav;
		}
		
		@RequestMapping(value="/kakaologinok")
		public ModelAndView kakaoLoginOK(@RequestParam("code") String code) {
			JsonNode token = kakaoLoginApi.getAccessToken(code);
			JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
			
			mav = memberService.kakaoLogin(profile); 
			return mav;
		}

		@RequestMapping(value="/naverjoin")
		public ModelAndView naverJoin(HttpSession session) {
			String naverUrl = naverJoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("naverUrl", naverUrl);
			mav.setViewName("NaverLogin");
			return mav;
		}
		
		@RequestMapping(value="/naverjoinok")
		public ModelAndView naverJoinOK(@RequestParam("code") String code,
				@RequestParam("state") String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
			mav = new ModelAndView();
			OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
			String profile = naverJoinApi.getUserProfile(oauthToken);
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(profile);
			
			JSONObject naverUser = (JSONObject)obj;
			JSONObject userInfo = (JSONObject)naverUser.get("response");
			String naverId = (String) userInfo.get("id");
			
			mav.addObject("naverId", naverId);
			mav.setViewName("memberv/MemberJoin");
			
			return mav;
		}
		
		@RequestMapping(value="/naverlogin")
		public ModelAndView naverLogin(HttpSession session) {
			String naverUrl = naverLoginApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("naverUrl", naverUrl);
			mav.setViewName("NaverLogin");
			return mav;
		}
		
		@RequestMapping(value="/naverloginok")
		public ModelAndView naverLoginOK(@RequestParam("code") String code,
				@RequestParam("state") String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
			OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
			String profile = naverJoinApi.getUserProfile(oauthToken);
			mav = memberService.naverLogin(profile);
			return mav;
			
		}
	
	
	
}
