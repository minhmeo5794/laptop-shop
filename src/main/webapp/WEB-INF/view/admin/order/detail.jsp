<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="" />
                    <meta name="author" content="" />
                    <title>Dashboard - SB Admin</title>
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                </head>

                <body class="sb-nav-fixed">
                    <!-- Header -->
                    <jsp:include page="../layout/header.jsp" />

                    <!-- Side nav -->
                    <div id="layoutSidenav">
                        <!-- Side nav - bar -->
                        <jsp:include page="../layout/sidebar.jsp" />

                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage orders</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item">
                                            <a class="text-decoration-none" href="/admin/order">Table orders</a>
                                        </li>
                                        <li class="breadcrumb-item active">Order ${order.id}</li>
                                    </ol>
                                    <!-- Detail -->
                                    <div class="mt-5 text-left">
                                        <div class="row align-items-center">
                                            <div class="col-md-12 col-12 mx-auto">
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <h2 class="mb-3">Order ${order.id}</h2>
                                                </div>
                                                <hr>
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">Sản phẩm</th>
                                                            <th scope="col">Tên</th>
                                                            <th scope="col">Giá cả</th>
                                                            <th scope="col">Số lượng</th>
                                                            <th scope="col">Thành tiền</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:if test="${empty orderDetails}">
                                                            <tr>
                                                                <td colspan="6">
                                                                    Không có sản phẩm trong giỏ hàng
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                        <c:forEach var="orderDetail" items="${orderDetails}">

                                                            <tr>
                                                                <th scope="row">
                                                                    <div class="d-flex align-items-center">
                                                                        <img src="/images/product/${orderDetail.product.image}"
                                                                            class="img-fluid me-5 rounded-circle"
                                                                            style="width: 80px; height: 80px;" alt="">
                                                                    </div>
                                                                </th>
                                                                <td>
                                                                    <p class="mb-0 mt-4">
                                                                        <a href="/product/${orderDetail.product.id}"
                                                                            target="_blank">
                                                                            ${orderDetail.product.name}
                                                                        </a>
                                                                    </p>
                                                                </td>
                                                                <td>
                                                                    <p class="mb-0 mt-4">
                                                                        <fmt:formatNumber type="number"
                                                                            value="${orderDetail.price}" /> đ
                                                                    </p>
                                                                </td>
                                                                <td>
                                                                    <div class="input-group quantity mt-4"
                                                                        style="width: 100px;">
                                                                        <input type="text"
                                                                            class="form-control form-control-sm text-center border-0"
                                                                            value="${orderDetail.quantity}">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <p class="mb-0 mt-4"
                                                                        data-cart-detail-id="${orderDetail.id}">
                                                                        <fmt:formatNumber type="number"
                                                                            value="${orderDetail.price * orderDetail.quantity}" />
                                                                        đ
                                                                    </p>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <a href="/admin/order" class="btn btn-dark mt-3">Back</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </main>

                            <!-- Footer in main -->
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>

                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/scripts.js"></script>
                </body>

                </html>