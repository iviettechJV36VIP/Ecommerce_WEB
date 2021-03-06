<%-- 
    Document   : header
    Created on : Oct 6, 2020, 5:20:30 PM
    Author     : Administrator
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

    <body>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="contactinfo">
                                
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="social-icons pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="https://twitter.com/"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="https://www.instagram.com/"><i class="fa fa-instagram"></i></a></li>
                                    <li><a href="https://www.linkedin.com/"><i class="fa fa-linkedin"></i></a></li>
                                    <li><a href="https://dribbble.com/"><i class="fa fa-dribbble"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header_top-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 clearfix">
                            <div class="logo pull-left">
                                <a href="index.html"><img src="images/home/logo.png" alt="" /></a>
                            </div>

                        </div>
                        <div class="col-md-8 clearfix">
                            <div class="shop-menu clearfix pull-right">
                                <ul class="nav navbar-nav">

                                    
                                    
                                        <form action="<c:url value='/j_spring_security_logout' />" method="post" id="logoutForm">
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />
                                        </form>
                                    

                                    
                                    
                                        <script>
                                        function formSubmit() {
                                            document.getElementById("logoutForm").submit();
                                        }
                                    </script>
                                    

                                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                                        <li>
                                            Wellcome, ${pageContext.request.userPrincipal.name} | <a
                                                href="javascript:formSubmit()"> Logout</a>
                                        </li>
                                        <li><a href="${pageContext.request.getContextPath()}/profile"><i class="fa fa-user"></i> Account</a></li>
                                        </c:if>
                                        <c:if test="${pageContext.request.userPrincipal.name == null}" >
                                        <li><a href="<c:url value="/login" />"><i class="fa fa-lock"></i> Login</a></li>
                                        </c:if>



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->

            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="<c:url value="/home" />"><i class="fa fa-home"></i> Home</a></li>
                                    <li><a href="<c:url value="/cart/checkout"/>"><i class="fa fa-crosshairs"></i> Check Out</a>
                                    <li><a href="<c:url value="/cart"/>"><i class="fa fa-shopping-cart"></i> Cart</a>
                                        
                                    </li> 
                                    
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="pull-right">
                                <form method="get" action="<c:url value="/search?keyword=${keyword}"/>">
                                    <input type="text" name="keyword">
                                    <input type="submit" value="Search">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-bottom  ?keyword=${keyword}-->
        </header>
    </body>
</html>
