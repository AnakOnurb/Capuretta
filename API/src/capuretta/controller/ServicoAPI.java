package capuretta.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import capuretta.dao.ServicoDAO;

/**
 * Servlet implementation class ServicoAPI
 */
@WebServlet(description = "ServicoAPI", urlPatterns = { "/ServicoAPI" })
public class ServicoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> dados = new ArrayList<String>();
	
    public ServicoAPI() 
    {
        super();      
    }

    public void init(ServletConfig config) throws ServletException 
    {

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Authorize (allow) all domains to consume the content
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        
        	String result = ServicoDAO.ReadAll();
    		JsonObject jsonResult = null;
    		try 
    		{ 	
    			result = "{\"Servico\":" + result + "}";
    			jsonResult = new JsonParser().parse(result).getAsJsonObject();
    			if(jsonResult != null)
    				response.getWriter().print(jsonResult);
    		} 
    		catch (Exception e) 
    		{
    			System.out.println(e.getMessage());
    		}
	}		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Authorize (allow) all domains to consume the content
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        
        //TODO: create insert option
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Authorize (allow) all domains to consume the content
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        
        //TODO: create update option
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Authorize (allow) all domains to consume the content
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        
      //TODO: create delete option
	}
}
