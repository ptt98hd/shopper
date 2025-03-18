package servlet;

import dao.ColorDao;
import entity.Color;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminColorServlet", urlPatterns = {"/admin/color"})
public class AdminColorServlet extends HttpServlet {

	private final ColorDao colorDao = new ColorDao();

//	GET
//	===================================================================================================================
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String get = request.getParameter("get");
		if (get == null) getList(request, response);
		else switch (get) {
			case "insert" -> getInsert(request, response);
			case "update" -> getUpdate(request, response);
			default -> response.sendRedirect("/shopper/admin/color");
		}
	}

//	Get List
	private void getList(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Color> colors = colorDao.getAllColors();
		request.setAttribute("colors", colors);
		request.getRequestDispatcher("/admin/color.jsp").forward(request, response);
	}

	private void getInsert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.getRequestDispatcher("/admin/colorInsert.jsp").forward(request, response);
	}

	private void getUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String colorIdStr = request.getParameter("colorId");
		Color color = new ColorDao().getColor(Integer.parseInt(colorIdStr));
		request.setAttribute("color", color);
		request.getRequestDispatcher("/admin/colorUpdate.jsp").forward(request, response);
	}

//	POST
//	===================================================================================================================
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String submit = request.getParameter("submit");
		switch (submit) {
			case "insert" -> insert(request, response);
			case "delete" -> delete(request, response);
			case "update" -> update(request, response);
			default -> response.sendRedirect("/shopper/admin/color");
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			String colorName = request.getParameter("colorName");
			String hex = request.getParameter("hex");
			if (colorName.isBlank() || hex.isBlank()) {
				throw new Exception();
			}

			Color color = new Color(0, colorName, hex);
			if (colorDao.insertColor(color) == 0) {
				throw new Exception();
			}

			message = "Thêm color thành công";
		} catch (Exception e) {
			message = "Thêm color không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/color");
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int colorId = Integer.parseInt(request.getParameter("colorId"));
			String colorName = request.getParameter("colorName");
			String hex = request.getParameter("hex");
			if (colorName.isBlank() || hex.isBlank()) {
				throw new Exception();
			}

			Color color = new Color(0, colorName, hex);
			if (colorDao.updateColor(colorId, color) == 0) {
				throw new Exception();
			}

			message = "Cập nhật thành công!";
		} catch (Exception e) {
			message = "Cập nhật không thành công!";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/color");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int colorId = Integer.parseInt(request.getParameter("colorId"));
			if (colorDao.deleteColor(colorId) == 0) {
				throw new Exception();
			}
			message = "Xoá thành công";
		} catch (Exception e) {
			message = "Xoá không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/color");
		}
	}
}
