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
							<a href="home">Trang chủ</a>
							<span class="mx-2 mb-0">/</span>
							<strong class="text-black">Đơn hàng</strong>
						</div>
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
											<th class="text-nowrap">ID</th>
											<th class="text-nowrap">Người nhận</th>
											<th class="text-nowrap">Số điện thoại</th>
											<th class="text-nowrap">Địa chỉ</th>
											<th class="text-nowrap">Thành tiền</th>
											<th class="text-nowrap">Trạng thái</th>
											<th class="text-nowrap">Hành động</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="order" items="${orders}">
											<tr>
												<td class="text-nowrap">
													${order.orderId}
												</td>
												<td class="text-nowrap">
													${order.consignee}
												</td>
												<td class="text-nowrap">
													${order.phone}
												</td>
												<td class="">
													${order.address}
												</td>
												<td class="product-total text-nowrap">
													$ <fmt:formatNumber value="${order.total}" groupingUsed="true"/>
												</td>
												<td class="">
													${order.status.name}
												</td>
												<td class="product-text text-nowrap">
													<form action="/shopper/order" method="post" class="d-flex gap-2">
														<a href="/shopper/order/detail?orderId=${order.orderId}"
														   class="btn btn-primary w-100 mr-2">
															<i class="bi bi-info-lg"></i>
														</a>
														<input type="hidden" name="orderId" value="${order.orderId}">
														<button type="submit" name="submit" value="delete"
																class="btn btn-danger w-100">
															<i class="bi bi-x-lg"></i>
														</button>
													</form>
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
