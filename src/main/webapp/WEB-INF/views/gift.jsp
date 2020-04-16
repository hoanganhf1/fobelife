<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript">
    $(document).ready(function() {
        $('#giftTable').DataTable();
    });
</script>
<div class="main fadeInDown" style="padding-top: 50px;">

    <div class="shopping-cart container">
        <div>
            <h5>
                <spring:message code="label.gift.point.title" />
                ${mGift.point}
            </h5>
        </div>
        <table id="giftTable" class="table table-striped table-bordered" style="width: 100%">
            <thead>
                <tr>
                    <th style="width: auto"><spring:message code="label.gift.code" /></th>
                    <th style="width: 100px"><spring:message code="label.gift.image" /></th>
                    <th style="width: auto"><spring:message code="label.gift.name" /></th>
                    <th style="width: auto"><spring:message code="label.gift.point" /></th>
                    <th style="width: auto"><spring:message code="label.gift.submit" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${mGift.data}" var="product">
                    <tr>
                        <td>${product.code }</td>
                        <td><img width="100px" src="${product.image}"></td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>
                            <button ${mGift.point >= product.price ? '':'disabled'}
                                class="btn ${mGift.point >= product.price ? 'btn-success':'btn-secondary'}"
                                onclick="return submitGift('${product.code}');">
                                <spring:message code="label.gift.submit" />
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th><spring:message code="label.gift.code" /></th>
                    <th><spring:message code="label.gift.image" /></th>
                    <th><spring:message code="label.gift.name" /></th>
                    <th><spring:message code="label.gift.point" /></th>
                    <th><spring:message code="label.gift.submit" /></th>
                </tr>
            </tfoot>
        </table>


    </div>

</div>
