package servlet;

import dao.ProductSizeDao;
import entity.ProductSize;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "UserCartServlet", urlPatterns = {"/cart"})
public class UserCartServlet extends HttpServlet {

	private final ProductSizeDao productSizeDao = new ProductSizeDao();

//	GET
//	===================================================================================================================
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductSize, Integer> cart = (Map<ProductSize, Integer>) session.getAttribute("cart");
		if (cart == null) cart = new LinkedHashMap<>();

		double cartTotal = 0;
		for (Map.Entry<ProductSize, Integer> entry : cart.entrySet()) {
			ProductSize key = entry.getKey();
			Integer value = entry.getValue();
			cartTotal += value * key.getProduct().getPrice();
		}
		request.setAttribute("cartTotal", cartTotal);

		request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
	}

//	POST
//	===================================================================================================================
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String submit = request.getParameter("submit");
		switch (submit) {
			case "add" -> addCart(request, response);
			case "delete" -> deleteCart(request, response);
			case "update" -> updateCart(request, response);
			default -> response.sendRedirect("/shopper/cart");
		}
	}

	private void addCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		Map<ProductSize, Integer> cart = (Map<ProductSize, Integer>) session.getAttribute("cart");
		if (cart == null) cart = new LinkedHashMap<>();

		try {
			int productSizeId = Integer.parseInt(request.getParameter("productSizeId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			ProductSize productSize = productSizeDao.getProductSize(productSizeId);

			if (cart.containsKey(productSize)) {
				int value = cart.get(productSize);
				cart.replace(productSize, value + quantity);
			} else {
				cart.put(productSize, quantity);
			}

			session.setAttribute("cart", cart);
			session.setAttribute("message", "Thêm vào giỏ hàng thành công");
			response.sendRedirect("/shopper/detail?productId=" + productSize.getProduct().getProductId());
		} catch (IOException | NumberFormatException e) {
			session.setAttribute("message", "Thêm vào giỏ hàng không thành công");
			response.sendRedirect("/shopper/product");
		}

	}

	private void updateCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductSize, Integer> cart = (Map<ProductSize, Integer>) session.getAttribute("cart");
		if (cart == null) cart = new LinkedHashMap<>();

		int productSizeId = Integer.parseInt(request.getParameter("productSizeId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		ProductSize productSize = new ProductSize();
		productSize.setProductSizeId(productSizeId);

		if (quantity == 0) cart.remove(productSize);
		else cart.replace(productSize, quantity);

		session.setAttribute("cart", cart);
		request.getRequestDispatcher("/user/_cart.jsp").forward(request, response);
	}

	private void deleteCart(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();
		Map<ProductSize, Integer> cart = (Map<ProductSize, Integer>) session.getAttribute("cart");
		if (cart == null) cart = new LinkedHashMap<>();

		int productSizeId = Integer.parseInt(request.getParameter("productSizeId"));

		ProductSize productSize = new ProductSize();
		productSize.setProductSizeId(productSizeId);

		cart.remove(productSize);
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("/user/_cart.jsp").forward(request, response);
	}
}
