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

import capuretta.dao.BudgetDAO;
import capuretta.model.Budget;

/**
 * Servlet implementation class ServicoAPI
 */
@WebServlet(description = "BudgetAPI", urlPatterns = { "/BudgetAPI" })
public class BudgetAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> dados = new ArrayList<String>();
	
    public BudgetAPI() 
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
        
        String last = request.getParameter("last");
        if(last == "true")
        {
        	String result = BudgetDAO.ReadLast();
    		JsonObject jsonResult = null;
    		try 
    		{ 	
    			result = "{\"Budget\":" + result + "}";
    			jsonResult = new JsonParser().parse(result).getAsJsonObject();
    			if(jsonResult != null)
    				response.getWriter().print(jsonResult);
    		} 
    		catch (Exception e) 
    		{
    			System.out.println(e.getMessage());
    		}	
        }
        else 
        {
        	String result = BudgetDAO.ReadAll();
    		JsonObject jsonResult = null;
    		try 
    		{ 	
    			result = "{\"Budget\":" + result + "}";
    			jsonResult = new JsonParser().parse(result).getAsJsonObject();
    			if(jsonResult != null)
    				response.getWriter().print(jsonResult);
    		} 
    		catch (Exception e) 
    		{
    			System.out.println(e.getMessage());
    		}	
		}
	}		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Authorize (allow) all domains to consume the content
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        
        try
        {
        	String usuario_id = request.getParameter("usuarioid");
        	int usuarioid = 0;
        	try 
        	{
        		usuarioid = Integer.parseInt(usuario_id);
        		String observacoes = request.getParameter("observacoes");
            	
            	System.out.println(usuarioid + observacoes + "");
            	
            	Budget b = BudgetDAO.Build(usuarioid, observacoes);
            	BudgetDAO.Create(b);
        	}
        	catch (Exception e) 
        	{
				System.out.println("Erro convertendo usuario_id");
			}
		}
        catch (Exception e) 
        {
        	System.out.println("Erro: " + e.getMessage());
		}
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
