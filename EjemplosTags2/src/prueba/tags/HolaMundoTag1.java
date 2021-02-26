/*
 * Created on 22-jun-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package prueba.tags;

//HolaMundoTag1.java
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HolaMundoTag1 extends TagSupport{
	public int doStartTag() throws JspException {
		try{
			pageContext.getOut().print("Hola Mundo 1");
		} catch (IOException e) {
			throw new JspException ("Error: IOException" + e.getMessage());
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
