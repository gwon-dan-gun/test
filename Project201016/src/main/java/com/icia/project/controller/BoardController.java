package com.icia.project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dto.BoardDTO;
import com.icia.project.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	private ModelAndView mav;

   //글쓰기
	@RequestMapping(value = "/boardwriteform")
	public String boardWriteForm() {
		return "boardv/BoardWrite";

	}
	//글쓰기처리
	@RequestMapping(value = "/boardwrite")
	public ModelAndView boardWrite(@ModelAttribute BoardDTO board) {
		mav = boardService.boardWrite(board);
		return mav;
	}
	@RequestMapping(value = "/boardlist")
	public ModelAndView boardList() {
		mav = boardService.boardList();
		return mav;

	}
	@RequestMapping(value = "/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,@RequestParam(value="page",required=false,defaultValue="1") int page) {
		mav = boardService.boardView(bnumber,page);
		return mav;

	}
	
	@RequestMapping(value="/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber")int bnumber) {
		mav= boardService.boardUpdate(bnumber);
		return mav;
		
	}
	@RequestMapping(value="/boardupdateprocess")
	public ModelAndView boardUpdateProcess(@ModelAttribute BoardDTO board) {
		mav=boardService.boardUpdateProcess(board);
		return mav;
		
	}
	@RequestMapping(value = "/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = boardService.boardDelete(bnumber);
		return mav;

	}
	@RequestMapping(value = "/boardwritefileform")
	public String boardWriteFileForm() {
		return "boardv/BoardWriteFile";

	}
	@RequestMapping(value="/boardwritefile")
	public ModelAndView boardWriteFile(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = boardService.boardWriteFile(board);
		return mav;

	}
	@RequestMapping("/boardlistpaging")
	public  ModelAndView boardListPaging(@RequestParam(value="page",required=false,defaultValue="1") int page) {
		mav = boardService.boardListPaging(page);
		return mav;
		
	}
	@RequestMapping("/boardsearch")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchtype,@RequestParam("keyword") String keyword) {
		mav=boardService.boardSearch(searchtype,keyword);
		return mav;
		
	}



}
