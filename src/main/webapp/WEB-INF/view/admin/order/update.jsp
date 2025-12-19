<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="" />
                    <meta name="author" content="" />
                    <title>Dashboard - SB Admin</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                        rel="stylesheet">
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
                                        <li class="breadcrumb-item active">Update order ${currentOrder.id}</li>
                                    </ol>
                                    <!-- Update -->
                                    <div class="mt-5 text-left">
                                        <div class="row align-items-center">
                                            <div class="col-md-6 col-12 mx-auto">
                                                <h2 class="mb-3">Update order ${currentOrder.id}</h2>
                                                <hr>
                                                <form:form method="post" action="/admin/order/update/${currentOrder.id}" class="row"
                                                    modelAttribute="currentOrder">
                                                    <div class="mb-3" style="display: none;">
                                                        <label class="form-label">Id:</label>
                                                        <form:input type="text" class="form-control" path="id" />
                                                    </div>
                                                    <div class="mb-3">
                                                        <label>Order id = ${currentOrder.id} </label>
                                                        &nbsp; &nbsp; &nbsp; &nbsp;
                                                        <label class="form-label">Price:
                                                            <fmt:formatNumber type="number"
                                                                value="${currentOrder.totalPrice}" /> đ
                                                        </label>
                                                    </div>

                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">User:</label>
                                                        <form:input type="text" class="form-control" disabled="true"
                                                            path="user.fullName" />
                                                    </div>

                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Status:</label>
                                                        <form:select class="form-select" path="status">
                                                            <form:option value="PENDING">PENDING</form:option>
                                                            <form:option value="SHIPPING">SHIPPING</form:option>
                                                            <form:option value="COMPLETE">COMPLETE</form:option>
                                                            <form:option value="CANCEL">CANCEL</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-12 mb-5">
                                                        <button type="submit" class="btn btn-warning">Update</button>
                                                        <a href="/admin/order" class="btn btn-dark">Back</a>
                                                    </div>
                                                </form:form>
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
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <script src="/js/scripts.js"></script>
                </body>

                </html>