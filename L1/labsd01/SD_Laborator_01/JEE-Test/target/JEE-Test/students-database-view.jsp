<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<body>
	    <h1>Informatii studenti</h1>
		<%if (request.getAttribute("eroare") != null) {
                out.print("Eroare la citirea bazei de date!" + "<br>");
        }
        else if (request.getAttribute("studenti") != null) {
            java.util.List<String> studenti = (java.util.List<String>) request.getAttribute("studenti");

            if(studenti.isEmpty()) {
                out.print("Nu exista studenti in baza de date" + "<br>");
            } else {
                for (String student : studenti) {
                    out.print(student + "<br>");
                }
            }
        } else {
            out.print("Nu exista studenti in baza de date" + "<br>");
        }%>
	</body>
</html>


