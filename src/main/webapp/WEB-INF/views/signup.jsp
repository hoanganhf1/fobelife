<%@ include file="/WEB-INF/views/layouts/tagLibs.jsp"%>

<!-- Font Icon -->
<link rel="stylesheet" href="/resources/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="/resources/css/style.css">


<div class="main fadeInDown">
    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <form method="POST" class="register-form" id="register-form" action="/user/signup">
                        <div class="form-group">
                            <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="username" id="username" placeholder="User Name" required/>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label>
                            <input type="email" name="email" placeholder="Your Email"/>
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" placeholder="Password" required/>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" class="form-submit" value="Register" />
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure>
                        <img src="/resources/images/signup-image.jpg" alt="sing up image">
                    </figure>
                </div>
            </div>
        </div>
    </section>
</div>
