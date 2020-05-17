<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
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
                                    ${question.userAnswer eq question.answer ? 'disabled' : '' } ${question.userAnswer eq option.code ? 'checked' : '' }>
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
