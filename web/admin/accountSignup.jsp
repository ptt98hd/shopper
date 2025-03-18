<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin | Account</title>
        <%@include file="/WEB-INF/jspf/admin/style.jspf"%>
    </head>

    <body style="width: 100lvw; height: 100lvh">
		<main class="container-fluid d-flex justify-content-center align-items-center w-100 h-100">
			<div style="width: 500px">
				<form action="/shopper/admin/account" method="post"
					  class="shadow border rounded p-5 bg-body-tertiary">
					<h1 class="text-center mb-4">SIGNUP</h1>
					<hr class="my-4">
					<div class="my-4">
						<label for="fullname" class="form-label">Full Name</label>
						<input type="text" name="fullname" class="form-control" id="fullname" required>
					</div>
					<div class="my-4">
						<label for="emailLogin" class="form-label">Email Address</label>
						<input type="email" name="email" class="form-control" id="emailLogin" required>
					</div>
					<div class="my-4">
						<label for="password" class="form-label">Password</label>
						<input type="password" name="password" class="form-control" id="password" required>
					</div>
					<button type="submit" name="submit" value="signup" class="w-100 btn btn-primary border-3">
						Đăng ký
					</button>
				</form>
			</div>
		</main>

		<%@include file="/WEB-INF/jspf/admin/message.jspf" %>
		<%@include file="/WEB-INF/jspf/admin/script.jspf"%>
	</body>
</html>
