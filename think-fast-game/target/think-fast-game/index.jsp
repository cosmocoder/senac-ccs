<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Think Fast Game</title>
        <script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="resources/js/knockout-2.0.0.js"></script>
    </head>
    <body>
        <h1>Think Fast Game</h1>
        <div id="participant">
            <h2>Insert your name and click start to begin:</h2>
            <input type="text" name="participant" />
            <input type="button" value="start" />
        </div>
        <br/>
        <div id="survey">
            <span>Qual a capital da Rússia?</span>
            <ul>
                <li style="list-style: none;">
                    <input type="radio" name="answer"/>
                    <span>Moscou</span>
                </li>
            </ul>
            <span id="message"></span>
        </div>
    </body>
</html>