<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Account</title>
		<%@include file="/WEB-INF/jspf/user/style.jspf" %>
	</head>
	<body>
		<div class="site-wrap">
			<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>

			<div class="bg-light py-3">
				<div class="container">
					<div class="row">
						<div class="col-md-12 mb-0">
							<a href="/shopper/home">Trang chủ</a>
							<span class="mx-2 mb-0">/</span>
							<strong class="text-black">Tài khoản</strong>
						</div>
					</div>
				</div>
			</div>

			<div class="site-section">
				<div class="container">
					<div class="row">
						<!--Login-->
						<div class="col-md-6">
							<form action="account" method="post">
								<div class="p-3 p-lg-5 border">
									<h1 class="text-primary mt-3 mt-md-0">ĐĂNG NHẬP</h1>
									<h5 class="text-danger">${messageLogin}</h5>
									<hr>
									<div class="form-group row">
										<div class="col-md-12">
											<label for="email" class="text-black">
												Địa chỉ Email <span class="text-danger">*</span>
											</label>
											<input type="email" name="email" required
												   class="form-control" id="email">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<label for="password" class="text-black">
												Mật khẩu <span class="text-danger">*</span>
											</label>
											<input type="password" name="password" required
												   class="form-control" id="password">
										</div>
									</div>

									<div class="form-group row">
										<div class="col-lg-12">
											<button type="submit" name="submit" value="login"
													class="btn btn-primary btn-lg btn-block">
												Đăng nhập
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>

						<!--signup-->
						<div class="col-md-6 mt-4 mt-md-0">
							<form action="account" method="post">
								<div class="p-3 p-lg-5 border">
									<h1 class="text-primary mt-3 mt-md-0">ĐĂNG KÝ</h1>
									<h5 class="text-danger">${messageSignup}</h5>
									<hr>
									<div class="form-group row">
										<div class="col-12">
											<label for="fullname" class="text-black">
												Họ và tên <span class="text-danger">*</span>
											</label>
											<input type="text" name="fullname" required
												   class="form-control" id="fullname">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<label for="email" class="text-black">
												Địa chỉ Email <span class="text-danger">*</span>
											</label>
											<input type="email" name="email" required
												   class="form-control" id="email">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<label for="password" class="text-black">
												Mật khẩu <span class="text-danger">*</span>
											</label>
											<input type="password" name="password" required
												   class="form-control" id="password">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<label for="rePassword" class="text-black">
												Nhập lại mật khẩu <span class="text-danger">*</span>
											</label>
											<input type="password" name="rePassword" required
												   class="form-control" id="rePassword">
										</div>
									</div>

									<div class="form-group row">
										<div class="col-lg-12">
											<button type="submit" name="submit" value="signup"
													class="btn btn-primary btn-lg btn-block">
												Đăng ký
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>

			<%@include file="/WEB-INF/jspf/user/footer.jspf" %>
		</div>

		<%@include file="/WEB-INF/jspf/user/message.jspf"%>
		<%@include file="/WEB-INF/jspf/user/script.jspf" %>
	</body>
</html>
