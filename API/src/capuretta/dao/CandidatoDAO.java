package capuretta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import capuretta.model.Candidato;
import capuretta.util.DBUtils;

public class CandidatoDAO
{
	public static void Create(Candidato c) 
	{
		//create - String table_name, String[] fields_name, String[] fields_values
        ArrayList<String> fields_name = new ArrayList<String>();
        ArrayList<Object> fields_values = new ArrayList<Object>();
		boolean success = false;
		
		String table_name = "candidate";
		
		fields_name.add("nome");
		fields_name.add("cpf");
		fields_name.add("endereco");
		fields_name.add("telefone");
		fields_name.add("descricao");
		
		fields_values.add(c.getNome());
		fields_values.add(c.getCpf());
		fields_values.add(c.getEndereco());
		fields_values.add(c.getTelefone());
		fields_values.add(c.getDescricao());
		
		success = DBUtils.create(table_name, fields_name.toArray(), fields_values.toArray());
        System.out.println("CREATE Candidato: " + success);
	}

	public static String ReadAll()
	{
		String table_name = "candidate";
		
		ResultSet resultado = DBUtils.read(table_name);
		ArrayList<Candidato> cList = new ArrayList<Candidato>();
		String jsonResponse = "";
		
		if(resultado != null)
		{
			System.out.println("READAll Candidato: true");

			try 
			{
				while(resultado.next())
				{
					int id = resultado.getInt("id");
					String nome = resultado.getString("nome");
					String cpf = resultado.getString("cpf");
					String endereco = resultado.getString("endereco");
					String telefone = resultado.getString("telefone");
					String descricao = resultado.getString("descricao");
					
					Candidato c = new Candidato(id, nome, cpf, endereco, telefone, descricao);
					cList.add(c);
				}
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				jsonResponse = gson.toJson(cList);
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}

			return jsonResponse;
		}
		else
		{
			System.out.println("READAll Candidato: false");
			return null;
		}
	}

	public static void ReadOne(int id) 
	{
		String table_name = "candidate";
		String condition = "id = " + id;
		
		ResultSet resultado = DBUtils.read(table_name, condition);
		
		if(resultado != null)
			System.out.println("READOne Candidato: true");
		else
			System.out.println("READOne Candidato: false");
	}

	public static void Update() 
	{
		// TODO Auto-generated method stub
	}

	public static void Delete() 
	{
		// TODO Auto-generated method stub
	}

	public static Candidato Build(String nome, String cpf, String endereco, String telefone, String descricao) 
	{
		try 
		{
			Candidato c = new Candidato(nome, cpf, endereco, telefone, descricao);
			return c;
		} 
		catch (Exception e) 
		{
			System.out.println("Erro no candidato: " + e.getMessage());
		}
		return null;
	}

}
