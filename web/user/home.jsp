<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Trang chủ</title>
		<%@include file="/WEB-INF/jspf/user/style.jspf" %>
	</head>
	<body>

		<div class="site-wrap">
			<%@include file="/WEB-INF/jspf/user/navbar.jspf"%>
			<%@include file="/WEB-INF/jspf/user/heroes.jspf" %>
			<%@include file="/WEB-INF/jspf/user/promotion.jspf" %>

			<!--Brand Session-->
			<div class="site-section site-blocks-2">
				<div class="container">
					<div class="row justify-content-center mb-3">
						<div class="col-md-7 site-section-heading text-center pt-4">
							<h2>Danh mục sản phẩm</h2>
						</div>
					</div>
					<div class="row align-items-center justify-content-center">
						<c:forEach var="category" items="${categories}">
							<div class="col-12 col-md-4 mb-4 mb-lg-0" data-aos="fade" data-aos-delay="">
								<a class="block-2-item" href="/shopper/product?categoryId=${category.categoryId}">
									<figure class="image">
										<img src="${category.categoryImg}" alt="${category.categoryName}" class="img-fluid">
									</figure>
									<div class="text">
										<span class="text-uppercase">Collection</span>
										<h3>${category.categoryName}</h3>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!--Brand Session-->

			<!--New Product-->
			<div class="site-section block-3 site-blocks-2 bg-light">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-7 site-section-heading text-center pt-4">
							<h2>Sản phẩm mới</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="nonloop-block-3 owl-carousel">
								<c:forEach var="product" items="${products}">
									<div class="item h-100">
										<div class="card block-4 h-100 text-center border-0 rounded-0">
											<figure class="card-img-top block-4-image mb-0">
												<a href="detail?productId=${product.productId}">
													<img src="${product.productImg}" alt="${product.productName}" class="card-img-top">
												</a>
											</figure>
											<div class="card-body block-4-text">
												<a href="detail?productId=${product.productId}"
												   class="h6 text-primary product-name mb-0">
													${product.productName}
												</a>
											</div>
											<div class="card-footer bg-white">
												<p class="mb-0">
													${product.category.categoryName} - ${product.brand.brandName}
												</p>
												<p class="text-primary mb-0">
													$ <fmt:formatNumber value="${product.price}"/>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--New Product-->

			<!--Brands-->
			<div class="site-section block-8">
				<div class="container">
					<div class="row justify-content-center  mb-5">
						<div class="col-md-7 site-section-heading text-center pt-4">
							<h2>Thương hiệu</h2>
						</div>
					</div>
					<div class="row justify-content-center">
						<c:forEach var="brand" items="${brands}">
							<div class="col-12 col-md-4 mb-4 mb-lg-0" data-aos="fade" data-aos-delay="">
								<a class="block-2-item" href="/shopper/product?brandId=${brand.brandId}">
									<figure class="image shadow">
										<img src="${brand.brandImg}" class="img-fluid">
									</figure>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!--Brands-->

			<%@include file="/WEB-INF/jspf/user/footer.jspf" %>
		</div>
		<%@include file="/WEB-INF/jspf/user/message.jspf"%>
		<%@include file="/WEB-INF/jspf/user/script.jspf" %>
	</body>
</html>
