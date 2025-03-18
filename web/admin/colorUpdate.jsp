<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Color</title>
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
                            <li class="breadcrumb-item active">Update</li>
                        </ol>
                        <div class="card mb-4">
                            <form action="color" method="post" class="p-5 bg-body-tertiary">
								<div class="mb-3">
									<label for="colorId" class="form-label">Color ID</label>
									<input type="number" name="colorId" value="${color.colorId}"
										   readonly required
										   class="form-control" id="colorId">
								</div>
								<div class="mb-3">
									<label for="colorName" class="form-label">Color Name</label>
									<input type="text" name="colorName" value="${color.colorName}" required
										   class="form-control" id="colorName">
								</div>
								<div class="mb-3">
									<label for="hex" class="form-label">Color Hex</label>
									<input type="color" name="hex" value="${color.hex}" required
										   class="form-control form-control-color" id="hex">
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
