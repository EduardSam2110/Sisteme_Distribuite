<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<body>
	    <h1>Informatii studenti</h1>
	    <form method="post" action="./read-student-db">
            <input type="text" name="keyword" value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
            <button type="submit">Cauta</button>
        </form>
        <ul>
		<%if (request.getAttribute("eroare") != null) {

                out.print("<li>Eroare la citirea bazei de date!</li>" + "<br>");
        }
        else if (request.getAttribute("studenti") != null) {
            java.util.List<String> studenti = (java.util.List<String>) request.getAttribute("studenti");

            if(studenti.isEmpty()) {
                out.print("<li>Nu exista studenti in baza de date</li>" + "<br>");
            } else {
                for (String student : studenti) {
                    out.print("<li>" + student + "</li><br>");
                }
            }
        } else {
            out.print("<li>Nu exista studenti in baza de date</li>" + "<br>");
        }%>
        </ul>
        <form method="post" action="./export-student-db">
            <input type="hidden" name="keyword" value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
            <button type="submit">Export JSON</button>
        </form>
        <br>
        <form method="post" action="./update-student-db">
            <button type="submit">Modifica studentul dupa id:</button><br>
            Id: <input type="number" name="idStudent"><br>
            Nume: <input type="text" name="numeStudent" placeholder="nume nou"><br>
            Prenume: <input type="text" name="prenumeStudent" placeholder="prenume nou"><br>
            Varsta: <input type="number" name="varstaStudent" placeholder="varsta noua"><br>
        </form>
        <br>
        <form method="post" action="./delete-student-db">
            <button type="submit">Sterge studentul cu id-ul:</button>
            Id: <input type="number" name="idStudent"><br>
        </form>
	</body>
</html>


