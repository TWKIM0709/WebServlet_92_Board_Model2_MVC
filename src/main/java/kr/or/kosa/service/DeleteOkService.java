package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class DeleteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg = "";
		String url = "";
		try {
			BoardDao dao = new BoardDao();
			String idx = request.getParameter("idx");
			String pwd = request.getParameter("pwd");
			
			int result = dao.deleteOk(idx, pwd);
			
			if(result > 0) {
				msg="delete success";
				url="list.do";
			}
		} catch (Exception e) {
			msg="delete fail";
			url="list.do";
		}
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url", url);
		forward.setPath("/board/redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
