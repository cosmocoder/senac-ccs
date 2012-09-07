package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Think Fast Game</title>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"resources/js/jquery-1.7.2.min.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"resources/js/knockout-2.0.0.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h1>Think Fast Game</h1>\r\n");
      out.write("        <div id=\"participant\">\r\n");
      out.write("            <h2>Insert your name and click start to begin:</h2>\r\n");
      out.write("            <input type=\"text\" name=\"participant\" />\r\n");
      out.write("            <input type=\"button\" value=\"start\" data-bind=\"click: play\" />\r\n");
      out.write("        </div>\r\n");
      out.write("        <br/>\r\n");
      out.write("        <div id=\"survey\">\r\n");
      out.write("            <span data-bind=\"text: question\">Qual a capital da Rússia?</span>\r\n");
      out.write("            <ul data-bind=\"foreach: answers\">\r\n");
      out.write("                <li style=\"list-style: none;\">\r\n");
      out.write("                    <input type=\"radio\" name=\"answer\"/>\r\n");
      out.write("                    <span data-bind=\"text: $data\">Moscou</span>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            <span id=\"message\"></span>\r\n");
      out.write("        </div>\r\n");
      out.write("        <script>\r\n");
      out.write("            var ThinkFast = function() {\r\n");
      out.write("                var self = this;\r\n");
      out.write("                self.participant = ko.observable();\r\n");
      out.write("                self.question = ko.observable();\r\n");
      out.write("                self.answers = ko.observableArray([]);\r\n");
      out.write("\r\n");
      out.write("                self.play = function() {\r\n");
      out.write("                    self.question(\"Qual a capital da Rússia?\");\r\n");
      out.write("                    self.answers.push(\"Servia\");                   \r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            ko.applyBindings(new ThinkFast());\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </script>\r\n");
      out.write("    </body>\r\n");
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
