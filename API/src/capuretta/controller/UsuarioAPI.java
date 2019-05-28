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

import capuretta.dao.UsuarioDAO;
import capuretta.model.Usuario;

/**
 * Servlet implementation class ServicoAPI
 */
@WebServlet(description = "UsuarioAPI", urlPatterns = { "/UsuarioAPI" })
public class UsuarioAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> dados = new ArrayList<String>();
	
    public UsuarioAPI() 
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
        
		String result = UsuarioDAO.ReadAll();
		JsonObject jsonResult = null;
		try 
		{ 	
			result = "{\"Usuario\":" + result + "}";
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
        	String nome = request.getParameter("nome");
        	String email = request.getParameter("email");
        	String telefone = request.getParameter("telefone");
        	
        	System.out.println(nome + email + telefone + "");
        	
        	Usuario u = UsuarioDAO.Build(nome, email, telefone);
        	UsuarioDAO.Create(u);
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
