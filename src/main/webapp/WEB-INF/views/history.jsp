<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<script type="text/javascript">
    $(document).ready(function() {
        applyDataTable('#historyTable', 500);
    });
</script>
<div class="main" style="padding-top: 40px;">
    <div class="container">
        <table id="historyTable" class="table table-striped table-bordered" style="width: 100%">
            <thead>
                <tr>
                    <th><spring:message code="label.cart.history.code"/></th>
                    <c:if test="${mHistory.admin}">
                        <th><spring:message code="label.cart.history.user"/></th>
                    </c:if>
                    <th><spring:message code="label.cart.history.item"/></th>
                    <th><spring:message code="label.cart.history.total"/></th>
                    <th><spring:message code="label.cart.history.payment"/></th>
                    <c:if test="${mHistory.admin}">
                        <th><spring:message code="label.cart.history.status"/></th>
                    </c:if>
                    <th><spring:message code="label.cart.history.ordered.date"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${mHistory.data}" var="history">
                    <tr>
                        <td>${history.code}</td>
                        <c:if test="${mHistory.admin}">
                            <td>${history.username }</td>
                        </c:if>
                        <td>${history.itemsAsString }</td>
                        <td>${history.total }</td>
                        <td>${history.type }</td>
                         <c:if test="${mHistory.admin}">
                            <td>
                                <button type="button" class="btn btn-success" onclick="return deliverOrder('${data.id}');">
                                    <spring:message code="btn.delivery" />
                                </button>
                            </td>
                        </c:if>
                        <td>${history.createdDate }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>