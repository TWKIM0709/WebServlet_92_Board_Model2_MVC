package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class ReplyOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg = "";
		String url = "";
		String idx_fk = "";
		try {
			BoardDao dao = new BoardDao();
			String writer = request.getParameter("reply_writer");
			String content = request.getParameter("reply_content");
			String pwd = request.getParameter("reply_pwd");
			idx_fk = request.getParameter("idx");
			String userid = "empty";
			
			int result = dao.replywrite(Integer.parseInt(idx_fk), writer, userid, content, pwd);
			if(result > 0){
		    	msg ="댓글 입력 성공";
		    	url ="content.do?idx="+idx_fk;
		    }
		} catch (Exception e) {
	    	msg="댓글 입력 실패";
	    	url="content.do?idx="+idx_fk;
		}
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url", url);
		forward.setPath("/board/redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
