<html>
    <body>
        <h2>Students Database!</h2>
        <p>
            <%out.print(request.getAttribute("message") + "<br>");%>
        </p>
        <br/>
        <p>
            <a href="./formular-db.jsp">Formular student</a>
        </p>
        <br/>
        <p>
            <a href="./read-student-db">Vizualizare studenti</a>
        </p>
        <br/>
    </body>
</html>
