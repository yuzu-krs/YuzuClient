package jp.example;

import static java.util.Objects.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("message", requireNonNullElse(req.getParameter("name"), "World"));
		req.getRequestDispatcher("/WEB-INF/view/hello.jsp").forward(req, res);
	}
}
