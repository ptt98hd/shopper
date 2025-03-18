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

@WebServlet(name = "AdminAccountServlet", urlPatterns = {"/admin/account"})
public class AdminAccountServlet extends HttpServlet {

	private final AccountDao accountDao = new AccountDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account admin = (Account) session.getAttribute("admin");

		if (admin == null) {
			request.getRequestDispatcher("/admin/accountLogin.jsp").forward(request, response);
		} else {
			String get = request.getParameter("get");
			switch (get) {
				case "signup" -> request.getRequestDispatcher("accountSignup.jsp").forward(request, response);
				case "update" -> request.getRequestDispatcher("accountUpdate.jsp").forward(request, response);
				default -> response.sendRedirect("/shopper/admin/dashboard");
			}
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
			case "update" -> update(request, response);
			default -> response.sendRedirect("/shopper/admin/account");
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		String message;
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.isBlank() || password.isBlank()) {
				throw new Exception("Email và Password không được để trống");
			}

			Account account = accountDao.adminLogin(email, password);
			if (account == null) {
				throw new Exception("Sai email hoặc mật khẩu");
			}

			message = "Đăng nhập thành công";
			session.setAttribute("message", message);
			session.setAttribute("admin", account);
			response.sendRedirect("/shopper/admin/dashboard");
		} catch (Exception e) {
			message = e.getMessage();
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/account");
		}
	}

	protected void signup(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		String message;
		try {
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.isBlank() || password.isBlank() || fullname.isBlank()) {
				throw new Exception("Vui lòng điền toàn bộ form");
			}

			Account account = new Account(0, fullname, email, password, true);
			if (accountDao.createAdmin(account) == 0) {
				throw new Exception("Tạo admin không thành công");
			}

			message = "Tạo admin thành công";
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/dashboard");
		} catch (Exception e) {
			message = e.getMessage();
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/account?get=signup");
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		String message;
		try {
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			if (email.isBlank() || oldPassword.isBlank() || fullname.isBlank()) {
				throw new Exception("Vui lòng điền toàn bộ form");
			}
			if (newPassword.isBlank()) {
				newPassword = oldPassword;
			}

			Account account = accountDao.adminLogin(email, oldPassword);
			if (account == null) {
				throw new Exception("Mật khẩu cũ không đúng");
			}

			account.setFullname(fullname);
			account.setPassword(newPassword);
			if (accountDao.updateAccount(account.getAccountId(), account) == 0) {
				throw new Exception("Cập nhật admin không thành công");
			}

			message = "Cập nhật admin thành công";
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/dashboard");
		} catch (Exception e) {
			message = e.getMessage();
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/account?get=update");
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		session.setAttribute("message", "Đăng xuất thành công");
		response.sendRedirect("/shopper/admin/account");
	}
}
