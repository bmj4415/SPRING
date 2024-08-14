package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;


@Controller
public class BoardController {
private BoardService boardService;

	@Autowired //생성자 주입을 할 생성자가 한개라면 굳이 @Autowired를 쓸 필요는 없음.
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list);
		return "board/boardList";
	}
	
	//어노테이션을 사용하지 않겠다는 부분 다시 복습하기
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	@GetMapping("boardInfo") //커맨드 객체 : QueryString
							 // ?bno=100
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boards", findVO);
		return "board/boardInfo";
	}
	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	
	@PostMapping("boardInsert") //일반적으로 등록하는 페이지의 형식이 <form/>형식을 많이 활용 => QueryString
	public String boardInsertProcess(BoardVO boardVO) {
		int no = boardService.boardInsert(boardVO);
		return "redirect:boardInfo?bno="+ no;
	}
	
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	
	@GetMapping("boardUpdate") //QueryString : ?bno=100
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		return "board/boardUpdate";
	}
	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map)
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateJson(@RequestBody BoardVO boardVO){
		return boardService.boardUpdate(boardVO);
	}

	
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출

	@GetMapping("boardDelete") // QueryString : ?no=100
	public String boardDelete(/*@RequestParam*/ Integer no) { //@RequestParam을 작성하면 반드시 no의 데이터가 넘어오는걸 가정해야함. 만약 넘어오지 않는 경우 stop되기 때문에.
		boardService.boardDelete(no);
		return "redirect:boardList";
	}
}
