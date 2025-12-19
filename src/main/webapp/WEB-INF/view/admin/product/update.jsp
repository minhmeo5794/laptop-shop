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
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <script>
                    $(document).ready(() => {
                        const orgImageFile = "${currentProduct.image}" ? "${currentProduct.image}" : "${currentProductImage}";
                        let urlImage;
                        const imagePreview = $("#imagePreview");
                        const imageFile = $("#imageFile");


                        console.log("currentProduct.image: ", "${currentProduct.image}");
                        console.log("currentProduct.name: ", "${currentProduct.name}");
                        console.log("currentProduct.price: ", "${currentProduct.price}");

                        if (orgImageFile) {
                            urlImage = "/images/product/" + orgImageFile;
                            imagePreview.attr("src", urlImage);

                            console.log("urlImage: ", urlImage);
                        }

                        imageFile.change(function (e) {
                            // if (orgImageFile) {
                            //     urlImage = "/images/product/" + orgImageFile;
                            //     imagePreview.attr("src", urlImage);
                            // }
                            const imgURL = URL.createObjectURL(e.target.files[0]);

                            imagePreview.attr("src", imgURL);
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
                                <h1 class="mt-4">Manage products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item">
                                        <a class="text-decoration-none" href="/admin/product">Table products</a>
                                    </li>
                                    <li class="breadcrumb-item active">Update product ${currentProduct.id}</li>
                                </ol>
                                <!-- Update -->
                                <div class="mt-5 text-left">
                                    <div class="row align-items-center">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h2 class="mb-3">Update product ${currentProduct.id}</h2>
                                            <hr>
                                            <form:form action="/admin/product/update/${currentProduct.id}" method="post"
                                                modelAttribute="currentProduct" enctype="multipart/form-data">
                                                <div class="mb-3 d-none">
                                                    <label for="id" class="form-label">Id</label>
                                                    <form:input type="text" class="form-control" path="id" />
                                                </div>
                                                <div class="mb-3 row g-3">
                                                    <div class="col">
                                                        <label for="name" class="form-label">Name</label>
                                                        <form:input type="text" class="form-control" path="name" />
                                                    </div>
                                                    <div class="col">
                                                        <label for="price" class="form-label">Price</label>
                                                        <form:input type="text" class="form-control" path="price" />
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="detailDesc" class="form-label">Detail
                                                        description</label>
                                                    <form:textarea type="text" class="form-control" path="detailDesc" />
                                                </div>
                                                <div class="mb-3 row g-3">
                                                    <div class="col">
                                                        <label for="shortDesc" class="form-label">Short
                                                            description</label>
                                                        <form:input type="text" class="form-control" path="shortDesc" />
                                                    </div>
                                                    <div class="col">
                                                        <label for="quantity" class="form-label">Quantity</label>
                                                        <form:input type="number" class="form-control" min="0"
                                                            path="quantity" />
                                                    </div>
                                                </div>
                                                <div class="mb-3 row g-3">
                                                    <div class="col">
                                                        <label for="brand" class="form-label">Brand</label>
                                                        <form:select class="form-select" path="brand">
                                                            <form:option value="Apple">Apple</form:option>
                                                            <form:option value="Dell">Dell</form:option>
                                                            <form:option value="Asus">Asus</form:option>
                                                            <form:option value="Lenovo">Lenovo</form:option>
                                                            <form:option value="LG">LG</form:option>
                                                            <form:option value="Acer">Acer</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col">
                                                        <label for="purpose" class="form-label">Purpose</label>
                                                        <form:select class="form-select" path="purpose">
                                                            <form:option value="Gaming">Gaming</form:option>
                                                            <form:option value="GraphicDesign">Thiết kế đồ hoạ
                                                            </form:option>
                                                            <form:option value="StudentAndOffice">Sinh viên - Văn
                                                                phòng
                                                            </form:option>
                                                            <form:option value="ThinAndLight">Mỏng nhẹ</form:option>
                                                            <form:option value="Businessmen">Doanh nhân
                                                            </form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="imageFile" class="form-label">Image</label>
                                                        <input class="form-control" type="file" id="imageFile"
                                                            accept=".png, .jpg, .jpeg" name="productImageFile" />
                                                    </div>
                                                    <div class="mb-3">
                                                        <img style="max-height: 250px; max-width: 50%;"
                                                            alt="image preview" id="imagePreview" />
                                                    </div>
                                                </div>
                                                <div>
                                                    <button type="submit" class="btn btn-warning">Update</button>
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
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script src="/js/scripts.js"></script>
            </body>

            </html>