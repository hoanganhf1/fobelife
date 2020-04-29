<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="vi">
<head>
<title><tiles:insertAttribute name="title" /></title>
<tiles:insertAttribute name="header" />
</head>
<body>
    <tiles:insertAttribute name="menu" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
    <tiles:insertAttribute name="modal" />
    <tiles:insertAttribute name="liveChat" />
</body>
</html>