<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<body>
	    <h1>Informatii studenti</h1>
	    <form method="GET" action="./read-student-db">
            <input type="text" placeholder="Cauta student" name="keyword">
            <button type="submit">Cauta</button>
        </form>
		<%if (request.getAttribute("eroare") != null) {
                out.print("Eroare la citirea bazei de date!" + "<br>");
        }
        else if (request.getAttribute("studenti") != null) {
            java.util.List<String> studenti = (java.util.List<String>) request.getAttribute("studenti");

            if(studenti.isEmpty()) {
                out.print("Nu exista studenti in baza de date" + "<br>");
            } else {
                String keyword = request.getParameter("keyword");
                boolean gasit = false;
                for (String student : studenti) {
                    if(keyword != null && student.contains(keyword))
                        out.print(student + "<br>");
                        gasit = true;
                }

                if(gasit == false)
                    out.print("Nu s-a gasit niciun student " + "<br>");
            }
        } else {
            out.print("Nu exista studenti in baza de date" + "<br>");
        }%>
	</body>
</html>


