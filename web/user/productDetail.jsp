<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Sản phẩm</title>
		<%@include file="/WEB-INF/jspf/user/style.jspf" %>
	</head>
	<body>

		<div class="site-wrap">
			<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>

			<!--Breadcrumbs-->
			<div class="bg-light py-3">
				<div class="container">
					<div class="row">
						<div class="col-md-12 mb-0">
							<a href="home">Trang chủ</a>
							<span class="mx-2 mb-0">/</span>
							<a href="product">Sản phẩm</a>
							<span class="mx-2 mb-0">/</span>
							<strong class="text-black">${product.productName}</strong>
						</div>
					</div>
				</div>
			</div>

			<!--Product Detail-->
			<div class="site-section">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							<img src="${product.productImg}" alt="Image" class="img-fluid">
						</div>
						<form action="cart" method="post" class="col-md-6">
							<h2 class="text-black">${product.productName}</h2>
							<hr>
							<div class="d-flex justify-content-between align-items-center my-3">
								<div>
									<p>
										<b>Mã sản phẩm: </b> ${product.productId}
									</p>
									<p>
										<b>Danh mục: </b>${product.category.categoryName}
									</p>
									<p>
										<b>Thương hiệu: </b>${product.brand.brandName}
									</p>
									<p class="mb-0">
										<b>Màu sắc: </b>${product.color.colorName}
									</p>
								</div>
								<img src="${product.brand.brandImg}" width="175px" class="border"/>
							</div>
							<hr>
							<p>
								<strong class="text-primary h4">
									$ <fmt:formatNumber value="${product.price}" groupingUsed="true"/>
								</strong>
							</p>
							<div class="mb-1 d-flex">
								<c:forEach var="productSize" items="${productSizes}" varStatus="status">
									<label for="productSize${productSize.productSizeId}" class="d-flex mr-3 mb-3">
										<span class="d-inline-block mr-2 ">
											<input type="radio" name="productSizeId" value="${productSize.productSizeId}"
												   id="productSize${productSize.productSizeId}"
												   ${status.index == 0 ? "checked" : ""}>
										</span>
										<span class="d-inline-block text-black">${productSize.size.sizeEu}</span>
									</label>
								</c:forEach>
							</div>
							<div class="mb-4">
								<div class="input-group mb-3" style="max-width: 120px;">
									<div class="input-group-prepend">
										<button class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
									</div>
									<input type="text" name="quantity" class="form-control text-center" min="1" value="1">
									<div class="input-group-append">
										<button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
									</div>
								</div>
							</div>
							<hr>
							<p class="mt-4">
								<button type="submit" name="submit" value="add"
										class="buy-now btn btn-sm btn-primary">
									Thêm vào giỏ hàng
								</button>
							</p>

						</form>
					</div>
				</div>
			</div>

			<div class="site-section block-3 site-blocks-2 bg-light">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-7 site-section-heading text-center pt-4">
							<h2>Sản phẩm liên quan</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<%@include file="/WEB-INF/jspf/user/suggestion.jspf" %>
						</div>
					</div>
				</div>
			</div>

			<%@include file="/WEB-INF/jspf/user/footer.jspf" %>
		</div>
		<%@include file="/WEB-INF/jspf/user/message.jspf"%>
		<%@include file="/WEB-INF/jspf/user/script.jspf" %>
	</body>
</html>
