package jp.example;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HelloServletTest {
	
	@ParameterizedTest
	@CsvSource(delimiter = '|', textBlock = """
		# パラメーター	| 期待値	| テスト内容
		#----------------------------------------------------
						| World		| null（パラメーター無し）
		''				| ''		| 空文字
		' '				| ' '		| 空白
		' all '			| ' all '	| 両端空白
		Test ①			| Test ①	| マルチバイト
		""")
	void doGet(String param, String expected) throws IOException, ServletException {
		var req = mock(HttpServletRequest.class);
		var res = mock(HttpServletResponse.class);
		var dsp = mock(RequestDispatcher.class);
		doReturn(dsp).when(req).getRequestDispatcher(any());
		doReturn(param).when(req).getParameter("name");
		
		new HelloServlet().doGet(req, res);
		verify(req).setAttribute("message", expected);
		verify(req).getRequestDispatcher("/WEB-INF/view/hello.jsp");
		verify(dsp).forward(req, res);
	}
}
