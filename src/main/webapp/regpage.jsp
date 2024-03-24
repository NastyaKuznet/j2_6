<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
</head>
<body>
    <form action="registration" method="POST">
        Логин: <input type="text" name="login"/>
        E-mail: <input type="text" name="email"/>
        Пароль: <input type="password" name="pass"/>
        <input type="submit" value="Зарегистрироваться"/>
    </form>
    <a href="/">Войти, если уже зарегистрирован</a>
</body>
</html>