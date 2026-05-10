<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <title>Manage users</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
                            <h1 class="mt-4">Manage users</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item active">Table users</li>
                            </ol>
                            <!-- Table -->
                            <div class="mt-5 text-left">
                                <div class="row align-items-center">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <h2 class="mb-3">Table users</h2>
                                            <a href="/admin/user/create" class="btn btn-primary">Create a user</a>
                                        </div>
                                        <hr>
                                        <table class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id</th>
                                                    <th scope="col">Email</th>
                                                    <th scope="col">Full Name</th>
                                                    <th scope="col">Role</th>
                                                    <th scope="col">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="user" items="${users}">
                                                    <tr>
                                                        <td>${user.id}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.fullName}</td>
                                                        <td>${user.role.name}</td>
                                                        <td>
                                                            <a href="/admin/user/${user.id}"
                                                                class="btn btn-success me-1">View</a>
                                                            <a href="/admin/user/update/${user.id}"
                                                                class="btn btn-warning me-1">Update</a>
                                                            <a href="/admin/user/delete/${user.id}"
                                                                class="btn btn-danger">Delete</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                <c:if test="${empty users}">
                                                    <tr>
                                                        <td colspan="6" class="text-center">
                                                            No users found.
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
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