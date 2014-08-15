package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class edit_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("Edit the Given Item\n");
      out.write("</h2>\n");
      out.write("\n");
      out.write("<form method=\"post\" action=\"edit.do\" enctype=\"multipart/form-data\">\n");
      out.write("\n");
      out.write("<table width=\"100%\" cellpadding=\"2px\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t<input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\n");
      out.write("\t<tr>\n");
      out.write("\t\t<td>Item Name:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item URL:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"url\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item Price:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"price\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.price}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item Points:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"points\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.points}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item Image: <br><img src=\"image.do?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.imageId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" width=\"75px\"></td>\n");
      out.write("\t\t<td><input type=\"file\" name=\"imgFile\" value=\"\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Item Action Image:<br><img src=\"image.do?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.actionImageId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" width=\"75px\"></td>\n");
      out.write("\t\t<td><input type=\"file\" name=\"actionFile\" value=\"\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Comment:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"comment\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.comment}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Tags:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"tags\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.tags}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Rank:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"rank\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.rank}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td>Status:</td>\n");
      out.write("\t\t<td><input type=\"text\" name=\"status\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\n");
      out.write("\t</tr><tr>\n");
      out.write("\t\t<td colspan=\"2\" align=\"center\"><br>\n");
      out.write("\t\t\t<input type=\"submit\" name=\"button\" value=\"Edit Item\" />\n");
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
