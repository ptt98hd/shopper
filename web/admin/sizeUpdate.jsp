<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                            <form action="/shopper/admin/size" method="post" class="p-5 bg-body-tertiary">
								<div class="mb-3">
									<label for="sizeId" class="form-label">Size ID</label>
									<input type="number" name="sizeId" value="${size.sizeId}"
										   readonly required
										   class="form-control" id="sizeId">
								</div>
								<div class="mb-3">
									<label for="sizeEu" class="form-label">Size ID</label>
									<input type="number" name="sizeEu" value="${size.sizeEu}"
										   required
										   class="form-control" id="sizeEu">
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
