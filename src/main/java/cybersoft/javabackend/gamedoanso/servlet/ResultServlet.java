package cybersoft.javabackend.gamedoanso.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.gamedoanso.UrlHelper.UrlHelper;
import cybersoft.javabackend.gamedoanso.model.GameRecord;
import cybersoft.javabackend.gamedoanso.model.Player;
import cybersoft.javabackend.gamedoanso.vendor.Vendor;

@WebServlet(name = "resultServlet",urlPatterns = {UrlHelper.RESULT})
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.append(Vendor.GETBOOTSTRAP);
		HttpSession session = req.getSession();
		
		boolean flag = true;
		if(flag && (session.getAttribute("result") == null  ||  session.getAttribute("playerNumber") == null || session.getAttribute("player") == null)) {
			flag = false;
		}
		if(flag) {
			boolean isFind = true;
			boolean isWinner = false;
			int result = (int) session.getAttribute("result");
			int playerNumber = (int) session.getAttribute("playerNumber");
			Player player = (Player) session.getAttribute("player");
			if(isFind && playerNumber > result) {
				out.append(" <form action=\""+req.getContextPath() + UrlHelper.HANDLE_GAME+"\" method=\"post\">\r\n"
						+ "		<div class=\"form-group\">\r\n"
						+ "          <h4 class=\"text-danger\"><i class=\"bi bi-exclamation-triangle\"></i> Số bạn vừa nhập lớn hơn đáp án</h4>\r\n"
						+ "          <label for=\"number\"><i class=\"bi bi-pencil\"></i> Chọn lại số khác: </label>\r\n"
						+ "          <input style=\"width: 100px;\" type=\"text\" class=\"form-control\" id=\"number\" name=\"number\" required placeholder=\"1 -> 1000\">\r\n"
						+ "      </div>\r\n"
						+ "      <button type=\"submit\" class=\"btn btn-primary\">Đoán ngay</button>"
						
						+ "   </form>");
				player.setTimesGuess(player.getTimesGuess() + 1);
				isFind = false;
			}
			if(isFind && playerNumber < result) {
				out.append(" <form action=\""+req.getContextPath() + UrlHelper.HANDLE_GAME+"\" method=\"post\">\r\n"
						+ "		<div class=\"form-group\">\r\n"
						+ "          <h4 class=\"text-danger\"><i class=\"bi bi-exclamation-triangle\"></i> Số bạn vừa nhập nhỏ hơn đáp án</h4>\r\n"
						+ "          <label for=\"number\"><i class=\"bi bi-pencil\"></i> Chọn lại số khác: </label>\r\n"
						+ "          <input style=\"width: 100px;\" type=\"text\" class=\"form-control\" id=\"number\" name=\"number\" required placeholder=\"1 -> 1000\">\r\n"
						+ "      </div>\r\n"
						+ "      <button type=\"submit\" class=\"btn btn-primary\">Đoán ngay</button>"
						
						+ "   </form>");
				player.setTimesGuess(player.getTimesGuess() + 1);
				isFind = false;
			}
			if(isFind && playerNumber == result) {
				out.append(" <div>\r\n"
						+ "        <h1 class=\"text-success\"><i class=\"bi bi-trophy\"></i> Chúc mừng bạn số bạn vừa đoán là đáp án chính xác!!</h1>\r\n"
						+ "<h2>Kết quả: "+ result +"</h2>"
						+ "<h2 class=\"mt-3\">Số lần đoán của bạn là: "
						+ player.getTimesGuess()
						+ "</h2>"
						+ "        <a class=\"btn btn-primary\" href=\""+req.getContextPath() + UrlHelper.HOME +"\">Quay trở lại trang chủ!</a>\r\n"
						+ "    </div>\r\n"
						+ "");
				isWinner = true;
			}
			if(isWinner) {
				if(session.getAttribute("playerList") != null) {
					List<Player> playerList = (List<Player>) session.getAttribute("playerList");
					playerList.add(player);
				}
				session.removeAttribute("result");
				session.removeAttribute("player");
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendRedirect(req.getContextPath() + UrlHelper.HOME);
		}
	}
}
