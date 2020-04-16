<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>

<div class="container">
    <div class="card" style="margin-top: 50px;">
        <h5 class="card-header">Product import</h5>
        <div class="card-body">
            <form method="post" action="/product/import" enctype="multipart/form-data">
                <div class="form-row">
                  <div class="form-group col-md-2">
                        <button id="btnImport" name="btnImport" type="submit" class="btn btn-primary form-control">Import</button>
                    </div>
                    <div class="custom-file form-group col-md-5">
                        <input type="file" class="custom-file-input form-control" id="file" name="file"> <label class="custom-file-label" for="customFile">Choose File</label>
                    </div>
                    <div class="form-group col-md-2">
                        <a target="_blank" href="/resources/docs/ImportProduct.csv.zip"><spring:message code="lable.download.template" /></a>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="card" style="margin-top: 50px;">
        <h5 class="card-header">Training import</h5>
        <div class="card-body">
            <form method="post" action="/training/import" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <button id="btnImport" name="btnImport" type="submit" class="btn btn-primary form-control">Import</button>
                    </div>
                    <div class="custom-file form-group col-md-5">
                        <input type="file" class="custom-file-input form-control" id="file" name="file"> <label class="custom-file-label" for="customFile">Choose File</label>
                    </div>
                    <div class="form-group col-md-2">
                        <a target="_blank" href="/resources/docs/ImportTraining.csv.zip"><spring:message code="lable.download.template" /></a>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="card" style="margin-top: 50px;">
        <h5 class="card-header">User import</h5>
        <div class="card-body">
            <form method="post" action="/user/import" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <button id="btnImport" name="btnImport" type="submit" class="btn btn-primary form-control">Import</button>
                    </div>
                    <div class="custom-file form-group col-md-5">
                        <input type="file" class="custom-file-input form-control" id="file" name="file"> <label class="custom-file-label" for="customFile">Choose File</label>
                    </div>
                    <div class="form-group col-md-2">
                        <a target="_blank" href="/resources/docs/ImportProduct.csv.zip"><spring:message code="lable.download.template" /></a>
                    </div>
                </div>
            </form>
        </div>
    </div>

     <div class="card" style="margin-top: 50px;">
        <h5 class="card-header">News import</h5>
        <div class="card-body">
            <form method="post" action="/news/import" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <button id="btnImport" name="btnImport" type="submit" class="btn btn-primary form-control">Import</button>
                    </div>
                    <div class="custom-file form-group col-md-5">
                        <input type="file" class="custom-file-input form-control" id="file" name="file"> <label class="custom-file-label" for="customFile">Choose File</label>
                    </div>
                    <div class="form-group col-md-2">
                        <a target="_blank" href="/resources/docs/ImportProduct.csv.zip"><spring:message code="lable.download.template" /></a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>