package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class info_002dstudent_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\t<title>Informatii student</title>\n");
      out.write("\t\t<meta charset=\"UTF-8\" />\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t\t<h3>Informatii student</h3>\n");
      out.write("\n");
      out.write("\t\t<!-- populare bean cu informatii din cererea HTTP -->\n");
      out.write("\t\t");
      beans.StudentBean studentBean = null;
      synchronized (request) {
        studentBean = (beans.StudentBean) _jspx_page_context.getAttribute("studentBean", PageContext.REQUEST_SCOPE);
        if (studentBean == null){
          studentBean = new beans.StudentBean();
          _jspx_page_context.setAttribute("studentBean", studentBean, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "nume",
 request.getAttribute("nume") );
      out.write("\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "prenume",
 request.getAttribute("prenume") );
      out.write("\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "varsta",
 request.getAttribute("varsta") );
      out.write("\n");
      out.write("\n");
      out.write("\t\t<!-- folosirea bean-ului pentru afisarea informatiilor -->\n");
      out.write("\t\t<p>Urmatoarele informatii au fost introduse:</p>\n");
      out.write("\t\t<ul type=\"bullet\">\n");
      out.write("\t\t\t<li>Nume: ");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getNume())));
      out.write("</li>\n");
      out.write("\t\t\t<li>Prenume: ");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getPrenume())));
      out.write("</li>\n");
      out.write("\t\t\t<li>Varsta: ");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getVarsta())));
      out.write("</li>\n");
      out.write("\n");
      out.write("\t\t\t<!-- anul nasterii nu face parte din bean, il afisam separat (daca exista) -->\n");
      out.write("\t\t\t<li>Anul nasterii: ");

			    Object anNastere = request.getAttribute("anNastere");
			    if (anNastere != null) {
			        out.print(anNastere);
			    } else {
			        out.print("necunoscut");
			    }
			
      out.write("</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t</body>\n");
      out.write("</html>\n");
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
