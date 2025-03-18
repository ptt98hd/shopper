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
                            <li class="breadcrumb-item active">Insert</li>
                        </ol>
                        <div class="card mb-4">
                            <form action="category" method="post" class="p-5 bg-body-tertiary">
								<div class="mb-3">
									<label for="categoryName" class="form-label">Category Name</label>
									<input type="text" name="categoryName" required
										   class="form-control" id="categoryName">
								</div>
								<div class="mb-3">
									<label for="categoryImg" class="form-label">Category Image</label>
									<input type="url" name="categoryImg" required
										   class="form-control" id="categoryImg">
								</div>
								<button type="submit" name="submit" value="insert"
										class="btn btn-success w-100 mt-3">Create
								</button>
							</form>
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
