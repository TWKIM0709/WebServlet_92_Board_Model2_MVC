package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class WriteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg = "";
		String url="";
		try {
			BoardDao dao = new BoardDao();
			Board board = new Board();
			board.setSubject(request.getParameter("subject"));
			board.setWriter(request.getParameter("writer"));
			board.setEmail(request.getParameter("email"));
			board.setHomepage(request.getParameter("homepage"));
			board.setContent(request.getParameter("content"));
			board.setPwd(request.getParameter("pwd"));
			board.setFilename(request.getParameter("filename"));
			
			int result = dao.writeok(board);
			
			if(result > 0) {
				msg = "insert success";
				url = "list.do";
			} else {
				msg = "insert fail";
				url = "/board/board_write.jsp";
			}
		} catch (Exception e) {
			msg = "insert fail";
			url = "/board/board_write.jsp";
		}
		
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		forward.setPath("/board/redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
