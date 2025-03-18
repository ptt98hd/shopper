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
                                            <th>Image</th>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="category" items="${categories}">
											<tr>
												<td>
													<img src="${category.categoryImg}" height="75rem"/>
												</td>
												<td>${category.categoryId}</td>
												<td>${category.categoryName}</td>
												<td>
													<form action="/shopper/admin/category" method="post"
														  class="d-flex flex-column gap-2">
														<input type="hidden" name="categoryId" value="${category.categoryId}">
														<button type="submit" name="submit" value="delete"
																class="btn btn-danger w-100">
															Delete
														</button>
														<a href="/shopper/admin/category?get=update&categoryId=${category.categoryId}"
														   class="btn btn-primary w-100">
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
