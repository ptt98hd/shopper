<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <h1 class="mt-4 mb-4">Product</h1>
                        <div class="card mb-4">
                            <form action="product" method="post" class="p-5 bg-body-tertiary">
								<input type="hidden" name="productId" value="${product.productId}">
								<div class="mb-3">
									<label for="productName" class="form-label">Product Name</label>
									<input type="text" name="productName" value="${product.productName}" required
										   class="form-control" id="productName">
								</div>
								<div class="mb-3">
									<label for="productImg" class="form-label">Image Link</label>
									<input type="text" name="productImg" value="${product.productImg}" required
										   class="form-control" id="productImg">
								</div>
								<div class="mb-3">
									<label for="price" class="form-label">Price</label>
									<input type="number" name="price" value="<fmt:formatNumber value="${product.price}" groupingUsed="false"/>" required
										   class="form-control" id="price">
								</div>
								<div class="mb-3">
									<label for="categoryId" class="form-label">Category</label>
									<select id="categoryId" name="categoryId" class="form-select">
										<c:forEach var="category" items="${categories}">
											<option value="${category.categoryId}"
													${category.categoryId == product.category.categoryId ? "selected" : ""}>
												${category.categoryName}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="mb-3">
									<label for="brandId" class="form-label">Brand</label>
									<select id="brandId" name="brandId" class="form-select">
										<c:forEach var="brand" items="${brands}">
											<option value="${brand.brandId}"
													${brand.brandId == product.brand.brandId ? "selected" : ""}>
												${brand.brandName}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="mb-3">
									<label for="colorId" class="form-label">Color</label>
									<select id="colorId" name="colorId" class="form-select">
										<c:forEach var="color" items="${colors}">
											<option value="${color.colorId}"
													${color.colorId == product.color.colorId ? "selected" : ""}>
												${color.colorName}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="mb-3">
									<c:forEach var="size" items="${sizes}">
										<div class="form-check-inline">
											<input type="checkbox" name="sizeIds" value="${size.key.sizeId}" ${size.value ? "checked" : ""}
												   id="size${size.key.sizeId}" class="form-check-input">
											<label for="size${size.key.sizeId}" class="form-check-label">
												${size.key.sizeEu}
											</label>
										</div>
									</c:forEach>
								</div>
								<button type="submit" name="submit" value="update"
										class="btn btn-success w-100 mt-3">Update
								</button>
							</form>
                        </div>
                    </div>
                </main>

                <%@include file="/WEB-INF/jspf/admin/footer.jspf" %>
            </div>
        </div>
		<%@include file="/WEB-INF/jspf/admin/message.jspf" %>
		<%@include file="/WEB-INF/jspf/admin/script.jspf" %>
    </body>
</html>
