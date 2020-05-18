<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript">
    $(document).ready(function() {
        applyDataTable('#cartTable', 500);
        if ($('#note').val()) {
            $('#note').val($('#note').val().trim());
        }
    });
</script>
<div class="main fadeInDown">
    <div class="shopping-cart container" style=" margin-top: -100px; ">
        <c:if test="${currentPage eq 'gift'}">
            <div>
                <h5>
                    <spring:message code="label.gift.point.title" />
                    <span class="total-point">${mProduct.point}</span>
                </h5>
                <h5>
                    <spring:message code="label.gift.point.used" />
                    <span class="total-point-used">0</span>
                </h5>
            </div>
        </c:if>
        <form action="/cart" method="post">
            <input type="hidden" id="pointUsed" name="pointUsed" value="${mProduct.pointUsed }">
            <div class="product">
                <table id="cartTable" class="table table-striped table-bordered" style="width: 100%">
                    <thead>
                        <tr style="text-align: center;">
                            <th><spring:message code="label.product.code" /></th>
                            <c:if test="${fn:contains(mProduct.pageType, 'fobelife')}">
                                <th><spring:message code="label.product.image" /></th>
                            </c:if>
                            <th><spring:message code="label.product.name" /></th>
                            <th><spring:message code="label.product.price" /></th>
                            <th><spring:message code="label.product.quantity" /></th>
                            <c:if test="${currentPage eq 'gift'}">
                            <th><spring:message code="label.product.bonus" /></th>
                            </c:if>
                            <th><spring:message code="label.product.total" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${mProduct.data}" var="product">
                            <tr>
                                <td><div class="product-code">${product.code }</div></td>
                                <c:if test="${fn:contains(mProduct.pageType, 'fobelife')}">
                                    <td><img width="100px" src="${product.image}"></td>
                                </c:if>
                                <td>
                                    <div class="product-details">
                                        <div class="product-title">${product.name}</div>
                                        <p class="product-description">${product.description}</p>
                                    </div>
                                </td>
                                <td><div id="${product.code }-price" class="product-price">
                                        <fmt:formatNumber pattern="###,###" value="${product.price}" type="currency" currencySymbol="" />
                                    </div></td>
                                <td>
                                    <div class="product-quantity">
                                        <input id="${product.code }-quantity" type="number" value="${product.quantity }" min="0" step="${product.step}" onchange="return updateQuantity('${product.code }')">
                                    </div>
                                </td>
                                <c:if test="${currentPage eq 'gift'}">
                                <td>
                                    <div class="product-point">
                                        <input id="${product.code }-point" type="number" value="0" min="0" onchange="return updateBonus('${product.code }', '${product.bonus }')">
                                    </div>
                                </td>
                                </c:if>
                                <td>
                                    <div id="${product.code }-line-price" class="product-line-price" >
                                        <fmt:formatNumber pattern="###,###" value="${product.total }" type="currency" currencySymbol=""/>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:forEach items="${mProduct.data}" var="product">
                <input id="${product.code}-order" name="productOrder" class="product-order" type="hidden" value="${product.code} ${product.quantity } ${product.total }" />
            </c:forEach>

            <div class="totals">
                <div class="totals-item totals-item-total">
                    <label><spring:message code="label.product.grand.total" /></label>
                    <div class="totals-value" id="cart-total">
                        <fmt:formatNumber pattern="###,###" value="${mProduct.total}" type="currency" currencySymbol=""/>
                    </div>
                    <input type="hidden" class="cart-total" name="cartTotal" value="${mProduct.total}">
                </div>
            </div>
            <c:if test="${fn:contains(mProduct.pageType, 'review')}">
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.checkout.cod" /></label>
                        <div class="totals-value">
                            <input style="margin-left: -0.8rem;" value="COD" type="radio" class="form-check-input" name="paymentType">
                        </div>
                    </div>
                </div>
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.checkout.visa" /></label>
                        <div class="totals-value">
                            <input style="margin-left: -0.8rem;" value="VISA" type="radio" class="form-check-input" name="paymentType" checked>
                        </div>
                    </div>
                </div>
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.checkout.visa.gop" /></label>
                        <div class="totals-value">
                            <input style="margin-left: -0.8rem;" value="VISA-GOP" type="radio" class="form-check-input" name="paymentType">
                        </div>
                    </div>
                </div>
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.checkout.note" /></label>
                        <div class="totals-value">
                            <textarea name="note" id="note"></textarea>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${!fn:contains(mProduct.pageType, 'review')}">
                <button class="checkout" id="checkout-cart" disabled>
                    <spring:message code="btn.cart.review" />
                </button>
            </c:if>
            <c:if test="${fn:contains(mProduct.pageType, 'review')}">
                <button class="checkout" id="checkout-cart">
                    <spring:message code="btn.checkout" />
                </button>
            </c:if>
        </form>
    </div>
</div>
<tiles:insertAttribute name="review" />
<script src="/resources/js/cart.js"></script>
