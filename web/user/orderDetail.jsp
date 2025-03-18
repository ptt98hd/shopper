<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Đơn hàng</title>
		<%@include file="/WEB-INF/jspf/user/style.jspf" %>
	</head>
	<body>

		<div class="site-wrap">
			<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>

			<div class="bg-light py-3">
				<div class="container">
					<div class="row">
						<div class="col-md-12 mb-0">
							<a href="/shopper/home">Trang chủ</a>
							<span class="mx-2 mb-0">/</span>
							<a href="/shopper/order">Đơn hàng</a>
							<span class="mx-2 mb-0">/</span>
							<strong class="text-black">Chi tiết đơn hàng</strong></div>
					</div>
				</div>
			</div>

			<div class="site-section">
				<div action="cart" method="post" class="container">
					<div class="row mb-0">
						<div class="col-md-12">
							<div class="site-blocks-table">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th class="product-thumbnail">Hình ảnh</th>
											<th class="">Sản phẩm</th>
											<th class="product-price">Giá</th>
											<th class="product-quantity">Số lượng</th>
											<th class="product-total text-nowrap">Thành tiền</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="orderDetail" items="${orderDetails}">
											<tr>
												<td class="product-thumbnail">
													<img src="${orderDetail.productSize.product.productImg}" alt="Image" class="img-fluid">
												</td>
												<td class="text-left">
													<h2 class="h4 text-black">${orderDetail.productSize.product.productName}</h2>
													<p><b>Kích cỡ:</b> ${orderDetail.productSize.size.sizeEu} EU</p>
												</td>
												<td class="text-nowrap">
													$ <fmt:formatNumber value="${orderDetail.productSize.product.price}" groupingUsed="true"/>
												</td>
												<td>
													${orderDetail.quantity}
												</td>
												<td>
													$ <fmt:formatNumber value="${orderDetail.total}" groupingUsed="true"/>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
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
