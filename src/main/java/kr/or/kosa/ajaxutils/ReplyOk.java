package kr.or.kosa.ajaxutils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Reply;

@WebServlet("/ReplyOk")
public class ReplyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyOk() {
        super();
    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String msg = "";
		String url = "";
		String idx_fk = "";
		int result = 0;
		try {
			BoardDao dao = new BoardDao();
			String writer = request.getParameter("reply_writer");
			String content = request.getParameter("reply_content");
			String pwd = request.getParameter("reply_pwd");
			idx_fk = request.getParameter("idx");
			String userid = "empty";
			result = dao.replywrite(Integer.parseInt(idx_fk), writer, userid, content, pwd);
			if(result > 0){
				List<Reply> replylist = dao.replylist(idx_fk);
				request.setAttribute("list", replylist);
				request.setAttribute("idx", idx_fk);
		    }
		} catch (Exception e) {
			result = 0;
			System.out.println(e);
			e.getStackTrace();
//	    	msg="댓글 입력 실패";
//	    	url="content.do?idx="+idx_fk;
		}
//		request.setAttribute("board_msg",msg);
//		request.setAttribute("board_url", url);
		request.setAttribute("result", result);
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/replytableview.jsp");
		dis.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
