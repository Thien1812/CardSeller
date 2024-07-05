

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <style>
            body {
                background-image: url(image/signup/cardTransfer.jpg);
                background-size: 100%;
            }
            .login {
                margin: 0 auto;
                width:40%;
                margin-top:50px;
                width: 420px;
                height: 673px;
                padding: 8px;
                border-width: 1px;
                border-style: solid;
                border-radius:10px;
                border-color: #7C4DFF;
                background: #FFFFFF;
                text-align: center;
            }
            h3{
                font-family: "Inter";
                font-weight: 700;
                font-size: 26px;
                line-height: 1;
                letter-spacing: -0.2px;
                margin-top: 40px;
                margin-bottom: 40px;
            }
            h6{
                font-family: "Inter";
                font-weight: 400;
                font-size: 14px;
                line-height: 1.3;
                margin-top: 10px;
                text-align: center;
            }
            .text-input {
                width: 300px;
                height: 48px;
                background: #FFFFFF;
                color: #444444;
                border-color: #EBEBEB;
                border-style: solid;
                border-radius: 6px;
                margin-bottom: 5px;
                margin-left: auto;
                margin-right: auto;
                margin-top: 10px;

            }
            .rem {
                font-family: "Inter";
                width: 300px;
                height: 48px;
                outline:none;
                border: none;
                margin-bottom: 5px;
                margin-left: auto;
                margin-right: auto;
                text-align: right;
            }
            .text-input input{
                outline:none;
                border: none;
                font-family: "Inter";
                font-weight: 500;
                font-size: 14px;
                line-height: 1.3;
                width: 250px;
                height: 40px;
                border-radius: 6px;
                padding-left:15px;
            }

            .icon{
                width: 21px;
                height: 21px;
                object-fit: cover;
                vertical-align: middle;
            }
            button{
                width: 300px;
                height: 48px;
                background:#7C4DFF;
                border-color:#7C4DFF;
                border-width: 1px;
                border-style: solid;
                border-radius: 9px;
                font-family: "Inter";
                font-weight: 600;
                font-size: 16px;
                color: #FFFFFF;
                text-align: center;
                margin-top: 10px;
            }
            .warning{
                outline:none;
                border: none;
                font-family: "Inter";
                font-weight: 500;
                font-size: 14px;
                line-height: 1.3;
                width: 300px;
                height: 20px;
                border-radius: 6px;
                text-align: center;
            }

        </style>

    </head>
    <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    if(request.getSession().getAttribute("acc")!=null){
        response.sendRedirect("home");
        }
    %>
    <body>
        <div class="container" >
            <div class="login">
                <h3>Login</h3>
                <form action="login" method="post">
                    <div class ="text-input">
                        <img class="icon" src="image/signup/user.png"/>
                        <input  type="text" name="user" placeholder="Username" value="${requestScope.user}" required/>
                    </div>

                    <div class ="text-input" style="margin-bottom: 30px">
                        <img class="icon"  src="image/signup/lock.png"/>
                        <input  type="password" name="pass" placeholder="Password" value="${requestScope.pass}" required/>
                    </div>
                    <img src="/CardSeller/captcha" id="captcha"/>
                    <img src="image/reload.jpg" onclick="reloadCaptcha()" width="40" height="40"/>
                    <br>
                    <div class="text-input">
                        <img class="icon"  src="image/signup/check.png"/>
                        <input name="answer" required placeholder="Enter captcha"/>
                    </div>
                    <input class="warning" style="color: red"  type="text" readonly value="${requestScope.mess}"/>
                    <button type="submit" class="">Login</button>
                    <h6> <a href="forgot-password.jsp" style="color:#7C4DFF; text-decoration: underline;">Forgot your password?</a></h6>
                    <h6> <a href="signup.jsp" style="color:#7C4DFF; text-decoration: underline;">Create a new account</a></h6>
                </form>
            </div> 
        </div>
    </body>
    <script type="text/javascript">
        function reloadCaptcha() {
            document.getElementById('captcha').src = 'captcha?' + new Date().getTime();
        }

        window.onload = function () {
            // Reload the CAPTCHA image
            reloadCaptcha();
        };
    </script>
</body>
</html>
