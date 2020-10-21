package com.icia.project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sql;

	public int memberJoin(MemberDTO member) {
		if (member.getKakaoId() != null)
			return sql.insert("Member.kakaoMemberJoin", member);
		else if (member.getNaverId() != null)
			return sql.insert("Member.naverMemberJoin", member);
		else
			return sql.insert("Member.memberJoin", member);
	}

	public String memberLogin(MemberDTO member) {
		return sql.selectOne("Member.memberLogin", member);
	}

	public List<MemberDTO> memberList() {
		return sql.selectList("Member.memberList");

	}

	public MemberDTO memberView(String mid) {
		return sql.selectOne("Member.memberView", mid);
	}

	public int memberDelete(String mid) {
		return sql.delete("Member.memberDelete", mid);
	}

	public String idOverlap(String mid) {

		return sql.selectOne("Member.idOverlap", mid);
	}

	public String kakaoLogin(String kakaoId) {

		return sql.selectOne("Member.kakaoLogin", kakaoId);
	}

	public String naverLogin(String naverId) {

		return sql.selectOne("Member.naverLogin", naverId);
	}

	public int memberUpdate(MemberDTO member) {
		
		 return sql.update("Member.memberUpdate", member);
	}

}
