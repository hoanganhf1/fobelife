<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<sec:authorize access="hasAuthority('ADMIN')">
    <link rel="stylesheet" href="/resources/css/product.css">
    <div class="container" style="margin-top: 100px;">
        <form method="post" action="/training/import" enctype="multipart/form-data">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <button id="btnImport" name="btnImport" type="submit" class="btn btn-primary form-control">Import</button>
                </div>
                <div class="custom-file form-group col-md-5">
                    <input type="file" class="custom-file-input form-control" id="file" name="file"> <label class="custom-file-label"
                        for="customFile">Choose File</label>
                </div>
                <div class="form-group col-md-2">
                    <a target="_blank" href="/resources/docs/ImportTraining.csv.zip"><spring:message code="lable.download.template" /></a>
                </div>
            </div>
        </form>
    </div>
</sec:authorize>
<div class="container" style="padding-top: 50px;">
    <h5>
        <spring:message code="label.training.numberOfPassed" />${mTraining.numberOfPassed}</h5>
    <h5>
        <spring:message code="label.training.numberOfFailed" />${mTraining.numberOfFailed}</h5>
    <h5>
        <spring:message code="label.training.numberOfAvailable" />${mTraining.numberOfAvailable}</h5>
</div>

<div id="accordion" class="container">
    <form action="/training" method="POST">
        <c:forEach items="${mTraining.data }" var="question">
            <div class="card">
                <div class="card-header" id="heading-${question.code}">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${question.code}" aria-expanded="true"
                            aria-controls="collapseOne">${question.content}</button>
                        <!--                         <span id="answer" class="btn btn-link" style="float: right;">A</span> -->
                    </h5>
                </div>

                <div id="collapse${question.code}" class="collapse show" aria-labelledby="heading${question.code}" data-parent="#accordion">
                    <div class="card-body">
                        <c:forEach items="${question.options }" var="option">
                            <c:set var="oClass" value="" />
                            <c:if test="${question.userAnswer eq option.code}">
                                <c:set var="oClass" value="btn-danger" />
                                <c:if test="${question.userAnswer eq question.answer}">
                                    <c:set var="oClass" value="btn-success" />
                                </c:if>
                            </c:if>
                            <div class="custom-control custom-radio ${oClass}">
                                <input type="radio" id="${option.code}" value="${option.code}" name="${question.code}" class="custom-control-input"
                                    ${not empty question.userAnswer ? 'disabled' : '' } ${question.userAnswer eq option.code ? 'checked' : '' }>
                                <label class="custom-control-label" for="${option.code}">${option.content }</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${mTraining.numberOfAvailable > 0 }">
            <button class="btn btn-success" style="margin-top: 50px; float: right;">
                <spring:message code="btn.training.submit" />
            </button>
        </c:if>
    </form>
</div>
