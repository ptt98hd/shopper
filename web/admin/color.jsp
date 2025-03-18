<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <h1 class="mt-4">Color</h1>
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
									<a href="/shopper/admin/color?get=insert"
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
                                            <th>Color</th>
                                            <th>Hex</th>
                                            <th>Name</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="color" items="${colors}">
											<tr>
												<td>${color.colorId}</td>
												<td>
													<div style="background-color: ${color.hex}; width: 50px; height: 50px"></div>
												</td>
												<td>${color.hex}</td>
												<td>${color.colorName}</td>
												<td>
													<form action="/shopper/admin/color" method="post"
														  class="d-flex gap-2">
														<input type="hidden" name="colorId" value="${color.colorId}">
														<button type="submit" name="submit" value="delete"
																class="btn btn-danger">
															Delete
														</button>
														<a href="/shopper/admin/color?get=update&colorId=${color.colorId}"
														   class="btn btn-primary">
															Update
														</a>
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
