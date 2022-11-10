package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Reply;

public class ContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BoardDao dao = new BoardDao();
			//글번호 체크
			String idx = request.getParameter("idx");
			if(idx == null || idx.trim().equals("")){
				request.setAttribute("board_msg", "board content read error");
				request.setAttribute("board_url", "list.do");
				forward.setPath("redirect.jsp");
				forward.setRedirect(false);
				return forward;
			}
			idx = idx.trim();
			
			//이전 페이지 체크
			String cpage = request.getParameter("cp"); //current page
			String pagesize = request.getParameter("ps"); //pagesize
			
			//List 페이지 처음 호출 ...
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
		
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "5"; 
			}
			
			//글번호가 있으면 조회수를 증가시키고 request에 board 객체 넣기
			boolean isread = dao.getReadNum(idx);
			if(isread)System.out.println("조회증가 : " + isread);
			
			Board board = dao.getContent(Integer.parseInt(idx));
			
			List<Reply> replylist = dao.replylist(idx);
			
			request.setAttribute("replylist", replylist);
			request.setAttribute("board", board);
			request.setAttribute("idx", idx);
			
			forward.setPath("/board/board_content.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			request.setAttribute("board_msg", "board content read error");
			request.setAttribute("board_url", "list.do");
			forward.setPath("/board/redirect.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
