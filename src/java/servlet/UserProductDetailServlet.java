package servlet;

import dao.ProductSizeDao;
import dao.ProductDao;
import entity.Product;
import entity.ProductSize;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/detail"})
public class UserProductDetailServlet extends HttpServlet {

	private final ProductDao productDao = new ProductDao();
	private final ProductSizeDao productSizeDao = new ProductSizeDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));

			Product product = productDao.getProduct(productId);
			if (product == null) throw new Exception();
			request.setAttribute("product", product);

			List<ProductSize> productSizes = productSizeDao.getProductSizes(productId);
			request.setAttribute("productSizes", productSizes);

			List<Product> products = productDao.getSimilarProducts(product);
			request.setAttribute("products", products);

			request.getRequestDispatcher("/user/productDetail.jsp").forward(request, response);
		} catch (Exception e) {
			session.setAttribute("message", "Mã sản phẩm không hợp lệ");
			response.sendRedirect("/shopper/product");
		}
	}
}
