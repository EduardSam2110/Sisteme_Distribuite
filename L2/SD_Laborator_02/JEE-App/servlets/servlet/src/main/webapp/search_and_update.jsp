<html>
<head>
    <title>Formular student</title>
    <meta charset="UTF-8" />
</head>
<body>
    <h3>Formular student</h3>
    Introduceti datele despre student:
    <form id="studentForm" method="get">
        Id: <input type="text" name="id" />
        <br />
        Nume: <input type="text" name="nume" />
        <br />
        Prenume: <input type="text" name="prenume" />
        <br />
        Varsta: <input type="number" name="varsta" />
        <br /><br />

        <button type="button" onclick="submitForm('search_and_update.jsp')">Cauta</button>

        <button type="button" onclick="submitForm('update-student')">Modifica</button>

        <button type="button" onclick="submitForm('delete-student')">Sterge</button>
    </form>

    <jsp:include page="./fetch-student-list">
        <jsp:param name="nume" value="<%= request.getParameter("nume")%>" />
        <jsp:param name="prenume" value="<%= request.getParameter("prenume")%>" />
        <jsp:param name="varsta" value="<%= request.getParameter("varsta")%>" />
    </jsp:include>


    <script>
        function submitForm(actionUrl) {
            document.getElementById('studentForm').action = actionUrl;
            document.getElementById('studentForm').submit();
        }
    </script>
</body>
</html>
