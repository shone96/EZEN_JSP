package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	//명령어 핸들러 클래스들은 process()메소드에서 알맞은 로직 코드를 실행한 후 결과를 보여줄 JSP의 URI를 리턴
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
