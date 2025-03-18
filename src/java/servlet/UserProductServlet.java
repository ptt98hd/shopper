package servlet;

import dao.BrandDao;
import dao.CategoryDao;
import dao.ColorDao;
import dao.ProductDao;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "UserProductServlet", urlPatterns = {"/product"})
public class UserProductServlet extends HttpServlet {

	private final ProductDao productDao = new ProductDao();
	private final CategoryDao categoryDao = new CategoryDao();
	private final BrandDao brandDao = new BrandDao();
	private final ColorDao colorDao = new ColorDao();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);

		List<Brand> brands = brandDao.getBrands();
		request.setAttribute("brands", brands);

		List<Color> colors = colorDao.getAllColors();
		request.setAttribute("colors", colors);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		processRequest(request, response);

		String categoryIdStr = request.getParameter("categoryId");
		int categoryId = categoryIdStr == null ? 0 : Integer.parseInt(categoryIdStr);
		request.setAttribute("categoryId", categoryId);

		String brandIdStr = request.getParameter("brandId");
		int brandId = brandIdStr == null ? 0 : Integer.parseInt(brandIdStr);
		request.setAttribute("brandId", brandId);

		String colorIdStr = request.getParameter("colorId");
		int colorId = colorIdStr == null ? 0 : Integer.parseInt(colorIdStr);
		request.setAttribute("colorId", colorId);

		String pageStr = request.getParameter("page");
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		request.setAttribute("page", page);

		String productName = request.getParameter("productName");
		if (productName == null) productName = "";
		request.setAttribute("productName", productName);

		String sortBy = request.getParameter("sort");
		if (sortBy == null) sortBy = "productId";
		request.setAttribute("sort", sortBy);

		int pages = productDao.countPages(false, categoryId, brandId, colorId, productName);
		request.setAttribute("pages", pages);

		List<Product> products = productDao.getProducts(false, categoryId, brandId, colorId, productName, sortBy, page);

		request.setAttribute("products", products);

		String get = request.getParameter("get");
		if (get == null) {
			request.getRequestDispatcher("/user/product.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/user/_product.jsp").forward(request, response);
		}
	}
}
