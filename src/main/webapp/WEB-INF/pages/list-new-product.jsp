
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



        

        <section>
            <div class="container">
                <div class="row">
                    <jsp:include page="include/categoryandproducer.jsp"/>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">New Products</h2>
                            <c:forEach var="tempProductNew" items="${pagedListHolder.pageList}">
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="${pageContext.request.getContextPath()}/resources/images/item/${tempProductNew.images[0].imageName}" alt="" />
                                                <h2>${tempProductNew.formattedPrice}</h2>
                                                <p>${tempProductNew.productName}</p>

                                            </div>
                                            <div class="product-overlay">
                                                <div class="overlay-content">
                                                    <h2>${tempProductNew.formattedPrice}</h2>
                                                    <p>${tempProductNew.productName}</p>
                                                    <a href="<c:url value="/productDetails/${tempProductNew.productId}" />" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Product Detail</a>
                                                    <a href="<c:url value="/buy/${tempProductNew.productId}" />" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>


                        </div>

                        

                    </div>
                </div>
            </div>
        </section>



        <jsp:include page="include/footer.jsp"/>


    </body>
</html>
