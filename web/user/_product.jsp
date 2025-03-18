<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="site-section" id="productContainer">
	<div class="container">
		<div class="row" id="filterForm"
			 hx-get="product"
			 hx-trigger="change"
			 hx-target="#productContainer"
			 hx-swap="outerHTML"
			 hx-include="this"
			 hx-vars='{"get" : "list"}'>
			<div class="col-md-9 order-2">

				<!--List Products-->
				<div class="row mb-4">
					<c:forEach var="product" items="${products}">
						<div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
							<div class="card block-4 text-center h-100 border-0 rounded-0 shadow">
								<figure class="card-img-top block-4-image mb-0">
									<a href="detail?productId=${product.productId}">
										<img src="${product.productImg}" class="card-img-top">
									</a>
								</figure>
								<div class="card-body block-4-text">
									<a href="detail?productId=${product.productId}"
									   class="h6 text-primary product-name">
										${product.productName}
									</a>
								</div>
								<div class="card-footer bg-white">
									<p class="mb-0">
										${product.category.categoryName} - ${product.brand.brandName}
									</p>
									<p class="text-primary mb-0" style="font-weight: 500">
										$ <fmt:formatNumber value="${product.price}" groupingUsed="true"/>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!--List Products-->

				<!--Pagination-->
				<div class="row" data-aos="fade-up">
					<div class="col-md-12 text-center">
						<div class="site-block-27">
							<ul class="pagination d-flex align-items-center justify-content-center">
								<li class="page-item">
									<button type="button" name="page" value="${page - 1 == 0 ? 1 : page - 1}" class="page-link"
											hx-get="product" hx-trigger="click" hx-target="#productContainer" hx-swap="outerHTML"
											onclick="document.getElementById('breadcrumbs').scrollIntoView({behavior: 'smooth'});">
										&lt;
									</button>
								</li>
								<c:forEach begin="1" end="${pages}" varStatus="status">
									<li class="page-item ${page != status.index ? "d-none d-sm-block" : ""}">
										<button type="button" name="page" value="${status.index}" class="page-link
												${page == status.index ? "bg-primary text-white" : ""}"
												hx-get="product" hx-trigger="click" hx-target="#productContainer" hx-swap="outerHTML"
												onclick="document.getElementById('breadcrumbs').scrollIntoView({behavior: 'smooth'});">
											${status.index}
										</button>
									</li>
								</c:forEach>
								<li class="page-item">
									<button type="button" name="page" value="${page + 1 > pages ? page : page + 1}" class="page-link"
											hx-get="product" hx-trigger="click" hx-target="#productContainer" hx-swap="outerHTML"
											hx-include="input, select"
											onclick="document.getElementById('breadcrumbs').scrollIntoView({behavior: 'smooth'});">
										&gt;
									</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!--Pagination-->
			</div>

			<!--Filters-->
			<div class="col-md-3 order-1 mb-5 mb-md-0">

				<!--Filter Name-->
				<div class="border p-4 mb-4">
					<h3 class="mb-3 h6 text-uppercase text-black d-block">Tìm kiếm</h3>
					<hr>
					<input type="search" name="productName"value="${productName}"
						   class="form-control" placeholder="Tên sản phẩm">
				</div>
				<!--Filter Name-->

				<!--Sort By-->
				<div class="border p-4 mb-4">
					<h3 class="mb-3 h6 text-uppercase text-black d-block">Sắp xếp</h3>
					<hr>
					<select name="sort" class="custom-select">
						<option ${sort == 'productId' ? "selected" : ""} value="productId">Mặc định</option>
						<option ${sort == 'productId desc' ? "selected" : ""} value="productId desc">Mới nhất</option>
						<option ${sort == 'productName' ? "selected" : ""} value="productName" >Tên, A đến Z</option>
						<option ${sort == 'productName desc' ? "selected" : ""} value="productName desc">Tên, Z đến A</option>
						<option ${sort == 'price' ? "selected" : ""} value="price">Giá, tăng dần</option>
						<option ${sort == 'price desc' ? "selected" : ""} value="price desc">Giá, giảm dần</option>
					</select>
				</div>
				<!--Sort By-->

				<!--Filter Category-->
				<div class="border p-4 mb-4">
					<h3 class="mb-3 h6 text-uppercase text-black d-block">Danh mục</h3>
					<hr>
					<select name="categoryId" class="custom-select">
						<option value="0" ${categoryId == 0 ? "selected" : ""}>
							Tất cả
						</option>
						<c:forEach var="category" items="${categories}">
							<option value="${category.categoryId}" ${categoryId == category.categoryId ? "selected" : ""}>
								${category.categoryName}
							</option>
						</c:forEach>
					</select>
				</div>
				<!--Filter Category-->

				<!--Filter Brand-->
				<div class="border p-4 mb-4">
					<h3 class="mb-3 h6 text-uppercase text-black d-block">Thương hiệu</h3>
					<hr>
					<select name="brandId" class="custom-select">
						<option value="0" ${brandId == 0 ? "selected" : ""}>
							Tất cả
						</option>
						<c:forEach var="brand" items="${brands}">
							<option value="${brand.brandId}" ${brandId == brand.brandId ? "selected" : ""}>
								${brand.brandName}
							</option>
						</c:forEach>
					</select>
				</div>
				<!--Filter Brand-->

				<!--Filter Color-->
				<div class="border p-4 mb-4">
					<h3 class="mb-3 h6 text-uppercase text-black d-block">Màu sắc</h3>
					<hr>
					<select name="colorId" class="custom-select">
						<option value="0" ${colorId == 0 ? "selected" : ""}>
							Tất cả
						</option>
						<c:forEach var="color" items="${colors}">
							<option value="${color.colorId}" ${colorId == color.colorId ? "selected" : ""}>
								${color.colorName}
							</option>
						</c:forEach>
					</select>
				</div>
				<!--Filter Color-->
			</div>
			<!--Filters-->
		</div>
	</div>
</div>
