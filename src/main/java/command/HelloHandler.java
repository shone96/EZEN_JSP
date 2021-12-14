package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloHandler implements CommandHandler{
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("hello", "안녕하세요!");//뷰 페이지에서 사용할 정보 저장
		return "/view/hello.jsp";// 뷰 페이지의 URI 리턴
	}
}
