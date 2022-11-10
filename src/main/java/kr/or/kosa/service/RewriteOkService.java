package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class RewriteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg="";
	    String url="";
	    Board board = null;
		try {
			BoardDao dao = new BoardDao();
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			String homepage = request.getParameter("homepage");
			String filename = request.getParameter("filename");
			String pwd = request.getParameter("pwd"); 
			String idx = request.getParameter("idx");
			
			String cpage = request.getParameter("cp"); //current page
			String pagesize = request.getParameter("ps"); //pagesize
			
			board = new Board();
			board.setIdx(Integer.parseInt(idx));
			board.setWriter(writer);
			board.setSubject(subject);
			board.setContent(content);
			board.setEmail(email);
			board.setHomepage(homepage);
			board.setFilename(filename);
			board.setPwd(pwd);
			
			System.out.print("board:");
			System.out.println(board);
			
			int result = dao.reWriteOk(board);
			System.out.println("result : " + result);
			
			request.setAttribute("board", board);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			
		    if(result > 0){
		    	msg ="rewrite insert success";
		    	url ="list.do";
		    } else {
		    	msg="rewrite insert fail";
		    	url="content.do?idx="+request.getParameter("idx");
		    }
		} catch (Exception e) {
	    	msg="rewrite insert fail";
	    	url="content.do?idx="+board.getIdx();
		}
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url", url);
		forward.setPath("/board/redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
