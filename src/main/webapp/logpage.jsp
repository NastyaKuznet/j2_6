<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
</head>
<body>
    <form action="log" method="POST">
        Логин: <input type="text" name="login"/>
        Пароль: <input type="password" name="pass"/>
        <input type="submit" value="Войти"/>
    </form>
    <a href="/registration">Зарегистрироваться</a>
</body>
</html>