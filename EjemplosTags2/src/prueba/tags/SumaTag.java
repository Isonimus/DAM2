/*
 * Created on 22-jun-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package prueba.tags;

//SumaTag.java
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class SumaTag extends TagSupport{
    private int num1,num2;
    public void setNum1(int num1){
        this.num1 = num1;
    }
    public void setNum2(int num2){
        this.num2 = num2;
    }
    public int doStartTag() throws JspException {
        try{
            pageContext.getOut().print("Suma: " + (num1+num2));
        } catch (IOException e) {
            throw new JspException ("Error: IOException" + 
			                                e.getMessage());
        }
        return SKIP_BODY;
    }
	
    public int doEndTag() throws JspException {
//    	No hay que poner esto así porque entonces deja de procesar la página JSP
		//return SKIP_PAGE;
		return EVAL_PAGE;
    }
}
