<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript">
    $(document).ready(function() {
        $('#courseTable').DataTable();
    });
</script>
<div class="main fadeInDown">

    <div class="shopping-cart container"  style="padding-top: 50px;">
        <table id="courseTable" class="table table-striped table-bordered" style="width: 100%">
            <tbody>
                <c:forEach items="${mCourse.data}" var="course">
                    <tr>
                        <td><a href="/training/${course.code }">${course.name }</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </div>

</div>
