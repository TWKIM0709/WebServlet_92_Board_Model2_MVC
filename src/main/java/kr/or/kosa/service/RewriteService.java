package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class RewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BoardDao dao = new BoardDao();
			String idx = request.getParameter("idx");
			String cpage = request.getParameter("cp");
			String pagesize = request.getParameter("ps");
			String subject = request.getParameter("subject"); // 답글의 제목으로 사용
			
			if(idx == null || subject == null || idx.trim().equals("") || subject.trim().equals("")){
				request.setAttribute("board_msg","데이터 누락 발생");
				request.setAttribute("board_url", "list.do");
				forward.setPath("redirect.jsp");
				forward.setRedirect(false);
				return forward;
			}
			if(cpage == null || pagesize == null){
				cpage ="1";
				pagesize = "5";
			}
			request.setAttribute("idx", idx);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("subject", subject);
			forward.setPath("/board/board_rewrite.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			request.setAttribute("board_msg","rewrite error");
			request.setAttribute("board_url", "list.do");
			forward.setPath("/board/redirect.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
