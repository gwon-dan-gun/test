package com.icia.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.project.dao.CommentDAO;
import com.icia.project.dto.CommentDTO;

@Service
public class CommentService {
	@Autowired
	public CommentDAO commentDAO;
	

	public List<CommentDTO> commentWrite(CommentDTO comment) {
		int writeResult = commentDAO.commentWrite(comment);
		List<CommentDTO> clist = new ArrayList<CommentDTO>();
		if (writeResult > 0) {
			clist = commentDAO.commentList(comment.getCbnumber());
		} else {
			clist = null;
		}

		return clist;
		
	}

	


}
