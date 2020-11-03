
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

    <body>


        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Check out</li>
                    </ol>
                </div><!--/breadcrums-->






                <div class="shopper-informations">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="shopper-info">
                                <p>Enter paypal information</p>
                                <form method="get" action="<c:url value="/cart/checkout/thankyou" />">
                                    <input type="text" placeholder="Paypal Code" name="code">
                                    <input type="text" placeholder="Paypal Name" name="name">
                                    <input type="text" placeholder="Paypal Month" name="month">
                                    <input type="text" placeholder="Paypal Year" name="year">
                                    <input type="text" placeholder="Paypal Security Code" name="securityCode">
                                    <input class="btn btn-primary" type="submit" value="Confirm">
                                </form>
                            </div>
                            <c:if test="${messenger != null}">
                                <div class="error"><i><h4 style="color:red;">${messenger}</h4></i></div>
                                        </c:if>
                                        <c:if test="${messenger1 != null}">
                                <div class="error"><i><h4 style="color:red;">${messenger1}</h4></i></div>
                                        </c:if>
                        </div>

                    </div>
                </div>
                <div class="review-payment">
                    <h2>Review & Payment</h2>
                </div>

                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Item</td>
                                <td class="description"></td>
                                <td class="price">Price</td>
                                <td class="quantity">Quantity</td>
                                <td class="total">Total</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.cart}">
                                <tr>
                                    <td class="cart_product">
                                        <a href=""><img src="images/cart/three.png" alt=""></a>
                                    </td>
                                    <td class="cart_description">
                                        <h4><a href="">${item.product.productName}</a></h4>
                                        <p>Product ID: ${item.product.productId}</p>
                                    </td>
                                    <td class="cart_price">
                                        <p>${item.product.formattedPrice}</p>
                                    </td>
                                    <td class="cart_quantity">
                                        <input style="width: 60px" type="number" name="quantities" value="${item.quantity}">
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_total_price"> <fmt:formatNumber value = "${item.product.price * item.quantity}" type = "currency"/></p>
                            </td>
                            <td class="cart_delete">
                                <a class="cart_quantity_delete" href="<c:url value="/remove/${item.product.productId}" />"><i class="fa fa-times"></i></a>
                            </td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>
                </div>
                
            </div>

        </section>
        <section id="do_action">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="total_area">
                            <ul>
                                <li>Total <span>${total}</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </section><!--/#cart_items-->



        <jsp:include page="include/footer.jsp"/>
    </body>
</html>