package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class formular_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<head>\n");
      out.write("\t\t<title>Formular student</title>\n");
      out.write("\t\t<meta charset=\"UTF-8\" />\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t\t<h3>Formular student</h3>\n");
      out.write("\t\tIntroduceti datele despre student:\n");
      out.write("\t\t<form action=\"./process-student\" method=\"post\">\n");
      out.write("\t\t\tNume: <input type=\"text\" name=\"nume\" />\n");
      out.write("\t\t\t<br />\n");
      out.write("\t\t\tPrenume: <input type=\"text\" name=\"prenume\" />\n");
      out.write("\t\t\t<br />\n");
      out.write("\t\t\tVarsta: <input type=\"number\" name=\"varsta\" />\n");
      out.write("\t\t\t<br />\n");
      out.write("\t\t\t<br />\n");
      out.write("\t\t\t<button type=\"submit\" name=\"submit\">Trimite</button>\n");
      out.write("\t\t</form>\n");
      out.write("\t</body>\n");
      out.write("</html>");
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
