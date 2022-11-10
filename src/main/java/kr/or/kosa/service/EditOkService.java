package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class EditOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg = "";
		String url = "";
		String idx = "";
		try {
			BoardDao dao = new BoardDao();
			idx = request.getParameter("idx");
			
			if(idx == null || idx.equals("")) {
				request.setAttribute("board_msg","글번호입력오류");
				request.setAttribute("board_url", "list.do");
				forward.setPath("/board/redirect.jsp");
				forward.setRedirect(false);
				return forward;
			}
			int result = dao.boardEdit(request);
			
			if(result > 0){
				msg="edit success";
				url="content.do?idx=" + idx;
			}
		} catch (Exception e) {
			msg="edit fail";
			url="edit.do?idx="+idx;
		}
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		forward.setPath("/board/redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
