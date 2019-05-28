package capuretta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import capuretta.model.Servico;
import capuretta.util.DBUtils;

public class ServicoDAO
{
	public static void Create(Servico s) 
	{
		//create - String table_name, String[] fields_name, String[] fields_values
        ArrayList<String> fields_name = new ArrayList<String>();
        ArrayList<Object> fields_values = new ArrayList<Object>();
		boolean success = false;
		
		String table_name = "services";
		
		fields_name.add("descricao");
		fields_name.add("|preco_hora");
		
		fields_values.add(s.getDescricao());
		fields_values.add(s.getPrecoHora());
		
		success = DBUtils.create(table_name, fields_name.toArray(), fields_values.toArray());
        System.out.println("CREATE Servico: " + success);
	}

	public static String ReadAll()
	{
		String table_name = "services";
		
		ResultSet resultado = DBUtils.read(table_name);
		ArrayList<Servico> sList = new ArrayList<Servico>();
		String jsonResponse = "";
		
		if(resultado != null)
		{
			System.out.println("READAll Servico: true");

			try 
			{
				while(resultado.next())
				{
					int id = resultado.getInt("id");
					String descricao = resultado.getString("descricao");
					double precohora = resultado.getDouble("preco_hora");
					
					Servico s = new Servico(id, descricao, precohora);
					sList.add(s);
				}
				
				Gson gson = new GsonBuilder().create();
				jsonResponse = gson.toJson(sList);
				System.out.println(jsonResponse);
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}

			return jsonResponse;
		}
		else
		{
			System.out.println("READAll Servico: false");
			return null;
		}
	}

	public static void ReadOne(int id) 
	{
		String table_name = "services";
		String condition = "id = " + id;
		
		ResultSet resultado = DBUtils.read(table_name, condition);
		
		if(resultado != null)
			System.out.println("READOne Servico: true");
		else
			System.out.println("READOne Servico: false");
	}

	public static void Update() 
	{
		// TODO Auto-generated method stub
	}

	public static void Delete() 
	{
		// TODO Auto-generated method stub
	}

	public static Servico Build(String descricao, double precohora) 
	{
		try 
		{
			Servico s = new Servico(descricao, precohora);
			return s;
		} 
		catch (Exception e) 
		{
			System.out.println("Erro no budget: " + e.getMessage());
		}
		return null;
	}

}
