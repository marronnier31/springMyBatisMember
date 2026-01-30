package com.zeus.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.domain.Member;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@MapperScan(basePackages = "com.zeus.mapper")
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/insertForm")
	public String memberInsertForm(Model model) {
		return "member/insertForm";
	}
	
	@PostMapping("/insert")
	public String memberInsert(Member member, Model model) {
		log.info("insert board = " + member.toString());
		try {
			int count = memberService.register(member);
			if(count > 0 ) {
				model.addAttribute("message", "%s 님의 등록이 완료되었습니다.".formatted(member.getName()));
				return"member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		model.addAttribute("message", "%s 님의 등록이 실패되었습니다.".formatted(member.getName()));
		return "member/failed";
	}
	/*
	@GetMapping("/boardList")
	public String boaradList(Model model) {
		log.info("boaradList");
		try {
			List<Board> boardList = boardService.list();
			model.addAttribute("boardList", boardList);
		} catch (Exception e) {e.printStackTrace();}
		return "board/boardList";
	}
	@GetMapping("/detail")
	public String boaradList(Board b,Model model) {
		log.info("boaradDetail board = " + b.toString());
		try {
			Board board = boardService.read(b);
			if(board == null) {
				model.addAttribute("message", "%d 님의 상세정보를 찾는데 실패했습니다.".formatted(b.getNo()));
				return "board/failed";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {e.printStackTrace();}
		return "board/detail";
	}
	@GetMapping("/delete")
	public String boaradDelete(Board b,Model model) {
		log.info("boaradDelete board = " + b.toString());
		try {
			int count = boardService.delete(b);
			if(count > 0) {
				model.addAttribute("message", "%d 님의 정보가 삭제되었습니다.".formatted(b.getNo()));
				return "board/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%d 님의 정보가 삭제 실패되었습니다.".formatted(b.getNo()));
		return "board/failed";
	}
	@GetMapping("/updateForm")
	public String boaradUpdateForm(Board b,Model model) {
		log.info("boaradUpdateForm board = " + b.toString());
		try {
			Board board = boardService.read(b);
			if(board == null) {
				model.addAttribute("message", "%d 님의 정보가 없습니다.".formatted(b.getNo()));
				return "board/failed";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {e.printStackTrace();}
		return "board/updateForm";
	}
	@PostMapping("/update")
	public String boaradUpdate(Board board, Model model) {
		
		log.info("boaradUpdate board = " + board.toString());
		try {
			int count = boardService.update(board);
			if(count > 0) {
				model.addAttribute("message", "%s 님의 게시글 수정 성공입니다.".formatted(board.getWriter()));
				return "board/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s 님의 게시글 수정이 실패되었습니다.".formatted(board.getWriter()));
		return "board/failed";
	}
	@GetMapping("/search")
	public String search(Board board, Model model) {
		log.info("boaradSearch  = "+ board.toString());
		try {
			List<Board> boardList = boardService.search(board);
			model.addAttribute("boardList", boardList);
		} catch (Exception e) {e.printStackTrace();}
		return "board/boardList";
	}
	*/
}
