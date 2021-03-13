<html>
<head>
    <title>Authentication Page | Login</title>
    <link rel="stylesheet" type="text/css" href="registerAuth.css">
</head>
<body>

<div id="wholeContainer" onMouseOver="this.style.backgroundColor='#a668ed'" onMouseOut="this.style.backgroundColor='#c899d1'">
    <h1>LOGIN</h1>

    <div id="holdsForms">
        <form method="POST" action="/homework2/auth">
            Email:
            <input type="text" id="femail" name="emails" placeholder="Email..."><br>
            Password:
            <input type="password" id="fpassword" name="passwords" placeholder="Password..."><br>
            <button type="submit" value="Login">LOGIN</button>
        </form>
    </div>

</div>
</body>
</html>