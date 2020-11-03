<%-- 
    Document   : footer
    Created on : Oct 6, 2020, 5:33:00 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <div class="panel panel-default">
                                    <h3 class="panel-title">
                                        <ul>
                                            <c:forEach var="item" items="${listCategory}">
                                                <li><a href="<c:url value="/category/${item.categoryName}/${item.categoryId}" /> ">${item.categoryName}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </h3>
                                </div>
                            </div>
                            <h2>Producer</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <div class="panel panel-default">
                                    <h3 class="panel-title">
                                        <ul>
                                            <c:forEach var="item" items="${listProducer}">
                                                <li><a href="<c:url value="/producer/${item.producerName}/${item.producerId}" /> ">${item.producerName}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </h3>
                                </div>
                            </div>

                        </div>
                    </div>
    </body>
</html>
