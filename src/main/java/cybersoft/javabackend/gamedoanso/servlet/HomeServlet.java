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

@WebServlet(name="gameService",urlPatterns = {UrlHelper.HOME})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameRecord gameRecord = new GameRecord();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.append(Vendor.GETBOOTSTRAP);
		List<Player> playerList = gameRecord.getPlayerList();
		req.getSession().setAttribute("playerList", playerList);
		
		out.append("<form method=\"post\" action=\"\">\r\n"
				+ "        <h1>Game đoán số</h1>\r\n"
				+ "          <div class=\"form-group\">\r\n"
				+ "            <label class=\"h4\" for=\"name\">Nhập tên của bạn</label>\r\n"
				+ "            <input  type=\"text\" required class=\"form-control\" id=\"name\" name=\"name\" style=\"width:300px\">\r\n"
				+ "          </div>\r\n"
				+ "          <button type=\"submit\" class=\"btn btn-primary\">Tiếp tục</button>\r\n"
				+ "      </form>\r\n");
		
		
		if(!playerList.isEmpty()) {
			
			out.append("<h3 class=\"mt-5\">Bảng xếp hạng</h3>"
					+ "<table class=\"table\">\r\n"
					+ "      <thead>\r\n"
					+ "        <tr>\r\n"
					+ "          <th scope=\"col\">Xếp hạng</th>\r\n"
					+ "          <th scope=\"col\">Tên</th>\r\n"
					+ "          <th scope=\"col\">Số lần đoán</th>\r\n"
					+ "        </tr>\r\n"
					+ "      </thead>\r\n"
					+ "      <tbody>\r\n");
			int i= 1;
			for (Player player : playerList) {
				out.append( "        <tr>\r\n"
						+ "          <th scope=\"row\">"+i +"</th>\r\n"
						+ "          <td>"+ player.getName()+ " </td>\r\n"
						+ "          <td>"+player.getTimesGuess() +"</td>\r\n"
						+ "        </tr>\r\n");
				i++;
			}
			out.append("	</tbody>\r\n"
					+ "		</table>");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		req.getSession().setAttribute("name", name);
		PrintWriter out = resp.getWriter();
		out.append(Vendor.GETBOOTSTRAP);
		out.append("<form  action=\""+ req.getContextPath() + UrlHelper.HANDLE_GAME+"\" method=\"post\" >\r\n"
				+ "      <h1>Hello : "+ name +" </h1>\r\n"
				+ "        <div class=\"form-group\">\r\n"
				+ "          <label class=\"h4\" for=\"number\">Hãy nhập số may mắn của bạn từ 1 -> 1000: </label>\r\n"
				+ "          <input style=\"width: 100px;\" type=\"text\" required  class=\"form-control\" id=\"number\" name=\"number\" placeholder=\"1 - 1000\">\r\n"
				+ "        </div>\r\n"
				+ "        <button type=\"submit\" class=\"btn btn-primary\">Đoán ngay</button>\r\n"
				+ "    </form>");
	}
}
