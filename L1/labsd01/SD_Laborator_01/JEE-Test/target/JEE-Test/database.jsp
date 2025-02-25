<html>
    <body>
        <h2>Students Database!</h2>
        <p>
            <%= request.getAttribute("message") != null ? request.getAttribute("message") : "No message available." %>
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
        <p>
            <a href="">Export JSON</a>
        </p>
    </body>
</html>
