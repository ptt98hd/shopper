<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Category</title>
		<%@include file="/WEB-INF/jspf/admin/style.jspf" %>
    </head>
    <body class="sb-nav-fixed">
		<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>

        <div id="layoutSidenav">
			<%@include file="/WEB-INF/jspf/admin/sidebar.jspf" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Category</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <div>
									<i class="fas fa-table me-1"></i>
									DataTable
								</div>
								<div>
									<a href="/shopper/admin/category?get=insert"
									   class="btn btn-success">
										Create
									</a>
								</div>
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Consignee</th>
                                            <th>Phone</th>
                                            <th>Email</th>
                                            <th>Address</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="order" items="${orders}">
											<tr>
												<td>${order.orderId}</td>
												<td>${order.consignee}</td>
												<td>${order.phone}</td>
												<td>${order.account.email}</td>
												<td>${order.address}</td>
												<td>
													$ <fmt:formatNumber value="${order.total}"/>
												</td>
												<td>
													<form action="/shopper/admin/order" method="post" class="d-flex gap-2">
														<input type="hidden" name="orderId" value="${order.orderId}">
														<select name="status" class="form-select">
															<c:forEach var="var" items="${statuses}">
																<option value="${var.name}" ${var.name == order.status.name ? "selected" : ""}>
																	${var.name}
																</option>
															</c:forEach>
														</select>
														<button type="submit" name="submit" value="update" class="btn btn-success">
															<i class="bi bi-arrow-clockwise"></i>
														</button>
													</form>
												</td>
												<td>
													<form action="/shopper/admin/order" method="post"
														  class="d-flex gap-2">
														<input type="hidden" name="orderId" value="${order.orderId}">
														<a href="/shopper/admin/order?get=info&orderId=${order.orderId}"
														   class="btn btn-primary w-100">
															<i class="fa-solid fa-circle-info"></i>
														</a>
														<button type="submit" name="submit" value="delete"
																class="btn btn-danger w-100">
															<i class="fa-solid fa-trash-can"></i>
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
                </main>

                <%@include file="/WEB-INF/jspf/admin/footer.jspf" %>
            </div>
        </div>

		<%@include file="/WEB-INF/jspf/admin/message.jspf" %>
		<%@include file="/WEB-INF/jspf/admin/script.jspf" %>
    </body>
</html>
