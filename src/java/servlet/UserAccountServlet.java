package servlet;

import dao.AccountDao;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "UserAccountServlet", urlPatterns = {"/account"})
public class UserAccountServlet extends HttpServlet {

	private final AccountDao accountDao = new AccountDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			request.getRequestDispatcher("/user/accountAuth.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/user/accountInfo.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String submit = request.getParameter("submit");

		switch (submit) {
			case "login" -> login(request, response);
			case "signup" -> signup(request, response);
			case "logout" -> logout(request, response);
			default -> response.sendRedirect("/shopper/account");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.isEmpty() || password.isEmpty()) {
				throw new Exception("Trường email và mật khẩu không được để trống!");
			}

			Account account = accountDao.userLogin(email, password);
			if (account == null) {
				throw new Exception("Sai địa chỉ email hoặc mật khẩu!");
			}

			session.setAttribute("account", account);

			session.setAttribute("message", "Đăng nhập thành công");
			response.sendRedirect("/shopper/account");
		} catch (Exception e) {
			session.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/user/accountAuth.jsp").forward(request, response);
		}
	}

	private void signup(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("rePassword");
			if (fullname.isBlank() || email.isBlank() || password.isBlank() || rePassword.isBlank()) {
				throw new Exception("Tất cả các trường thông tin cần được điền đầy đủ!");
			}

			if (!password.equals(rePassword)) {
				throw new Exception("Mật khẩu và Nhập lại mật khẩu phải giống nhau!");
			}

			Account account = new Account(0, fullname, email, password, false);

			if (accountDao.createUser(account) == 0) {
				throw new Exception("Địa chỉ email đã tồn tại!");
			}

			session.setAttribute("message", "Tạo tài khoản thành công");
			response.sendRedirect("/shopper/account");
		} catch (Exception e) {
			session.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/user/accountAuth.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.removeAttribute("account");
		response.sendRedirect("/shopper/account");
	}
}
