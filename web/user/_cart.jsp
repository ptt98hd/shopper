<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="site-section" id="cartContainer">
	<div class="container">
		<div class="row mb-5">
			<div class="col-md-12">
				<div class="site-blocks-table">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="product-thumbnail d-none d-xl-table-cell">Hình ảnh</th>
								<th class="">Sản phẩm</th>
								<th class="product-quantity">Số lượng</th>
								<th class="product-total text-nowrap">Thành tiền</th>
								<th class="product-remove">Sửa</th>
							</tr>
						</thead>
						<tbody>
							<c:set  var="cartTotal" value="0"/>
							<c:forEach var="item" items="${cart}">
								<c:set  var="cartTotal" value="${cartTotal + item.key.product.price * item.value}"/>
								<tr>
									<td class="product-thumbnail d-none d-xl-table-cell">
										<img src="${item.key.product.productImg}" alt="Image" class="img-fluid">
									</td>
									<td class="text-left">
										<h2 class="h4 text-black">${item.key.product.productName}</h2>
										<p class="mb-0"><b>Kích cỡ:</b> ${item.key.size.sizeEu} EU</p>
										<p class="mb-0"><b>Giá tiền:</b> $ <fmt:formatNumber value="${item.key.product.price}" groupingUsed="true"/></p>
									</td>
									<td>
										<div class="input-group mb-3 m-auto d-inline-flex flex-nowrap">
											<div class="input-group-prepend">
												<button class="btn btn-primary js-btn-minus" type="button"
														hx-post="cart" hx-target="#cartContainer" hx-swap="outerHTML" hx-trigger="click"
														hx-vals='{
														"productSizeId": "${item.key.productSizeId}",
														"quantity": "${item.value - 1}",
														"submit": "update"
														}'>
													&minus;
												</button>
											</div>
											<input type="number" name="quantity" value="${item.value}"
												   class="form-control text-center" style="min-width: 3rem">
											<div class="input-group-append">
												<button class="btn btn-primary js-btn-plus" type="button"
														hx-post="cart" hx-target="#cartContainer" hx-swap="outerHTML" hx-trigger="click"
														hx-vals='{
														"productSizeId": "${item.key.productSizeId}",
														"quantity": "${item.value + 1}",
														"submit": "update"
														}'>
													&plus;
												</button>
											</div>
										</div>
									</td>
									<td>
										$ <fmt:formatNumber value="${item.key.product.price * item.value}" groupingUsed="true"/>
									</td>
									<td>
										<button class="btn btn-primary btn-sm"
												hx-post="cart" hx-target="#cartContainer" hx-swap="outterHTML"
												hx-vars='{
												"productSizeId": "${item.key.productSizeId}",
												"submit": "delete"
												}'>
											<i class="bi bi-x-lg"></i>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-7">
				<div class="row mb-5">
					<div class="col-lg-6 mb-3">
						<a href="/shopper/product" class="btn btn-outline-primary btn-lg py-3 btn-block" style="font-size: 1rem">
							Tiếp tục mua sắm
						</a>
					</div>
					<div class="col-lg-6 mb-3">
						<a href="/shopper/checkout" class="btn btn-primary btn-lg py-3 btn-block" style="font-size: 1rem">
							Tiến hành đặt hàng
						</a>
					</div>
				</div>
			</div>

			<div class="col-md-5 pl-5">
				<div class="row justify-content-end">
					<div class="col-12">
						<div class="row">
							<div class="col-md-12 text-right border-bottom mb-5">
								<h3 class="text-black h4 text-uppercase">Tổng tiền</h3>
							</div>
						</div>
						<div class="row mb-4">
							<div class="col-md-12 text-right">
								<strong class="text-black h1 text-nowrap">
									$ <fmt:formatNumber value="${cartTotal}" groupingUsed="true"/>
								</strong>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>