<html>
<head>
    <title>Registration Page | Welcome</title>
    <link rel="stylesheet" type="text/css" href="registerAuth.css">
</head>
<body>

<div id="wholeContainer" onMouseOver="this.style.backgroundColor='#a668ed'" onMouseOut="this.style.backgroundColor='#c899d1'">
    <h1>WELCOME</h1>

    <div id="holdsForms">
        <form method="POST" action="/homework2/register">
            Email:
            <input type="text" id="Lemail" name="email" placeholder="Email..."><br>
            Password:
            <input type="password" id="Lpassword" name="password" placeholder="Password..."><br>
            <button type="submit" value="Register">REGISTER</button><br>
            Do you have an account? <br> If yes, then go to <a href="auth.jsp">"Login"</a>
        </form>
    </div>

</div>
</body>
</html>