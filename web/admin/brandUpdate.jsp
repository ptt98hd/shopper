<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Admin | Brand</title>
		<%@include file="/WEB-INF/jspf/admin/style.jspf" %>
    </head>
    <body class="sb-nav-fixed">
		<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>

        <div id="layoutSidenav">
			<%@include file="/WEB-INF/jspf/admin/sidebar.jspf" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="my-4">Brand</h1>
                        <div class="card mb-4">
                            <form action="brand" method="post" class="p-5 bg-body-tertiary">
								<div class="mb-3">
									<label for="brandId" class="form-label">Brand ID</label>
									<input type="number" name="brandId" value="${brand.brandId}"
										   readonly required
										   class="form-control" id="categoryId">
								</div>
								<div class="mb-3">
									<label for="brandName" class="form-label">Brand Name</label>
									<input type="text" name="brandName" value="${brand.brandName}" required
										   class="form-control" id="brandName">
								</div>
								<div class="mb-3">
									<label for="brandImg" class="form-label">Brand Image</label>
									<input type="url" name="brandImg" value="${brand.brandImg}" required
										   class="form-control" id="brandImg">
								</div>
								<button type="submit" name="submit" value="update"
										class="btn btn-primary w-100 mt-3">Update
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
