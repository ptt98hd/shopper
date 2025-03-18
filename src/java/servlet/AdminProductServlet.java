package servlet;

import dao.BrandDao;
import dao.CategoryDao;
import dao.ColorDao;
import dao.ProductDao;
import dao.ProductSizeDao;
import dao.SizeDao;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Product;
import entity.ProductSize;
import entity.Size;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "AdminProductServlet", urlPatterns = {"/admin/product"})
public class AdminProductServlet extends HttpServlet {

	private final ProductDao productDao = new ProductDao();
	private final ProductSizeDao productSizeDao = new ProductSizeDao();
	private final CategoryDao categoryDao = new CategoryDao();
	private final BrandDao brandDao = new BrandDao();
	private final ColorDao colorDao = new ColorDao();
	private final SizeDao sizeDao = new SizeDao();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);

		List<Brand> brands = brandDao.getBrands();
		request.setAttribute("brands", brands);

		List<Color> colors = colorDao.getAllColors();
		request.setAttribute("colors", colors);
	}

//	GET
//	===================================================================================================================
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String get = request.getParameter("get");

		if (get == null) getList(request, response);
		else switch (get) {
			case "info" -> getInfo(request, response);
			case "insert" -> getInsert(request, response);
			case "update" -> getUpdate(request, response);
			default -> response.sendRedirect("/shopper/admin/product");
		}
	}

//	Get List
	private void getList(HttpServletRequest request, HttpServletResponse response)
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
		request.getRequestDispatcher("/admin/product.jsp").forward(request, response);
	}

//	Get Information
	private void getInfo(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			List<Category> categories = categoryDao.getAllCategories();
			request.setAttribute("categories", categories);

			List<Brand> brands = brandDao.getBrands();
			request.setAttribute("brands", brands);

			List<Color> colors = colorDao.getAllColors();
			request.setAttribute("colors", colors);

			int productId = Integer.parseInt(request.getParameter("productId"));
			Product product = productDao.getProduct(productId);
			if (product == null) throw new Exception();
			request.setAttribute("product", product);

			List<Size> sizeList = sizeDao.getSizes();
			List<ProductSize> productSizes = productSizeDao.getProductSizes(productId);

			Map<Size, Boolean> sizes = new TreeMap<>((s1, s2) -> Short.compare(s1.getSizeEu(), s2.getSizeEu()));
			for (Size size : sizeList) {
				sizes.put(size, false);
			}
			for (ProductSize productSize : productSizes) {
				sizes.put(productSize.getSize(), true);
			}

			request.setAttribute("sizes", sizes);
		} catch (Exception e) {
			message = "Không tìm thấy product";
			session.setAttribute("message", message);
			response.sendRedirect(message);
		}

		request.getRequestDispatcher("/admin/productInfo.jsp").forward(request, response);
	}

//	Get Insert Form
	private void getInsert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);

		List<Brand> brands = brandDao.getBrands();
		request.setAttribute("brands", brands);

		List<Color> colors = colorDao.getAllColors();
		request.setAttribute("colors", colors);

		List<Size> sizes = sizeDao.getSizes();
		request.setAttribute("sizes", sizes);

		request.getRequestDispatcher("/admin/productInsert.jsp").forward(request, response);
	}

//	Get Update Form
	private void getUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);

		List<Brand> brands = brandDao.getBrands();
		request.setAttribute("brands", brands);

		List<Color> colors = colorDao.getAllColors();
		request.setAttribute("colors", colors);

		String productIdStr = request.getParameter("productId");
		int productId = Integer.parseInt(productIdStr);

		Product product = productDao.getProduct(productId);
		request.setAttribute("product", product);

		List<Size> sizeList = sizeDao.getSizes();
		List<ProductSize> productSizes = productSizeDao.getProductSizes(productId);
		Map<Size, Boolean> sizes = new TreeMap<>((s1, s2) -> Short.compare(s1.getSizeEu(), s2.getSizeEu()));
		for (Size size : sizeList) {
			sizes.put(size, false);
		}
		for (ProductSize productSize : productSizes) {
			sizes.put(productSize.getSize(), true);
		}
		request.setAttribute("sizes", sizes);
		request.getRequestDispatcher("/admin/productUpdate.jsp").forward(request, response);
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
			default -> response.sendRedirect("/shopper/admin/product");
		}
	}

//	Insert Product
	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			Product product = getProductRequest(request);
			int productId = productDao.insertProduct(product);
			product.setProductId(productId);
			if (productId == 0) throw new Exception();

			String[] sizeIds = request.getParameterValues("sizeIds");

			if (sizeIds != null) {
				for (String sizeId : sizeIds) {
					Size size = new Size(Integer.parseInt(sizeId), (short) 0);
					ProductSize productSize = new ProductSize(0, product, size);
					productSizeDao.insertProductSize(productSize);
				}
			}

			message = "Thêm sản phẩm thành công";
		} catch (Exception e) {
			message = "Thêm sản phẩm không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/product");
		}

	}

//	Update Product
	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			Product product = getProductRequest(request);
			productDao.updateProduct(productId, product);
			productSizeDao.deleteProductSizes(productId);
			String[] sizeIds = request.getParameterValues("sizeIds");

			if (sizeIds != null) {
				for (String sizeId : sizeIds) {
					ProductSize productSize = new ProductSize(0,
						new Product(productId, "", "", 0, null, null, null),
						new Size(Integer.parseInt(sizeId), (short) 0));
					productSizeDao.insertProductSize(productSize);
				}
			}

			message = "Cập nhật sản phẩm thành công";
		} catch (Exception e) {
			message = "Cập nhật sản phẩm không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/product");
		}
	}

	private Product getProductRequest(HttpServletRequest request)
		throws Exception {

		String productName = request.getParameter("productName");
		String productImg = request.getParameter("productImg");
		double price = Double.parseDouble(request.getParameter("price"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		int colorId = Integer.parseInt(request.getParameter("colorId"));

		Color color = new Color(colorId, "", "");
		Category category = new Category(categoryId, "", "");
		Brand brand = new Brand(brandId, "", "");

		return new Product(0, productName, productImg, price, category, brand, color);
	}

//	Delete Product
	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		String message = "";
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			if (productDao.deleteProduct(productId) == 0) throw new Exception();
			message = "Xoá sản phẩm thành công";
		} catch (Exception e) {
			message = "Xoá sản phẩm không thành công";
		} finally {
			session.setAttribute("message", message);
			response.sendRedirect("/shopper/admin/product");
		}
	}
}
