package capuretta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import capuretta.model.Budget;
import capuretta.util.DBUtils;

public class BudgetDAO
{
	public static void Create(Budget b) 
	{
		//create - String table_name, String[] fields_name, String[] fields_values
        ArrayList<String> fields_name = new ArrayList<String>();
        ArrayList<Object> fields_values = new ArrayList<Object>();
		boolean success = false;
		
		String table_name = "budget";
		
		fields_name.add("|usuario_id");
		fields_name.add("observacoes");
		
		fields_values.add(b.getUsuarioid());
		fields_values.add(b.getObservacoes());
		
		success = DBUtils.create(table_name, fields_name.toArray(), fields_values.toArray());
        System.out.println("CREATE Budget: " + success);
	}

	public static String ReadAll()
	{
		String table_name = "budget";
		
		ResultSet resultado = DBUtils.read(table_name);
		ArrayList<Budget> bList = new ArrayList<Budget>();
		String jsonResponse = "";
		
		if(resultado != null)
		{
			System.out.println("READAll Budget: true");

			try 
			{
				while(resultado.next())
				{
					int id = resultado.getInt("id");
					int usuarioid = resultado.getInt("usuario_id");
					String observacoes = resultado.getString("observacoes");
					
					Budget b = new Budget(id, usuarioid, observacoes);
					bList.add(b);
				}
				
				Gson gson = new GsonBuilder().create();
				jsonResponse = gson.toJson(bList);
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
			System.out.println("READAll Budget: false");
			return null;
		}
	}

	public static void ReadOne(int id) 
	{
		String table_name = "budget";
		String condition = "id = " + id;
		
		ResultSet resultado = DBUtils.read(table_name, condition);
		
		if(resultado != null)
			System.out.println("READOne Budget: true");
		else
			System.out.println("READOne Budget: false");
	}

	public static String ReadLast() 
	{
		String table_name = "budget";
		ArrayList<String> fields_name = new ArrayList<String>();
		fields_name.add("MAX(id)");
		fields_name.add("usuario_id");
		fields_name.add("observacoes");
		
		ResultSet resultado = DBUtils.read(table_name, fields_name.toArray());
		ArrayList<Budget> bList = new ArrayList<Budget>();
		String jsonResponse = "";
		
		if(resultado != null)
		{
			System.out.println("READLast Budget: true");

			try 
			{
				while(resultado.next())
				{
					int id = resultado.getInt("id");
					int usuarioid = resultado.getInt("usuario_id");
					String observacoes = resultado.getString("observacoes");
					
					Budget b = new Budget(id, usuarioid, observacoes);
					bList.add(b);
				}
				
				Gson gson = new GsonBuilder().create();
				jsonResponse = gson.toJson(bList);
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
			System.out.println("READLast Budget: false");
			return null;
		}
	}
	
	public static void Update() 
	{
		// TODO Auto-generated method stub
	}

	public static void Delete() 
	{
		// TODO Auto-generated method stub
	}

	public static Budget Build(int usuarioid, String observacoes) 
	{
		try 
		{
			Budget b = new Budget(usuarioid, observacoes);
			return b;
		} 
		catch (Exception e) 
		{
			System.out.println("Erro no budget: " + e.getMessage());
		}
		return null;
	}

}
