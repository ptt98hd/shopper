package servlet;

import dao.SizeDao;
import entity.Size;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminSizeServlet", urlPatterns = {"/admin/size"})
public class AdminSizeServlet extends HttpServlet {

	private final SizeDao sizeDao = new SizeDao();

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
			default -> response.sendRedirect("/shopper/admin/size");
		}
	}

//	Get List
	private void getList(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Size> sizes = sizeDao.getSizes();
		request.setAttribute("sizes", sizes);
		request.getRequestDispatcher("/admin/size.jsp").forward(request, response);
	}

//	Get Insert
	private void getInsert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getRequestDispatcher("/admin/sizeInsert.jsp").forward(request, response);
	}

//	Get Update
	private void getUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			int sizeId = Integer.parseInt(request.getParameter("sizeId"));
			Size size = sizeDao.getSize(sizeId);
			if (size == null) throw new Exception();
			request.setAttribute("size", size);

			request.getRequestDispatcher("/admin/sizeUpdate.jsp").forward(request, response);
		} catch (Exception e) {
			session.setAttribute("message", "Size không khả dụng");
			response.sendRedirect("/shopper/admin/size");
		}
	}

//	POST
//	===================================================================================================================
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String submit = request.getParameter("submit");

		switch (submit) {
			case "insert" -> insert(request, response);
			case "update" -> update(request, response);
			case "delete" -> delete(request, response);
			default -> response.sendRedirect("/shopper/admin/size");
		}
	}

//	Insert
	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			short sizeEu = Short.parseShort(request.getParameter("sizeEu"));
			Size size = new Size(0, sizeEu);
			if (sizeDao.insertSize(size) == 0) throw new Exception();
			message = "Thêm size thành công";
		} catch (Exception e) {
			message = "Thêm size không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/size");
		}
	}

//	Update
	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int sizeId = Integer.parseInt(request.getParameter("sizeId"));
			short sizeEu = Short.parseShort(request.getParameter("sizeEu"));
			Size size = new Size(0, sizeEu);
			if (sizeDao.updateSize(sizeId, size) == 0) throw new Exception();
			message = "Cập nhật size thành công";
		} catch (Exception e) {
			message = "Cập nhật size không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/size");
		}
	}

//	Delete
	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int sizeId = Integer.parseInt(request.getParameter("sizeId"));
			if (sizeDao.deleteSize(sizeId) == 0) {
				throw new Exception();
			}
			message = "Xoá size thành công";
		} catch (Exception e) {
			message = "Xoá size không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/size");
		}
	}
}
