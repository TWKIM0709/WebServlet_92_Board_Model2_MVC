package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class ReplyDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg = "";
		String url = "";
		String idx_fk = "";
		try {
			BoardDao dao = new BoardDao();
			idx_fk=request.getParameter("idx");//댓글의 원본 게시글 번호
			String no = request.getParameter("no");//댓글의 순번(PK)
			String pwd = request.getParameter("delPwd");//댓글의 암호
			System.out.println(idx_fk + "/" + no + "/" + pwd + "/");
			
			if(idx_fk == null || no == null || pwd == null || no.trim().equals(""))
			{
				request.setAttribute("idk", idx_fk);
				request.setAttribute("board_msg","데이터 누락");
				request.setAttribute("board_url", "content.do");
				forward.setPath("/board/redirect.jsp");
				forward.setRedirect(false);
				return forward;
			}
			int result = dao.replyDelete(no, pwd);

		    if(result > 0){
		    	msg ="댓글 삭제 성공";
		    	url ="content.do?idx="+idx_fk;
		    }
		} catch (Exception e) {
	    	msg="댓글 삭제 실패";
	    	url="content.do?idx="+idx_fk;
		}
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url", url);
		forward.setPath("/board/redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
