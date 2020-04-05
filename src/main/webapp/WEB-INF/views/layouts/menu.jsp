<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item ${currentPage eq 'fobelife' ? 'active' : ''}"><a class="nav-link" href="/cart/fobelife"><spring:message code="label.menu.home" /></a></li>
        <li class="nav-item ${currentPage eq 'chosi' ? 'active' : ''}"><a class="nav-link" href="/cart/chosi"><spring:message code="label.menu.cho_si" /></a></li>
        <sec:authorize access="hasAuthority('ADMIN')">
            <li class="nav-item ${currentPage eq 'signup' ? 'active' : ''}"><a class="nav-link" href="/user/signup"><spring:message
                        code="label.menu.signup" /></a></li>
            <li class="nav-item ${currentPage eq 'product' ? 'active' : ''}"><a class="nav-link" href="/product"><spring:message
                        code="label.menu.manage.product" /></a></li>
        </sec:authorize>
        <li class="nav-item ${currentPage eq 'history' ? 'active' : ''}"><a class="nav-link" href="/cart/history"><spring:message
                    code="label.menu.history" /></a></li>
        <li class="nav-item ${currentPage eq 'news' ? 'active' : ''}"><a class="nav-link" href="#"><spring:message
                    code="label.menu.news" /></a></li>
        <li class="nav-item ${currentPage eq 'training' ? 'active' : ''}"><a class="nav-link" href="/training"><spring:message
                    code="label.menu.training" /></a></li>
        <li class="nav-item ${currentPage eq 'gift' ? 'active' : ''}"><a class="nav-link" href="/gift"><spring:message
                    code="label.menu.gift" /></a></li>
        <li class="nav-item ${currentPage eq 'logout' ? 'active' : ''}"><a class="nav-link" href="/logout"><spring:message
                    code="label.menu.logout" /></a></li>
    </ul>
</nav>