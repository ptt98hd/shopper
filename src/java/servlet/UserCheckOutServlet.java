package servlet;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.OrderStatus;
import entity.ProductSize;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserCheckOutServlet", urlPatterns = {"/checkout"})
public class UserCheckOutServlet extends HttpServlet {

	private final OrderDao orderDao = new OrderDao();
	private final OrderDetailDao orderDetailDao = new OrderDetailDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductSize, Integer> cart = (Map<ProductSize, Integer>) session.getAttribute("cart");
		if (cart == null) cart = new HashMap<>();

		if (cart.isEmpty()) {
			session.setAttribute("message", "Giỏ hàng trống");
			response.sendRedirect("/shopper/cart");
			return;
		}

		if (session.getAttribute("account") == null) {
			session.setAttribute("message", "Đăng nhập để tiến hành đặt hàng");
			response.sendRedirect("/shopper/account");
			return;
		}

		int total = 0;
		for (Map.Entry<ProductSize, Integer> entry : cart.entrySet()) {
			ProductSize key = entry.getKey();
			Integer value = entry.getValue();
			total += key.getProduct().getPrice() * value;
		}
		request.setAttribute("total", total);

		request.getRequestDispatcher("/user/checkout.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message;

		Map<ProductSize, Integer> cart = (Map<ProductSize, Integer>) session.getAttribute("cart");
		if (cart == null || cart.isEmpty()) {
			message = "Giỏ hàng trống";
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/cart");
			return;
		}

		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			message = "Đăng nhập để tiến hành đặt hàng";
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/account");
			return;
		}

		String consignee = request.getParameter("consignee");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		if (consignee.isBlank() || phone.isBlank() || address.isBlank()) {
			message = "Form đặt hàng chưa được điền hoàn chỉnh";
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/checkout");
			return;
		}

		List<OrderDetail> orderDetails = new ArrayList<>();
		double total = 0;

		for (ProductSize productSize : cart.keySet()) {
			int quantity = cart.get(productSize);

			double subTotal = productSize.getProduct().getPrice() * quantity;
			total += subTotal;

			OrderDetail orderDetail = new OrderDetail(0, 0, productSize, quantity, subTotal);
			orderDetails.add(orderDetail);
		}

		Order order = new Order(0, consignee, phone, address, total, account, OrderStatus.PENDING);
		int orderId = orderDao.insertOrder(order);
		if (orderId == 0) {
			message = "Đặt hàng không thành công";
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/cart");
			return;
		}

		for (OrderDetail orderDetail : orderDetails) {
			orderDetail.setOrderId(orderId);
			int rowAffected = orderDetailDao.insertOrderDetail(orderDetail);

			if (rowAffected == 0) {
				orderDetailDao.deleteOrderDetails(orderId);
				orderDao.deleteOrder(orderId);
				message = "Đặt hàng không thành công";
				session.setAttribute("message", message);
				response.sendRedirect("/shopper/cart");
				return;
			}
		}

		cart.clear();
		session.setAttribute("cart", cart);
		message = "Đặt hàng thành công";
		session.setAttribute("message", message);
		response.sendRedirect("/shopper/order");
	}
}
