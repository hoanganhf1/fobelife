<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>

<!-- Modal -->
<div class="modal fade" id="cartReview" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content" style="width: 800px">
            <div class="modal-header">
                <h5 class="modal-title" id="cartReviewTitle"><spring:message code="lable.cart.review.title" /></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
            <table id="cartReviewTable" class="table table-striped table-bordered" style="width: 100%">
                    <thead>
                        <tr>
                            <th style="width: auto"><spring:message code="label.product.code" /></th>
                            <th style="width: 100px"><spring:message code="label.product.image" /></th>
                            <th style="width: auto"><spring:message code="label.product.name" /></th>
                            <th style="width: auto"><spring:message code="label.product.price" /></th>
                            <th style="width: auto"><spring:message code="label.product.quantity" /></th>
                            <th style="width: auto"><spring:message code="label.product.total" /></th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>