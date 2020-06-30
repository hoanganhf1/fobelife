<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript">
    $(document).ready(function() {
        $('#newsTable').DataTable();
    });
</script>
<div class="main fadeInDown">

    <div class="shopping-cart container" style="padding-top: 50px;">
        <div style="text-align: center;">
            <h1>${mNews.subject }</h1>
        </div>
        <br>

        <c:forEach items="${mNews.contentList}" var="content">
            <div>
            <c:choose>
                <c:when test="${fn:startsWith(content, 'http')}">
                    <img alt="" src="${content }">
                </c:when>
                <c:otherwise>
                    <span>${content}</span>
                </c:otherwise>
            </c:choose>
            </div>
        </c:forEach>
    </div>
</div>