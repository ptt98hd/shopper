package servlet;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Order;
import entity.OrderDetail;
import entity.OrderStatus;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminOrderServlet", urlPatterns = {"/admin/order"})
public class AdminOrderServlet extends HttpServlet {

	private final OrderDao orderDao = new OrderDao();
	private final OrderDetailDao orderDetailDao = new OrderDetailDao();

//	GET
//	===================================================================================================================
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String get = request.getParameter("get");

		if (get == null) getList(request, response);
		else switch (get) {
			case "info" -> getInfo(request, response);
			default -> response.sendRedirect("/shopper/admin/order");
		}
	}

	private void getList(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Order> orders = orderDao.getOrders();
		request.setAttribute("orders", orders);

		OrderStatus[] statuses = OrderStatus.values();
		request.setAttribute("statuses", statuses);

		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}

	private void getInfo(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		int orderId = Integer.parseInt(request.getParameter("orderId"));

		Order order = orderDao.getOrder(orderId);
		request.setAttribute("order", order);

		List<OrderDetail> orderDetails = orderDetailDao.getOrderDetails(orderId);
		request.setAttribute("orderDetails", orderDetails);

		request.getRequestDispatcher("/admin/orderDetail.jsp").forward(request, response);
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
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message;

		int orderId = Integer.parseInt(request.getParameter("orderId"));

		int deleteOrderDetails = orderDetailDao.deleteOrderDetails(orderId);
		int deleteOrder = orderDao.deleteOrder(orderId);

		if (deleteOrderDetails < 1 || deleteOrder < 1) {
			message = "Xoá đơn hàng không thành công";
		} else {
			message = "Xoá đơn hàng thành công";
		}

		session.setAttribute("message", message);
		response.sendRedirect("/shopper/admin/order");

	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	}

	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message;

		int orderID = Integer.parseInt(request.getParameter("orderId"));
		String status = request.getParameter("status");

		if (orderDao.updateOrder(orderID, OrderStatus.getOrderStatus(status)) > 0) {
			message = "Cập nhật trạng thái đơn hàng thành công";
		} else {
			message = "Cập nhật trạng thái đơn hàng không thành công";
		}

		session.setAttribute("message", message);

		response.sendRedirect("/shopper/admin/order");
	}
}
