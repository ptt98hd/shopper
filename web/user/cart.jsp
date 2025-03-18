<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Giỏ hàng</title>
		<%@include file="/WEB-INF/jspf/user/style.jspf" %>
	</head>
	<body>

		<div class="site-wrap">
			<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>

			<div class="bg-light py-3">
				<div class="container">
					<div class="row">
						<div class="col-md-12 mb-0">
							<a href="home">Trang chủ</a>
							<span class="mx-2 mb-0">/</span>
							<strong class="text-black">Giỏ hàng</strong></div>
					</div>
				</div>
			</div>

			<%@include file="/user/_cart.jsp" %>

			<%@include file="/WEB-INF/jspf/user/footer.jspf" %>
		</div>
		<%@include file="/WEB-INF/jspf/user/message.jspf"%>
		<%@include file="/WEB-INF/jspf/user/script.jspf" %>
	</body>
</html>
