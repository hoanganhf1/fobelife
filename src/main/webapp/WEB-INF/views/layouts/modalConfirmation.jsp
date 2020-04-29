<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<!-- Modal -->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmLabel">
                    <spring:message code="modal.title" />
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modal-body">
                <spring:message code="modal.content" />
            </div>
            <!--       <div class="modal-footer" id="modal-footer"> -->
            <%--         <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="modal.cancel" /></button> --%>
            <%--         <button type="button" class="btn btn-primary"><spring:message code="modal.submit" /></button> --%>
            <!--       </div> -->
        </div>
    </div>
</div>