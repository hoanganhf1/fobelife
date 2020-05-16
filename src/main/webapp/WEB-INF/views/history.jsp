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
                    <th><spring:message code="label.cart.history.status"/></th>
                    <th><spring:message code="label.cart.history.ordered.date"/></th>
                    <th><spring:message code="label.cart.history.ordered.note"/></th>
                    <c:if test="${mHistory.admin}">
                        <th><spring:message code="label.cart.history.status.update"/></th>
                    </c:if>
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
                        <td><div style=" text-align: right; ">
                            <fmt:formatNumber value="${history.total }" type="currency" currencySymbol="VND" />
                            </div>
                        </td>
                        <td>${history.type }</td>
                        <td>${history.statusDesc }</td>
                        <td>${history.createdDate }</td>
                        <td>${history.note }</td>
                        <c:if test="${mHistory.admin}">
                            <td>
                                <button type="button" class="btn btn-success" onclick="return updateStatus('${history.id}', 'DELIVERED');">
                                    <spring:message code="btn.delivery" />
                                </button>
                                &nbsp;
                                <button type="button" class="btn btn-primary" onclick="return updateStatus('${history.id}', 'PAID');">
                                    <spring:message code="btn.paid" />
                                </button>
                                &nbsp;
                                <button type="button" class="btn btn-danger" onclick="return updateStatus('${history.id}', 'CANCEL');">
                                    <spring:message code="btn.cancel" />
                                </button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>