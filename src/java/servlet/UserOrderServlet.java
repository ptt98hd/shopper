package servlet;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Account;
import entity.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "UserOrderServlet", urlPatterns = {"/order"})
public class UserOrderServlet extends HttpServlet {

	private final OrderDao orderDao = new OrderDao();
	private final OrderDetailDao orderDetailDao = new OrderDetailDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			session.setAttribute("message", "Đăng nhập để xem tình trạng đơn hàng");
			response.sendRedirect("/shopper/account");
			return;
		}

		List<Order> orders = orderDao.getOrders(account.getAccountId());

		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/user/order.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int orderId = Integer.parseInt(request.getParameter("orderId"));

			if (orderDetailDao.deleteOrderDetails(orderId) == 0) {
				throw new Exception();
			}
			if (orderDao.deleteOrder(orderId) == 0) {
				throw new Exception();
			}

			message = "Huỷ đơn hàng thành công";
		} catch (Exception e) {
			message = "Huỷ đơn hàng không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/order");
		}
	}
}
