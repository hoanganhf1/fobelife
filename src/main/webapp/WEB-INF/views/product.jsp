<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<link rel="stylesheet" href="/resources/css/product.css">
<script type="text/javascript">

    $(document).ready(
            function() {
                // Add the following code if you want the name of the file appear on select
                $(".custom-file-input").on(
                        "change",
                        function() {
                            var fileName = $(this).val().split("\\").pop();
                            $(this).siblings(".custom-file-label").addClass(
                                    "selected").html(fileName);
                        });
            });
</script>
<div>
    <div class="container">
        <form method="post" action="/product">
            <div class="form-row">
            
                <div class="form-group col-md-2">
                    <label for="btnCreate">&nbsp;</label>
                    <button id="btnCreate" name="btnCreate" type="submit" class="btn btn-primary form-control">Create</button>
                </div>
                <div class="form-group col-md-2">
                    <label for="name">Product Name</label>
                    <input type="text" class="form-control" name="name" id="name"/>
                </div>
                <div class="form-group col-md-2">
                    <label for="description">Product Description</label>
                    <textarea id="description" name="description" rows="1" class="form-control" id="inputEmail4">
                    </textarea>
                </div>
                <div class="form-group col-md-2">
                    <label for="image">Product Image Link</label>
                    <input type="text" class="form-control" name="image" id="image"/>
                </div>
                <div class="form-group col-md-2">
                    <label for="price">Product Price</label>
                    <input type="text" class="form-control" id="price" name="price"/>
                </div>
                <div class="form-group col-md-2">
                    <label for="type">Product Type</label>
                    <select name="type" class="custom-select mb-3 form-control">
                        <option value="FOBELIFE">Fobelife</option>
                        <option value="CHO_SI"><spring:message code="label.product.type.cho_si" /></option>
                    </select>
                </div>
            </div>
        </form>
        <form method="post" action="/product/import" enctype="multipart/form-data">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <button id="btnImport" name="btnImport" type="submit" class="btn btn-primary form-control">Import</button>
                </div>
                <div class="custom-file form-group col-md-5">
                    <input type="file" class="custom-file-input form-control" id="file" name="file">
                    <label class="custom-file-label" for="customFile">Choose File</label>
                </div>
                <div class="form-group col-md-2">
                    <a target="_blank" href="/resources/docs/ImportProduct.csv.zip"><spring:message code="lable.download.template" /></a>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="main fadeInDown">

    <div class="container">
        <div class="shopping-cart container">

            <div class="column-labels">
                <label class="product-image">Image</label>
                <label class="product-details">Product</label>
                <label class="product-price">Price</label>
                <label class="product-price">Type</label>
                <label class="product-removal">Change status</label>
            </div>
            <c:forEach items="${mProduct.data}" var="product">
                <div class="product">
                    <div class="product-image">
                        <img src="${product.image}">
                    </div>
                    <div class="product-details">
                        <div class="product-title">${product.name}</div>
                        <p class="product-description">${product.description}</p>
                    </div>
                    <div class="product-price">${product.price}</div>
                    <div class="product-price">${product.type}</div>
                    <div class="product-removal">
                        <input type="hidden" class="hidden product-code" value="${product.code}" /> 
                        ${product.status eq 'ACTIVE' ? 
                            '<button class="inactive-product product-action">Inactive</button>' 
                            :'<button class="active-product product-action">Active</button>'
                            }

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- container// -->
<script src="/resources/js/product.js"></script>