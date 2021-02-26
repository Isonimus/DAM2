/*
 * Created on 22-jun-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package prueba.tags;

//HolaMundoTag.java
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HolaMundoTag implements Tag {
	private PageContext pc;
	private Tag parent;
	
	public HolaMundoTag() {
		super();
	}
	
	public int doStartTag() throws JspException {
		try {
			pc.getOut().print("Hola mundo que tal");
		} catch (IOException e){
			throw new JspException("Error al enviar al cliente" + e.getMessage());
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		//No hay que poner esto así porque entonces deja de procesar la página JSP
		//return SKIP_PAGE;
		return EVAL_PAGE;
	}
	
	public void release(){
	}
	
	public void setPageContext(PageContext pc){
		this.pc = pc;
	}
	
	public void setParent(Tag parent){
		this.parent = parent;
	}
	
	public Tag getParent(){
		return parent;
	}
}
