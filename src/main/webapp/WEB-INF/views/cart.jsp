<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<div class="main fadeInDown">
<%--
    <div class="shopping-cart container">
        <display:table name="${mProduct.data}" pagesize="10" id="myTable" sort="list">
            <display:column class="prodcut product-image" title="image" headerClass="column-labels product-image">
                <img src="${myTable.image}" />
            </display:column>
            <display:column class="prodcut product-details" title="name" headerClass="column-labels product-details">
                <div class="product-title">${myTable.name}</div>
                <p class="product-description">${myTable.description}</p>
            </display:column>
            <display:column class="prodcut product-price" title="price" property="price" headerClass="column-labels product-price"/>
            <display:column class="prodcut product-quantity" title="qua" headerClass="column-labels product-quantity"/>
            <display:column class="prodcut product-removal" title="remove" headerClass="column-labels product-removal">
                <button class="remove-product">remove</button>
            </display:column>
            <display:column class="prodcut product-line-price" title="total" headerClass="column-labels product-line-price"/>
        </display:table>
    </div>
--%>
    <div class="shopping-cart container">

        <div class="column-labels">
        <c:if test="${mProduct.pageType ne 'chosi' }">
            <label class="product-image"><spring:message code="label.product.image" /></label>
        </c:if>
        <c:if test="${mProduct.pageType eq 'chosi' }">
            <label class="product-image"><spring:message code="label.product.code" /></label>
        </c:if>
            <label class="product-details"><spring:message code="label.product.name" /></label>
            <label class="product-price"><spring:message code="label.product.price" /></label>
            <label class="product-quantity"><spring:message code="label.product.quantity" /></label>
            <label class="product-removal"><spring:message code="label.product.remove" /></label>
            <label class="product-line-price"><spring:message code="label.product.total" /></label>
        </div>
        <form action="/cart" method="post">
                <c:forEach items="${mProduct.data}" var="product">
                    <div class="product">
                        <input name="productCode" class="product-code" type="hidden" value="${product.code}" />
                        <input name="productOrder" class="product-order" type="hidden" value="${product.code} 0" />
                        
                        <div class="product-image">
                            <c:if test="${mProduct.pageType ne 'chosi' }">
                                <img src="${product.image}">
                            </c:if>
                            <c:if test="${mProduct.pageType eq 'chosi' }">
                                ${product.code}
                            </c:if>
                        </div>
                        <div class="product-details">
                            <div class="product-title">${product.name}</div>
                            <p class="product-description">${product.description}</p>
                        </div>
                        <div class="product-price">${product.price}</div>
                        <div class="product-quantity">
                            <input type="number" value="0" min="0">
                        </div>
                        <div class="product-removal">
                            <button class="remove-product">
                                <spring:message code="btn.remove" />
                            </button>
                        </div>
                        <div class="product-line-price">0</div>
                    </div>
                </c:forEach>
                <%--
                <div class="product">
                    <div class="product-image">
                        <img src="/resources/images/nike.jpg">
                    </div>
                    <div class="product-details">
                        <div class="product-title">Nike Flex Form TR Women's Sneaker</div>
                        <p class="product-description">It has a lightweight, breathable mesh upper with forefoot cables for a locked-down fit.</p>
                    </div>
                    <div class="product-price">12.99</div>
                    <div class="product-quantity">
                        <input type="number" value="1" min="1">
                    </div>
                    <div class="product-removal">
                        <button class="remove-product">Remove</button>
                    </div>
                    <div class="product-line-price">25.98</div>
                </div>
        --%>
        
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.product.grand.total" /></label>
                        <div class="totals-value" id="cart-total">0</div>
                        <input type="hidden" class="cart-total" name="cartTotal">
                    </div>
                </div>
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.checkout.visa" /></label>
                        <div class="totals-value"><input style="margin-left: -0.8rem; " value="VISA" type="radio" class="form-check-input" name="type" checked></div>
                    </div>
                </div>
                <div class="totals">
                    <div class="totals-item totals-item-total">
                        <label><spring:message code="label.checkout.cod" /></label>
                        <div class="totals-value"><input style="margin-left: -0.8rem; " value="COD" type="radio" class="form-check-input" name="type"></div>
                    </div>
                </div>

                <button class="checkout" id="checkout-cart">
                    <spring:message code="btn.checkout" />
                </button>
        </form>
    </div>
</div>
<script src="/resources/js/cart.js"></script>
