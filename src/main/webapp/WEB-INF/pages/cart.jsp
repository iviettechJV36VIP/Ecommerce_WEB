
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

    <jsp:include page="include/res.jsp"/>

<body>
	 <jsp:include page="include/header.jsp"/>

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
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
								<p>${item.product.price}</p>
							</td>
							<td class="cart_quantity">
                                                            <input style="width: 60px" type="number" name="quantities" value="${item.quantity}">
							</td>
							<td class="cart_total">
								<p class="cart_total_price">${item.product.price * item.quantity}</p>
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
	</section> <!--/#cart_items-->

	<section id="do_action">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Total <span>$${total}</span></li>
						</ul>
							<a class="btn btn-default check_out" href="">Check Out</a>
					</div>
				</div>
			</div>
		</div>
	</section><!--/#do_action-->

	<jsp:include page="include/footer.jsp"/>
	


</body>
</html>