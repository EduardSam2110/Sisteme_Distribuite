package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class update_002dand_002dview_002dstudent_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<html xmlns:jsp=\"http://java.sun.com/JSP/Page\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>Informatii student</title>\r\n");
      out.write("\t\t<meta charset=\"UTF-8\" />\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<h3>Informatii student</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- populare bean cu informatii din cererea HTTP -->\r\n");
      out.write("\t\t");
      beans.StudentBean studentBean = null;
      synchronized (request) {
        studentBean = (beans.StudentBean) _jspx_page_context.getAttribute("studentBean", PageContext.REQUEST_SCOPE);
        if (studentBean == null){
          studentBean = new beans.StudentBean();
          _jspx_page_context.setAttribute("studentBean", studentBean, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "nume",
 request.getAttribute("nume") );
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "prenume",
 request.getAttribute("prenume") );
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "varsta",
 request.getAttribute("varsta") );
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- folosirea bean-ului pentru afisarea informatiilor -->\r\n");
      out.write("\t\t<p>Urmatoarele informatii au fost introduse:</p>\r\n");
      out.write("\t\t<ul type=\"bullet\">\r\n");
      out.write("            <form action=\"./process-student\" method=\"post\">\r\n");
      out.write("                <li>Nume: <input type=\"text\" name=\"nume\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getNume())));
      out.write("\" /></li>\r\n");
      out.write("                <li>Prenume: <input type=\"text\" name=\"prenume\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getPrenume())));
      out.write("\"/></li>\r\n");
      out.write("                <li>Varsta: <input type=\"text\" name=\"varsta\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getVarsta())));
      out.write("\"/></li>\r\n");
      out.write("                <!-- anul nasterii nu face parte din bean, il afisam separat (daca exista) -->\r\n");
      out.write("                <li>Anul nasterii: ");

                    Object anNastere = request.getAttribute("anNastere");
                    if (anNastere != null) {
                        out.print(anNastere);
                    } else {
                        out.print("necunoscut");
                    }
                
      out.write("</li>\r\n");
      out.write("                <br />\r\n");
      out.write("                <br />\r\n");
      out.write("                <button type=\"Update\" name=\"submit\">Trimite</button>\r\n");
      out.write("            </form>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</body>\r\n");
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
