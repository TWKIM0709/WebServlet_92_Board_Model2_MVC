package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class EditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BoardDao dao = new BoardDao();
			String idx = request.getParameter("idx");
			if(idx == null || idx.trim().equals("")){
				request.setAttribute("board_msg","글번호 오류");
				request.setAttribute("board_url", "list.do");
				forward.setPath("/board/redirect.jsp");
				forward.setRedirect(false);
				return forward;
			}
			Board board = dao.getContent(Integer.parseInt(idx));
			if(board == null) {
				request.setAttribute("board_msg","데이터 오류. 목록가기");
				request.setAttribute("board_url", "list.do");
				forward.setPath("/board/redirect.jsp");
				forward.setRedirect(false);
				return forward;
			}
			//성공시
			request.setAttribute("idx", idx);
			request.setAttribute("board", board);
			forward.setPath("/board/board_edit.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			request.setAttribute("board_msg","edit read error");
			request.setAttribute("board_url", "list.do");
			forward.setPath("/board/redirect.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
