package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.utils.ThePager;

public class ListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BoardDao dao = new BoardDao();
			int totalboardcount = dao.totalBoardCount();

			String ps = request.getParameter("ps"); //pagesize
			String cp = request.getParameter("cp"); //current page
			System.out.println("cp:"+cp);
			System.out.println("ps:"+ps);
			System.out.println(1);
			//List 페이지 처음 호출 ...
			if(ps == null || ps.trim().equals("") || ps.equals("null")){
				//default 값 설정
				ps = "5"; //5개씩 
			}
			System.out.println(2);
			
			if(cp == null || cp.trim().equals("") || cp.equals("null")){
				//default 값 설정
				cp = "1"; // 1번째 페이지 보겠다 
			}
			System.out.println(3);
			System.out.println("cp:"+cp);
			System.out.println("ps:"+ps);
			int pagesize = Integer.parseInt(ps);
			System.out.println(4);
			int cpage = Integer.parseInt(cp);
			System.out.println(5);
			int pagecount=0;
			System.out.println(totalboardcount);
			System.out.println(pagesize);
			if(totalboardcount % pagesize == 0){
				System.out.println(2);
				pagecount = totalboardcount / pagesize; //  20 << 100/5
			}else{
				System.out.println(3);
				pagecount = (totalboardcount / pagesize) + 1; 
			}
			System.out.println(1);
			List<Board> list = dao.list(cpage, pagesize);
			ThePager pager =  new ThePager(totalboardcount,cpage,pagesize,3,"list.do");
			System.out.println(1);
			
			request.setAttribute("pager", pager);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage",cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalboardcount);
			forward.setPath("/board/board_list.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			request.setAttribute("board_msg", "list read error");
			request.setAttribute("board_url", "/index.jsp");
			forward.setPath("/board/redirect.jsp");
			forward.setRedirect(true);
		}
		return forward;
	}

}
