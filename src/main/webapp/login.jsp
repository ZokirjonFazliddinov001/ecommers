<%--
  Created by IntelliJ IDEA.
  User: murod
  Date: 4/6/2024
  Time: 5:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="static/bootstrap-grid.css" rel="stylesheet">
    <link href="static/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="row pt-4">
    <div class="col-4 offset-4">
        <div class="card">
            <div class="card-header bg-dark text-white">
                Login
            </div>
            <div class="card-body p-2">
                <form action="/auth/login" method="post">
                    <div>
                     <input class="form-control" name="username" type="text" placeholder="Username">
                    </div>
                    <div>
                     <input class="form-control my-2" name="password" type="text" placeholder="Password">
                    </div>
                    <label class="form-check-label mt-2">
                        Remember me
                        <input name="rememberMe" class="form-check" type="checkbox">
                    </label>
                    <button class="btn btn-dark"> sign in </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
