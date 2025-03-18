<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Admin | Size</title>
		<%@include file="/WEB-INF/jspf/admin/style.jspf" %>
    </head>
    <body class="sb-nav-fixed">
		<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>

        <div id="layoutSidenav">
			<%@include file="/WEB-INF/jspf/admin/sidebar.jspf" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="my-4">Size</h1>
                        <div class="card mb-4">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <div>
									<i class="fas fa-table me-1"></i>
									DataTable
								</div>
								<div>
									<a href="/shopper/admin/size?get=insert"
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
                                            <th>Size EU</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="size" items="${sizes}">
											<tr>
												<td>${size.sizeId}</td>
												<td>${size.sizeEu}</td>
												<td>
													<form action="/shopper/admin/size" method="post"
														  class="d-flex gap-2">
														<input type="hidden" name="sizeId" value="${size.sizeId}">
														<a href="/shopper/admin/size?get=update&sizeId=${size.sizeId}"
														   class="btn btn-primary">
															Update
														</a>
														<button type="submit" name="submit" value="delete"
																class="btn btn-danger">
															Delete
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
