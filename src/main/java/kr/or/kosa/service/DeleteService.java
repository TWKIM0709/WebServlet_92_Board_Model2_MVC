package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class DeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			String idx = request.getParameter("idx");
			if(idx == null || idx.equals("")) {
				request.setAttribute("board_msg", "글 번호가 넘어오지 않았습니다.");
				request.setAttribute("board_url", "list.do");
				forward.setPath("/board/redirect.jsp");
				forward.setRedirect(false);
			}
			
			request.setAttribute("idx", idx);
			forward.setPath("/board/board_delete.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			request.setAttribute("board_msg", "delete read error.");
			request.setAttribute("board_url", "list.do");
			forward.setPath("/board/redirect.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
