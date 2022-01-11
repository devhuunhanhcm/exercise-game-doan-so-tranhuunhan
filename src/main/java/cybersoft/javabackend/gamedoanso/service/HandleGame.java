package cybersoft.javabackend.gamedoanso.service;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.gamedoanso.UrlHelper.UrlHelper;
import cybersoft.javabackend.gamedoanso.model.Player;

@WebServlet(name = "handleGame",
			urlPatterns = {UrlHelper.HANDLE_GAME})
public class HandleGame extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(!session.isNew()) {
			resp.sendRedirect(req.getContextPath() + UrlHelper.HOME);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int result = 0;
		String name = (String) session.getAttribute("name");
		int playerNumber = Integer.parseInt(req.getParameter("number"));
		
		session.setAttribute("playerNumber", playerNumber);
		if(session.getAttribute("player") == null) {
			Player player = new Player(name,1);
			session.setAttribute("player", player);
		}
		
		if(session.getAttribute("result") == null) {
			result = ThreadLocalRandom.current().nextInt(1,1001);
			session.setAttribute("result", result);
		}
		req.getRequestDispatcher(UrlHelper.RESULT).forward(req, resp);
	}
}
