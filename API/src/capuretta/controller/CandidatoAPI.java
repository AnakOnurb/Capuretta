package capuretta.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capuretta.dao.CandidatoDAO;
import capuretta.model.Candidato;

/**
 * Servlet implementation class ServicoAPI
 */
@WebServlet(description = "CandidatoAPI", urlPatterns = { "/CandidatoAPI" })
public class CandidatoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> dados = new ArrayList<String>();
	
    public CandidatoAPI() 
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
        
		String jsonResponse = CandidatoDAO.ReadAll();
		if(jsonResponse != null)
			response.getWriter().print(jsonResponse);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Authorize (allow) all domains to consume the content
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        
        try
        {
        	String nome = request.getParameter("nome");
        	String cpf = request.getParameter("cpf"); 
        	String endereco = request.getParameter("endereco");
        	String telefone = request.getParameter("telefone");
        	String descricao = request.getParameter("descricao");
        	
        	System.out.println(nome + cpf + endereco + telefone + descricao + "");
        	
        	Candidato c = CandidatoDAO.Build(nome, cpf, endereco, telefone, descricao);
        	CandidatoDAO.Create(c);
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
