<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
                                <h1 class="mt-4">Manage products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item">
                                        <a class="text-decoration-none" href="/admin/product">Table products</a>
                                    </li>
                                    <li class="breadcrumb-item active">Delete a product</li>
                                </ol>
                                <!-- Delete -->
                                <div class="mt-5 text-left">
                                    <div class="row align-items-center">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h2 class="mb-3">Delete product: id ${currentProduct.id}</h2>
                                            <hr>
                                            <div class="alert alert-danger" role="alert">
                                                Are you sure you want to delete this product?
                                            </div>
                                            <form:form action="/admin/product/delete/${currentProduct.id}" method="post"
                                                modelAttribute="currentProduct">
                                                <div class="mb-3 d-none">
                                                    <label for="id" class="form-label">Id</label>
                                                    <form:input type="text" class="form-control" path="id"
                                                        value="${currentProduct.id}" />
                                                </div>
                                                <div>
                                                    <button type="submit" class="btn btn-danger">Confirm</button>
                                                    <a href="/admin/product" class="btn btn-dark">Back</a>
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
                <script src="/js/scripts.js"></script>
            </body>

            </html>