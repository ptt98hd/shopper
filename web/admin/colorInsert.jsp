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
                            <li class="breadcrumb-item active">Insert</li>
                        </ol>
                        <div class="card mb-4">
                            <form action="color" method="post" class="p-5 bg-body-tertiary">
								<div class="mb-3">
									<label for="colorName" class="form-label">Color Name</label>
									<input type="text" name="colorName" required
										   class="form-control" id="colorName">
								</div>
								<div class="mb-3">
									<label for="hex" class="form-label">Color Hex</label>
									<input type="color" name="hex" value="ff0000"  required
										   class="form-control form-control-color" id="hex">
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
