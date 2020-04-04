<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="/resources/css/login.css">
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="https://fobelife.com/wp-content/uploads/2018/03/logo_fobelife_200x70.png" id="icon" alt="User Icon" />
        </div>

        <!-- Login Form -->
        <form action="/login" method="POST">
            <input name="username" placeholder="<spring:message code="input.username.placeholder" />" class="fadeIn second" type="text"/>
            <input name="password" placeholder="<spring:message code="input.password.placeholder" />" class="fadeIn third" type="password"/>
            <input name="submit" class="fadeIn fourth" type="submit" value="LOGIN"/>
        </form>

        <!-- Remind Password -->
        <div id="formFooter">
            <span class="invisible">Invalid email/password</span>
        </div>

    </div>
</div>