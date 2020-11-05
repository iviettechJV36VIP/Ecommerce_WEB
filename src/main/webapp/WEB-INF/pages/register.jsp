
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

    <jsp:include page="include/res.jsp"/>

    <body>
        <!--/header-->
        <jsp:include page="include/header.jsp"/>

        <section id="form"><!--form-->
            <div class="container">
                <div class="row">


                    <div class="col-sm-4">
                        <div class="signup-form"><!--sign up form-->
                            <h2>New User Signup!</h2>
                            <c:if test="${not empty error}">
                                <div class="error">${error}</div>
                            </c:if>
                            <c:if test="${not empty msg}">
                                <div class="msg">${msg}</div>
                            </c:if>
                            <form:form name="registerForm" action="register" method="POST" modelAttribute="customer">
                                <form:input  path="customerFirstName" id="customerFirstName" placeholder="Customer First Name" />
                                <form:input  path="customerLastName" id="customerLasttName" placeholder="Customer Last Name" />
                                <form:input  path="email" id="email" placeholder="Email" />
                                <form:input  path="username" id="username" placeholder="User Name"/>
                                <form:password  path="password" id="password" placeholder="Password" />
                                <button type="submit" class="btn btn-default">Signup</button>
                            </form:form>
                        </div><!--/sign up form-->
                    </div>
                </div>
            </div>
        </section><!--/form-->
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>