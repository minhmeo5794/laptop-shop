<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Manage users</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
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
                                    <li class="breadcrumb-item">
                                        <a class="text-decoration-none" href="/admin/user">Table users</a>
                                    </li>
                                    <li class="breadcrumb-item active">Create a user</li>
                                </ol>
                                <!-- Create -->
                                <div class="mt-5 text-left">
                                    <div class="row align-items-center">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h2 class="mb-3">Create a user</h2>
                                            <hr>
                                            <form:form action="/admin/user/create" method="post"
                                                modelAttribute="newUser" enctype="multipart/form-data">
                                                <c:set var="errorEmail">
                                                    <form:errors path="email" cssClass="invalid-feedback" />
                                                </c:set>
                                                <c:set var="errorFullName">
                                                    <form:errors path="fullName" cssClass="invalid-feedback" />
                                                </c:set>
                                                <c:set var="errorPassword">
                                                    <form:errors path="password" cssClass="invalid-feedback" />
                                                </c:set>

                                                <div class="mb-3">
                                                    <label for="email" class="form-label">Email address</label>
                                                    <form:input type="email"
                                                        class="form-control ${not empty errorEmail? 'is-invalid':''}"
                                                        path="email" />
                                                    ${errorEmail}
                                                </div>
                                                <div class="mb-3 row g-3">
                                                    <div class="col">
                                                        <label for="fullName" class="form-label">Full name</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorFullName? 'is-invalid':''}"
                                                            path="fullName" />
                                                        ${errorFullName}
                                                    </div>
                                                    <div class="col">
                                                        <label for="password" class="form-label">Password</label>
                                                        <form:input type="password"
                                                            class="form-control ${not empty errorPassword? 'is-invalid':''}"
                                                            path="password" />
                                                        ${errorPassword}
                                                    </div>
                                                </div>
                                                <div class="mb-3 row g-3">
                                                    <div class="col">
                                                        <label for="phoneNumber" class="form-label">Phone number</label>
                                                        <form:input type="text" class="form-control"
                                                            path="phoneNumber" />
                                                    </div>
                                                    <div class="col">
                                                        <label for="address" class="form-label">Address</label>
                                                        <form:input type="text" class="form-control" path="address" />
                                                    </div>
                                                </div>
                                                <div class="mb-3 row g-3">
                                                    <div class="col">
                                                        <label for="role" class="form-label">Role</label>
                                                        <form:select class="form-select" path="role.name">
                                                            <form:option value="ADMIN">ADMIN</form:option>
                                                            <form:option value="USER">USER</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col">
                                                        <label for="avatarFile" class="form-label">Avatar</label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            accept=".png, .jpg, .jpeg" name="userImageFile" />
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <img style="max-height: 250px; max-width: 50%; display: none;"
                                                        alt="image preview" id="avatarPreview" />
                                                </div>
                                                <div>
                                                    <button type="submit" class="btn btn-primary">Create</button>
                                                    <a href="/admin/user" class="btn btn-dark">Back</a>
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