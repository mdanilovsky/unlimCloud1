<%-- 
    Document   : index
    Created on : 12.05.2013, 12:48:21
    Author     : fyntom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>ZeonDrive</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css" media="screen" />
    </head>
    <body>
        <div id="wrapper">
            <div id="logo">
                <h1>ZeonDrive</h1>                
            </div>
            <div id="freespace">              
                     <h2><b> <c:out value="${freespace}" /> Gb </b> - свободного места</h2>  
            </div>
            <ul id="menu">
                <li><a class="current" href="index.jsp">Рабочий стол</a></li>
                <li><a href="http://artnek.org.ru" target="_blank">Контакты</a></li>
            </ul>
            <div class="column">
                <h1><span class="number">1</span> Регистрация на сервисе Zeon Drive</h1>
                <p>Что бы регистрация прошла успешно, заполните все поля и нажмите на
                    кнопку</p>

                <c:if test="${notif ne null}">
                    <div class="notif">
                        <span><c:out value="${notif}" /></span> 
                    </div>
                </c:if>
                <form method="POST" action="signin">
                    <table width="100%" border="0">
                        <tr>
                            <td><label for="email">E-Mail*</label></td>
                            <td><input type="email" name="email" id="email"/></td>
                             <td></td>
                        </tr>
                        <tr>
                            <td><label for="password">Пароль*</label></td>
                            <td><input type="password" name="password" id="password"/></td>
                            <td><button type="submit" class="button" name="signin">Войти</button></td>
                        </tr>
                        <tr>
                            <td><label for="password2">Повторите пароль*</label></td>
                            <td><input type="password" name="password2" id="password2"/></td>
                            <td><button type="submit" class="button" name="reg">Зарегистрироваться</button></td>
                        </tr>
                        
                    </table>
                </form>
            </div>
            <div class="column">
                <h1><span class="number">2</span> Заливаем файлы</h1>
                <form method="POST" action="upload">
                    <table width="100%" border="0">
                        <tr>
                            <td><input type="file" name="file" id="file"/></td>
                            <td><button type="submit" class="button" name="upload">Загрузить</button></td>
                        </tr>                        
                    </table>
                </form>
            </div>
            <div class="column">
                <h1><span class="number">3</span> Синхронизируем файлы</h1>
                <p>Скачать приложение можно  <a href="http://fsfrf.ru/">по ссылке</a> </p>
            </div>
            <div class="clear"></div>
            <div class="half">
                <h2>Lorem Ipsum</h2>
                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</p>
                <p>Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                <p class="date">September 9th &middot; <a href="http://booknew.nm.ru/">Comment(11)</a></p>
            </div>
            <div class="clear"></div>
            <div class="news">Запуск сервиса
                <p>Мы планируем показать рабочий сервис к середине июля. Это будет интересно.</p>
            </div> 
            <div id="footer">
                <p>&copy; ZeonDrive &middot; Artur Nek</p>
            </div>
        </div>
    </body>
</html>
