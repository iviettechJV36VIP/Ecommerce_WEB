
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

    <jsp:include page="include/res.jsp"/>

<body>
	 <jsp:include page="include/header.jsp"/>

	

	<section id="do_action">
            <div class="container">
            <h2>Thank You</h2>
            <h2>Please check your email for more information about your order!</h2>
            </div>
	</section><!--/#do_action-->

	<jsp:include page="include/footer.jsp"/>
	


</body>
</html>