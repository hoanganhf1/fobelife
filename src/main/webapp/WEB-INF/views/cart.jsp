<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript">
    $(document).ready(function() {
        $('#cartTable').DataTable();
    });
</script>
<div class="main fadeInDown">
    <div class="shopping-cart container">
        <form action="/cart" method="post">
            <div class="product">
                <table id="cartTable" class="table table-striped table-bordered" style="width: 100%">
                    <thead>
                        <tr>
                            <th style="width: auto"><spring:message code="label.product.code" /></th>
                            <th style="width: 100px"><spring:message code="label.product.image" /></th>
                            <th style="width: auto"><spring:message code="label.product.name" /></th>
                            <th style="width: auto"><spring:message code="label.product.price" /></th>
                            <th style="width: auto"><spring:message code="label.product.quantity" /></th>
                            <th style="width: auto"><spring:message code="label.product.total" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${mProduct.data}" var="product">
                            <tr>
                                <td>${product.code }</td>
                                <td><img width="100px" src="${product.image}"></td>
                                <td>
                                    <div class="product-details">
                                        <div class="product-title">${product.name}</div>
                                        <p class="product-description">${product.description}</p>
                                    </div>
                                </td>
                                <td><div id="${product.code }-price" class="product-price">${product.price}</div></td>
                                <td>
                                    <div class="product-quantity">
                                        <input id="${product.code }-quantity" type="number" value="0" min="0" onchange="return updateQuantity('${product.code }')">
                                    </div>
                                </td>
                                <td>
                                    <div id="${product.code }-line-price" class="product-line-price">0</div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:forEach items="${mProduct.data}" var="product">
                <input id="${product.code }-order" name="productOrder" class="product-order" type="hidden" value="${product.code} 0 0" />
            </c:forEach>

            <div class="totals">
                <div class="totals-item totals-item-total">
                    <label><spring:message code="label.product.grand.total" /></label>
                    <div class="totals-value" id="cart-total">0</div>
                    <input type="hidden" class="cart-total" name="cartTotal" value="0">
                </div>
            </div>
            <div class="totals">
                <div class="totals-item totals-item-total">
                    <label><spring:message code="label.checkout.cod" /></label>
                    <div class="totals-value">
                        <input style="margin-left: -0.8rem;" value="COD" type="radio" class="form-check-input" name="type">
                    </div>
                </div>
            </div>
            <div class="totals">
                <div class="totals-item totals-item-total">
                    <label><spring:message code="label.checkout.visa" /></label>
                    <div class="totals-value">
                        <input style="margin-left: -0.8rem;" value="VISA" type="radio" class="form-check-input" name="type" checked>
                    </div>
                </div>
            </div>
            <div class="totals">
                <div class="totals-item totals-item-total">
                    <label><spring:message code="label.checkout.visa.gop" /></label>
                    <div class="totals-value">
                        <input style="margin-left: -0.8rem;" value="VISA-GOP" type="radio" class="form-check-input" name="type">
                    </div>
                </div>
            </div>

            <button class="checkout" id="checkout-cart">
                <spring:message code="btn.checkout" />
            </button>
        </form>
    </div>
</div>
<script src="/resources/js/cart.js"></script>
