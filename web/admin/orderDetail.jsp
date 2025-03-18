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
                        <h1 class="mt-4 mb-4">Order</h1>
                        <div class="card mb-4">
                            <div class="card-header d-inline-flex justify-content-between align-items-center">
								<p class="my-0">
									<b>Order ID:</b> ${order.orderId}
								</p>
								<p class="my-0">
									<b>Total:</b> <fmt:formatNumber value="${order.total}" groupingUsed="true"/>
								</p>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered mb-0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Image</th>
                                            <th>Product</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="orderDetail" items="${orderDetails}">
											<tr>
												<td>${orderDetail.orderDetailId}</td>
												<td>
													<img src="${orderDetail.productSize.product.productImg}" height="100px"/>
												</td>
												<td>
													<b>Name:</b> ${orderDetail.productSize.product.productName}<br>
													<b>Size:</b> ${orderDetail.productSize.size.sizeEu}
												</td>
												<td>
													$ <fmt:formatNumber value="${orderDetail.productSize.product.price}" groupingUsed="true"/>
												</td>
												<td>${orderDetail.quantity}</td>
												<td>${orderDetail.total}</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
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
