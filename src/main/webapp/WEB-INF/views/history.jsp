<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>

<div class="main" style="padding-top: 40px;">
    <div class="container">
        <spring:message code="label.cart.history.code" var="code"/>
        <spring:message code="label.cart.history.user" var="user"/>
        <spring:message code="label.cart.history.item" var="item"/>
        <spring:message code="label.cart.history.total" var="total" />
        <spring:message code="label.cart.history.payment" var="payment" />
        <spring:message code="label.cart.history.status" var="status" />
        <spring:message code="label.cart.history.ordered.date" var="date"/>
        <display:table class="table" cellpadding="1" cellspacing="1" id="data" name="mHistory.data" 
            requestURI="/cart/history" sort="external">
            <display:column headerScope="col" property="code" title="${code}"/>
            <c:if test="${mHistory.admin}">
                <display:column headerScope="col" property="username" title="${user}"/>
            </c:if>
            <display:column headerScope="col" property="itemsAsString" title="${item}"/>
            <display:column headerScope="col" property="total" title="${total}" sortable="true" sortName="total"/>
            <display:column headerScope="col" property="type" title="${payment}" sortable="true" sortName="type"/>
            <display:column headerScope="col" property="status" title="${status}"/>
            <display:column headerScope="col" property="createdDate" title="${date}" sortable="true" sortName="createdDate"/>
            <c:if test="${mHistory.admin}">
                <display:column headerScope="col" title="ACTION">
<%--                     <c:if test="${data.status ne 'VISA' }"> --%>
<%--                         <button type="button" class="btn btn-primary"><spring:message code="btn.paid" /></button> --%>
<!--                         &nbsp;&nbsp; -->
<%--                     </c:if> --%>
                    <button type="button" class="btn btn-success" onclick="return deliverOrder('${data.id}');"><spring:message code="btn.delivery" /></button>
                </display:column>
            </c:if>
        </display:table>
    </div>
</div>