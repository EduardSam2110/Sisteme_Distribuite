package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class students_002ddatabase_002dview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html xmlns:jsp=\"http://java.sun.com/JSP/Page\">\n");
      out.write("\t<body>\n");
      out.write("\t    <h1>Informatii studenti</h1>\n");
      out.write("\t    <form method=\"post\" action=\"./read-student-db\">\n");
      out.write("            <input type=\"text\" name=\"keyword\" value=\"");
      out.print( request.getParameter("keyword") != null ? request.getParameter("keyword") : "" );
      out.write("\">\n");
      out.write("            <button type=\"submit\">Cauta</button>\n");
      out.write("        </form>\n");
      out.write("        <ul>\n");
      out.write("\t\t");
if (request.getAttribute("eroare") != null) {

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
        }
      out.write("\n");
      out.write("        </ul>\n");
      out.write("        <form method=\"post\" action=\"./export-student-db\">\n");
      out.write("            <input type=\"hidden\" name=\"keyword\" value=\"");
      out.print( request.getParameter("keyword") != null ? request.getParameter("keyword") : "" );
      out.write("\">\n");
      out.write("            <button type=\"submit\">Export JSON</button>\n");
      out.write("        </form>\n");
      out.write("        <br>\n");
      out.write("        <form method=\"post\" action=\"./update-student-db\">\n");
      out.write("            <button type=\"submit\">Modifica studentul dupa id:</button><br>\n");
      out.write("            Id: <input type=\"number\" name=\"idStudent\"><br>\n");
      out.write("            Nume: <input type=\"text\" name=\"numeStudent\" placeholder=\"nume nou\"><br>\n");
      out.write("            Prenume: <input type=\"text\" name=\"prenumeStudent\" placeholder=\"prenume nou\"><br>\n");
      out.write("            Varsta: <input type=\"number\" name=\"varstaStudent\" placeholder=\"varsta noua\"><br>\n");
      out.write("        </form>\n");
      out.write("        <br>\n");
      out.write("        <form method=\"post\" action=\"./delete-student-db\">\n");
      out.write("            <button type=\"submit\">Sterge studentul cu id-ul:</button>\n");
      out.write("            Id: <input type=\"number\" name=\"idStudent\"><br>\n");
      out.write("        </form>\n");
      out.write("\t</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
