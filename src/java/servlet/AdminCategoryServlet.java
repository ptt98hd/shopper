package servlet;

import dao.CategoryDao;
import entity.Category;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/admin/category"})
public class AdminCategoryServlet extends HttpServlet {

	private final CategoryDao categoryDao = new CategoryDao();

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
			default -> response.sendRedirect("/shopper/admin/category");
		}
	}

//	Get List
	private void getList(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
	}

//	Get Insert Form
	private void getInsert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.getRequestDispatcher("/admin/categoryInsert.jsp").forward(request, response);
	}

//	Get Update Form
	private void getUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		Category category = new CategoryDao().getCategory(Integer.parseInt(categoryId));
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/categoryUpdate.jsp").forward(request, response);
	}

//	POST
//	===================================================================================================================
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String submit = request.getParameter("submit");

		switch (submit) {
			case "insert" -> createCategory(request, response);
			case "delete" -> deleteCategory(request, response);
			case "update" -> updateCategory(request, response);
			default -> response.sendRedirect("/shopper/admin/category");
		}
	}

//	Create
	private void createCategory(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			String categoryName = request.getParameter("categoryName");
			String categoryImg = request.getParameter("categoryImg");
			if (categoryName.isBlank() || categoryImg.isBlank()) {
				throw new Exception();
			}

			Category category = new Category(0, categoryName, categoryImg);
			if (categoryDao.insertCategory(category) == 0) {
				throw new Exception();
			}

			message = "Thêm category thành công";
		} catch (Exception e) {
			message = "Thêm category không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/category");
		}
	}

//	Update
	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			String categoryIdStr = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");
			String categoryImg = request.getParameter("categoryImg");
			if (categoryName.isBlank() || categoryImg.isBlank()) {
				throw new Exception();
			}

			int categoryId = Integer.parseInt(categoryIdStr);
			Category category = new Category(0, categoryName, categoryImg);
			if (categoryDao.updateCategory(categoryId, category) == 0) {
				throw new Exception();
			}

			message = "Cập nhật category thành công";
		} catch (Exception e) {
			message = "Cập nhật category không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/category");
		}
	}

//	Delete
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		HttpSession session = request.getSession();
		String message = "";
		try {
			String categoryIdStr = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(categoryIdStr);
			if (categoryDao.deleteCategory(categoryId) == 0) {
				throw new Exception();
			}
			message = "Xoá category thành công";
		} catch (Exception e) {
			message = "Xoá category không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/category");
		}
	}
}
