<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<script type="text/javascript">
    $(document).ready(function() {
        applyDataTable('#cartReviewTable', 300);
    });
</script>
<!-- Modal -->
<div class="modal fade" id="cartReview" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document" style="max-width: 800px;">
        <div class="modal-content" style="width: 800px">
            <div class="modal-header">
                <h5 class="modal-title" id="cartReviewTitle">
                    <spring:message code="lable.cart.review.title" />
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="product">
                    <table id="cartReviewTable" class="table table-striped table-bordered" style="width: 100%">
                        <thead>
                            <tr>
                                <th><spring:message code="label.product.code" /></th>
                                <c:if test="${fn:contains(mProduct.pageType, 'fobelife')}">
                                    <th><spring:message code="label.product.image" /></th>
                                </c:if>
                                <th><spring:message code="label.product.name" /></th>
                                <th><spring:message code="label.product.price" /></th>
                                <th><spring:message code="label.product.quantity" /></th>
                                <th><spring:message code="label.product.total" /></th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="modal.cancel" /></button>
                <button type="button" class="btn btn-primary"><spring:message code="btn.checkout" /></button>
            </div>
        </div>
    </div>
</div>