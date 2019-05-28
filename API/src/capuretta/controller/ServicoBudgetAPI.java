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

import capuretta.dao.ServicoBudgetDAO;
import capuretta.model.ServicoBudget;

/**
 * Servlet implementation class ServicoAPI
 */
@WebServlet(description = "ServicoBudgetAPI", urlPatterns = { "/ServicoBudgetAPI" })
public class ServicoBudgetAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> dados = new ArrayList<String>();
	
    public ServicoBudgetAPI() 
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
        
    	String result = ServicoBudgetDAO.ReadAll();
		JsonObject jsonResult = null;
		try 
		{ 	
			result = "{\"ServicoBudget\":" + result + "}";
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
        
        try
        {
        	String id_servico = request.getParameter("idservico");
        	String id_budget = request.getParameter("idbudget");
        	String qtde_horas = request.getParameter("qtdehoras");
        	String preco_s = request.getParameter("preco");
        	int idservico = 0;
        	int idbudget = 0;
        	double qtdehoras = 0;
        	double preco = 0;
        	try 
        	{
        		idservico = Integer.parseInt(id_servico);
        		idbudget = Integer.parseInt(id_budget);
        		qtdehoras = Double.parseDouble(qtde_horas);
        		preco = Double.parseDouble(preco_s);
            	
            	System.out.println(idservico + idbudget + qtdehoras + preco + "");
            	
            	ServicoBudget b = ServicoBudgetDAO.Build(idservico, idbudget, qtdehoras, preco);
            	ServicoBudgetDAO.Create(b);
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
