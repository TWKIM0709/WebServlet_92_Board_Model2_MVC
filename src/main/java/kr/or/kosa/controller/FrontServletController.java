package kr.or.kosa.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.ContentService;
import kr.or.kosa.service.DeleteOkService;
import kr.or.kosa.service.DeleteService;
import kr.or.kosa.service.EditOkService;
import kr.or.kosa.service.EditService;
import kr.or.kosa.service.ListService;
import kr.or.kosa.service.ReplyDeleteOk;
import kr.or.kosa.service.ReplyOkService;
import kr.or.kosa.service.RewriteOkService;
import kr.or.kosa.service.RewriteService;
import kr.or.kosa.service.WriteOkService;

@WebServlet("*.do")
public class FrontServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontServletController() {
        super();
    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //데이터 받기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlcommand = requestURI.substring(contextPath.length());
        
        String viewpage = "";
        Action action = null;
        ActionForward forward = null;
        
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        
        // 로직 분리
        if(urlcommand.equals("/writeok.do")) {
        	action = new WriteOkService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/list.do")) {
        	action = new ListService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/content.do")) {
        	action = new ContentService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/delete.do")) {
        	action = new DeleteService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/deleteok.do")) {
        	action = new DeleteOkService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/edit.do")) {
        	action = new EditService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/editok.do")) {
        	action = new EditOkService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/replyok.do")) {
        	action = new ReplyOkService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/rewrite.do")) {
        	action = new RewriteService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/rewriteok.do")) {
        	action = new RewriteOkService();
        	forward = action.execute(request, response);
        } else if (urlcommand.equals("/write.do")) {
        	forward = new ActionForward();
        	forward.setPath("/board/board_write.jsp");
        	forward.setRedirect(false);
        } else if (urlcommand.equals("/replydeleteok.do")) {
        	action = new ReplyDeleteOk();
        	forward = action.execute(request, response);
        }

		if(forward != null) {
			if(forward.isRedirect()) { //redirect
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
