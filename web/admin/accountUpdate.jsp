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
					<h1 class="text-center mb-4">UPDATE</h1>
					<hr class="my-4">
					<div class="my-4">
						<label for="fullname" class="form-label">Full Name</label>
						<input type="text" name="fullname" value="${admin.fullname}" required
							   class="form-control" id="fullname">
					</div>
					<div class="my-4">
						<label for="emailLogin" class="form-label">Email Address</label>
						<input type="email" name="email" value="${admin.email}" required readonly
							   class="form-control" id="emailLogin">
					</div>
					<div class="my-4">
						<label for="oldPassword" class="form-label">Old Password</label>
						<input type="password" name="oldPassword" required
							   class="form-control" id="oldPassword">
					</div>
					<div class="my-4">
						<label for="newPassword" class="form-label">New Password</label>
						<input type="password" name="newPassword"
							   class="form-control" id="newPassword">
					</div>
					<button type="submit" name="submit" value="update" class="w-100 btn btn-primary border-3">
						Đăng ký
					</button>
				</form>
			</div>
		</main>

		<%@include file="/WEB-INF/jspf/admin/message.jspf" %>
		<%@include file="/WEB-INF/jspf/admin/script.jspf"%>
	</body>
</html>
