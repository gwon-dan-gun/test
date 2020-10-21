package com.icia.project.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dto.PageDTO;
import com.icia.project.dao.BoardDAO;
import com.icia.project.dto.BoardDTO;
@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	private ModelAndView mav;
	
	private static final int PAGE_LIMIT= 3;
	private static final int BLOCK_LIMIT= 5;

	public ModelAndView boardWrite(BoardDTO board) {
		mav= new ModelAndView();
		int writeResult=boardDAO.boardWrite(board);
		if(writeResult > 0){
			mav.setViewName("redirect:/boardlistpaging");
		}else {
			mav.setViewName("boardv/BoardWriteFail");
		}
		
		return mav;
	}

	public ModelAndView boardList() {
		mav = new ModelAndView();
		List<BoardDTO> boardList = boardDAO.boardList();
		mav.addObject("boardList", boardList);
		mav.setViewName("boardv/BoardList");
		return mav;
	}

	public ModelAndView boardView(int bnumber, int page) {
		mav=new ModelAndView();
		boardDAO.boaedHits(bnumber);
		BoardDTO boardView =boardDAO.boardView(bnumber);
		mav.addObject("boardView", boardView);
		mav.setViewName("boardv/BoardView");
		return mav;
	}

	public ModelAndView boardUpdate(int bnumber) {
		mav= new ModelAndView();
		BoardDTO updateView=boardDAO.boardView(bnumber);
		mav.addObject("updateView", updateView);
		mav.setViewName("boardv/BoardUpdate");
		return mav;
	}

	public ModelAndView boardUpdateProcess(BoardDTO board) {
		mav= new ModelAndView();
		int updateResult =boardDAO.boardUpdate(board);
		if(updateResult>0) {
			mav.setViewName("redirect:/boardview?bnumber=" + board.getBnumber());
		}else {
			mav.setViewName("boardv/BoardUpdateFail");
		}
		return mav;
	}

	public ModelAndView boardDelete(int bnumber) {
		mav= new ModelAndView();
		int deleteResult= boardDAO.boardDelete(bnumber);
		if(deleteResult > 0) {
			mav.setViewName("redirect:/BoardListPaging");
		}else {
			mav.setViewName("boardv/BoardDeleteFail");
		}
		return mav;
	}

	public ModelAndView boardWriteFile(BoardDTO board) throws IllegalStateException, IOException {
		mav= new ModelAndView();
		MultipartFile bfile =board.getBfile();
		String bfilename =bfile.getOriginalFilename();
		bfilename =System.currentTimeMillis()+"_"+bfilename;
		
		String savePath="D:\\source\\spring2\\Project201016\\src\\main\\webapp\\resources\\UploadFile"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int writeResult = boardDAO.boardWriteFile(board);
		if(writeResult>0) {
			mav.setViewName("redirect:/boardlistpaging");
		}else {
			mav.setViewName("boardv/boardWriteFileFail");
		}
		return mav;
		
	}

	public ModelAndView boardListPaging(int page) {
		mav= new ModelAndView();
		int listCount =boardDAO.listCount();
		int startRow =(page-1)*PAGE_LIMIT+1;
		int endRow=page*PAGE_LIMIT;
		
		PageDTO paging =new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = boardDAO.boardListPaging(paging);
		
		int maxPage =(int)(Math.ceil((double)listCount/PAGE_LIMIT));
		int startPage=(((int)(Math.ceil((double)page/BLOCK_LIMIT)))-1)*BLOCK_LIMIT+1;
		
		int endPage =startPage +BLOCK_LIMIT -1;
		if(endPage > maxPage) {
			endPage =maxPage;
		}
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging",paging);
		mav.addObject("boardList",boardList);
		mav.setViewName("boardv/BoardListPaging");		
		return mav;
	}

	public ModelAndView boardSearch(String searchtype, String keyword) {
		mav= new ModelAndView();
		List<BoardDTO> searchLsit =boardDAO.boardSearch(searchtype, keyword);
		mav.addObject("boardList",searchLsit);
		mav.setViewName("boardv/BoardListPaging");
		return mav;
	}
	

}
