package servlet;

import dao.BrandDao;
import entity.Brand;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminBrandServlet", urlPatterns = {"/admin/brand"})
public class AdminBrandServlet extends HttpServlet {

	private final BrandDao brandDao = new BrandDao();

//	GET
//	===================================================================================================================
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String get = request.getParameter("get");
		if (get == null) getProducts(request, response);
		else switch (get) {
			case "insert" -> getInsert(request, response);
			case "update" -> getUpdate(request, response);
			default -> response.sendRedirect("/shopper/admin/brand");
		}
	}

//	Get List Product
	private void getProducts(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Brand> brands = brandDao.getBrands();
		request.setAttribute("brands", brands);
		request.getRequestDispatcher("/admin/brand.jsp").forward(request, response);
	}

//	Get Update Form
	private void getUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String brandId = request.getParameter("brandId");
		Brand brand = brandDao.getBrand(Integer.parseInt(brandId));
		request.setAttribute("brand", brand);
		request.getRequestDispatcher("/admin/brandUpdate.jsp").forward(request, response);
	}

//	Get Insert Form
	private void getInsert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getRequestDispatcher("/admin/brandInsert.jsp").forward(request, response);
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
			default -> response.sendRedirect("/shopper/admin/brand");
		}
	}

//	Insert Brand
	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";

		try {
			String brandName = request.getParameter("brandName");
			String brandImg = request.getParameter("brandImg");
			if (brandName.isBlank() || brandImg.isBlank()) throw new Exception();

			Brand brand = new Brand(0, brandName, brandImg);
			if (brandDao.insertBrand(brand) == 0) throw new Exception();

			message = "Thêm brand thành công";
		} catch (Exception e) {
			message = "Thêm brand không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/brand");
		}
	}

//	Update Brand
	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";

		try {
			String brandIdStr = request.getParameter("brandId");
			String brandName = request.getParameter("brandName");
			String brandImg = request.getParameter("brandImg");
			if (brandName.isBlank() || brandImg.isBlank() || brandImg.isBlank()) throw new Exception();

			int brandId = Integer.parseInt(brandIdStr);

			Brand brand = new Brand(0, brandName, brandImg);

			if (brandDao.updateBrand(brandId, brand) == 0) throw new Exception();
			else message = "Cập nhật brand thành công";
		} catch (Exception e) {
			message = "Cập nhật brand không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/brand");
		}
	}

//	Delete Brand
	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		HttpSession session = request.getSession();
		String message = "";
		try {
			String brandIdStr = request.getParameter("brandId");
			int brandId = Integer.parseInt(brandIdStr);
			if (brandDao.deleteBrand(brandId) == 0) throw new Exception();
			else message = "Xoá brand thành công";
		} catch (Exception e) {
			message = "Xoá brand không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/brand");
		}
	}
}
