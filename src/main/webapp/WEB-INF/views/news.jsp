<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript">
    $(document).ready(function() {
        $('#newsTable').DataTable();
    });
</script>
<div class="main fadeInDown">

    <div class="shopping-cart container"  style="padding-top: 50px;">
        <table id="newsTable" class="table table-striped table-bordered" style="width: 100%">
            <tbody>
                <c:forEach items="${mNews.data}" var="news">
                    <tr>
                        <td><a href="/news/${news.code }">${news.name }</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>