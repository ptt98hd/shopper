<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Product</title>
		<%@include file="/WEB-INF/jspf/admin/style.jspf" %>
    </head>
    <body class="sb-nav-fixed">
		<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>

        <div id="layoutSidenav">
			<%@include file="/WEB-INF/jspf/admin/sidebar.jspf" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="my-4">Product</h1>
						<form action="/shopper/admin/product" method="get" class="card mb-4">
                            <div class="card-header d-inline-flex align-items-center gap-2 w-100">
								<div>
									<a href="/shopper/admin/product?get=insert"
									   class="btn btn-success">
										Create
									</a>
								</div>
								<!--Filters-->
								<div class="d-inline-flex gap-2 w-100">
									<div class="input-group">
										<i class="bi bi-search input-group-text"></i>
										<input type="search" name="productName" value="${productName}"
											   placeholder="Tên sản phẩm"
											   class="form-control">
									</div>
									<div class="input-group">
										<i class="bi bi-tags input-group-text"></i>
										<select class="form-select" name="categoryId">
											<option value="0">Category</option>
											<c:forEach items="${categories}" var="category">
												<option value="${category.categoryId}" ${categoryId == category.categoryId ? "selected" : ""}>
													${category.categoryName}
												</option>
											</c:forEach>
										</select>
									</div>
									<div class="input-group">
										<i class="bi bi-patch-check input-group-text"></i>
										<select class="form-select" name="brandId">
											<option value="0">Brand</option>
											<c:forEach items="${brands}" var="brand">
												<option value="${brand.brandId}" ${brandId == brand.brandId ? "selected" : ""}>
													${brand.brandName}
												</option>
											</c:forEach>
										</select>
									</div>
									<div class="input-group">
										<i class="bi bi-eyedropper input-group-text"></i>
										<select class="form-select" name="colorId">
											<option value="0">Color</option>
											<c:forEach items="${colors}" var="color">
												<option value="${color.colorId}" ${colorId == color.colorId ? "selected" : ""}>
													${color.colorName}
												</option>
											</c:forEach>
										</select>
									</div>
									<div class="input-group">
										<i class="bi bi-filter-circle input-group-text"></i>
										<select class="form-select" name="sort">
											<option value="productId" ${sort == "productId" ? "selected" : ""}>
												Sort By
											</option>
											<option value="productId desc" ${sort == "productId desc" ? "selected" : ""}>
												Mới nhất
											</option>
											<option value="productName" ${sort == "productName" ? "selected" : ""}>
												Tên: a - z
											</option>
											<option value="productName desc" ${sort == "productName desc" ? "selected" : ""}>
												Tên: z - a
											</option>
											<option value="price" ${sort == "price" ? "selected" : ""}>
												Giá: tăng dần
											</option>
											<option value="price desc" ${sort == "price desc" ? "selected" : ""}>
												Giá: giảm dần
											</option>
										</select>
									</div>
									<button type="submit" name="submit" value="filter"
											class="btn btn-danger">
										Filter
									</button>
								</div>
								<!--Filters-->
                            </div>

                            <div class="card-body mb-0">
                                <table class="table table-bordered mb-0">
                                    <thead>
                                        <tr>
                                            <th>Image</th>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Category</th>
                                            <th>Brand</th>
                                            <th>Color</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="product" items="${products}">
											<tr>
												<td>
													<img src="${product.productImg}" height="75rem"/>
												</td>
												<td>${product.productId}</td>
												<td>${product.productName}</td>
												<td><fmt:formatNumber value="${product.price}" groupingUsed="true"/></td>
												<td>${product.category.categoryName}</td>
												<td>${product.brand.brandName}</td>
												<td>${product.color.colorName}</td>
												<td class="d-flex gap-2 flex-column">
													<a href="/shopper/admin/product?get=info&productId=${product.productId}"
													   class="btn btn-primary w-100">
														Info
													</a>
												</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>

							<!--Pagination-->
							<nav>
								<ul class="pagination justify-content-center">
									<li class="page-item">
										<button type="submit" name="page" value="${page > 1 ? (page - 1) : 1}"
												class="page-link">
											<i class="bi bi-arrow-left"></i>
										</button>
									</li>
									<c:forEach begin="1" end="${pages}" varStatus="status">
										<li class="page-item">
											<button type="submit" name="page" value="${status.index}"
													class="page-link ${page == status.index ? "active" : ""}">
												${status.index}
											</button>
										</li>
									</c:forEach>
									<li class="page-item">
										<button type="submit" name="page" value="${page + 1}"
												class="page-link">
											<i class="bi bi-arrow-right"></i>
										</button>
									</li>
								</ul>
							</nav>
							<!--Pagination-->
                        </form>
                    </div>
                </main>

                <%@include file="/WEB-INF/jspf/admin/footer.jspf" %>
            </div>
        </div>
		<%@include file="/WEB-INF/jspf/admin/message.jspf" %>
		<%@include file="/WEB-INF/jspf/admin/script.jspf" %>
    </body>
</html>
