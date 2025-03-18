package servlet;

import dao.BrandDao;
import dao.CategoryDao;
import dao.ProductDao;
import entity.Brand;
import entity.Category;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "UserHomeServlet", urlPatterns = {"/home"})
public class UserHomeServlet extends HttpServlet {

	private final BrandDao brandDao = new BrandDao();
	private final CategoryDao categoryDao = new CategoryDao();
	private final ProductDao productDao = new ProductDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Brand> brands = brandDao.getBrands();
		request.setAttribute("brands", brands);

		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);

		List<Product> products = productDao.getNewProducts();
		request.setAttribute("products", products);

		request.getRequestDispatcher("/user/home.jsp").forward(request, response);
	}
}
