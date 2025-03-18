package servlet;

import dao.OrderDetailDao;
import entity.OrderDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "UserOrderDetailServlet", urlPatterns = {"/order/detail"})
public class UserOrderDetailServlet extends HttpServlet {

	private final OrderDetailDao orderDetailDao = new OrderDetailDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("account") == null) {
			session.setAttribute("message", "Đăng nhập để xem đơn hàng");
			response.sendRedirect("/shopper/account");
			return;
		}

		try {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			List<OrderDetail> orderDetails = orderDetailDao.getOrderDetails(orderId);
			if (orderDetails.isEmpty()) {
				throw new Exception();
			}
			request.setAttribute("orderDetails", orderDetails);
			request.getRequestDispatcher("/user/orderDetail.jsp").forward(request, response);
		} catch (Exception e) {
			session.setAttribute("message", "Mã đơn hàng không hợp lệ");
			response.sendRedirect("/shopper/order");
		}
	}
}
