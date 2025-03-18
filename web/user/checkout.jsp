<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Đặt hàng</title>
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
							<a href="index.html">Trang chủ</a>
							<span class="mx-2 mb-0">/</span>
							<strong class="text-black">Đặt hàng</strong></div>
					</div>
				</div>
			</div>
			<!--Breadcrumbs-->

			<div class="site-section">
				<div class="container">
					<form action="checkout" method="post" class="row">
						<!--Shipping info-->
						<div class="col-md-5 mb-5 mb-md-0">
							<h2 class="h3 mb-3 text-black">Thông tin giao hàng</h2>
							<div class="p-3 p-lg-5 border">
								<div class="form-group">
									<label for="consignee" class="text-black">
										Người nhận hàng <span class="text-danger">*</span>
									</label>
									<input type="text" name="consignee" value="${account.fullname}" required
										   placeholder="Họ và tên"
										   class="form-control" id="consignee">
								</div>
								<div class="form-group">
									<label for="phone" class="text-black">
										Số điện thoại <span class="text-danger">*</span>
									</label>
									<input type="tel" name="phone" required
										   placeholder="Số điện thoại"
										   class="form-control" id="phone">
								</div>
								<div class="form-group">
									<label for="address" class="text-black">
										Địa chỉ giao hàng <span class="text-danger">*</span>
									</label>
									<input type="text" name="address" required
										   placeholder="Địa chỉ giao hàng"
										   class="form-control" id="address">
								</div>
							</div>
						</div>
						<!--Shipping info-->

						<!--Order Info-->
						<div class="col-md-7">
							<div class="row mb-5">
								<div class="col-md-12">
									<h2 class="h3 mb-3 text-black">Thông tin đơn hàng</h2>
									<div class="p-3 p-lg-5 border">
										<table class="table site-block-order-table mb-4">
											<thead>
												<tr>
													<th class="text-nowrap">Hình ảnh</th>
													<th class="text-nowrap">Sản phẩm</th>
													<th class="text-nowrap">Số lượng</th>
													<th class="text-nowrap">Thành tiền</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${cart}">
													<tr>
														<td>
															<img src="${item.key.product.productImg}" class="border" width="60px"/>
														</td>
														<td>${item.key.product.productName}</td>
														<td>${item.value}</td>
														<td class="text-nowrap">
															$ <fmt:formatNumber value="${item.key.product.price * item.value}" groupingUsed="true"/>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

										<div class="border rounded px-3 py-4 d-flex justify-content-between">
											<h3 class="mb-0 text-black">Tổng tiền: </h3>
											<h3 class="mb-0 text-black">
												$ <fmt:formatNumber value="${total}" groupingUsed="true"/>
											</h3>
										</div>

										<div class="form-group mt-5">
											<button class="btn btn-primary btn-lg py-3 btn-block">
												Tiến hành đặt hàng
											</button>
										</div>

									</div>
								</div>
							</div>

						</div>
						<!--Order Info-->
					</form>
					<!-- </form> -->
				</div>
			</div>

			<%@include file="/WEB-INF/jspf/user/footer.jspf" %>
		</div>
		<%@include file="/WEB-INF/jspf/user/message.jspf"%>
		<%@include file="/WEB-INF/jspf/user/script.jspf" %>
	</body>
</html>
