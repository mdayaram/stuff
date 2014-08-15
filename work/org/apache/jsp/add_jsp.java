package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "template-top.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<h2 align=\"center\">\n");
      out.write("Suggest An Item to Get Noj\n");
      out.write("</h2>\n");
      out.write("\n");
      out.write("<form method=\"post\" action=\"add.do\" enctype=\"multipart/form-data\">\n");
      out.write("\n");
      out.write("<table width=\"100%\" cellpadding=\"2px\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t<tr>\n");
      out.write("\t\t<td>Item Name:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item URL:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"url\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item Price:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"price\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${price}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item Image:</td>\n");
      out.write("\t\t<td><input type=\"file\" name=\"imgFile\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${imgFile}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Comment:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"comment\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${comment}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Tags:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"tags\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tags}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td colspan=\"2\" align=\"center\"><br>\n");
      out.write("\t\t\t<input type=\"submit\" name=\"button\" value=\"Add Item\" />\n");
      out.write("\t\t</td>\n");
      out.write("\t</tr>\n");
      out.write("</table>\n");
      out.write("</form>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "template-bottom.jsp", out, false);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
