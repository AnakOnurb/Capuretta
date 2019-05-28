package capuretta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import capuretta.model.ServicoBudget;
import capuretta.util.DBUtils;

public class ServicoBudgetDAO
{
	public static void Create(ServicoBudget b) 
	{
		//create - String table_name, String[] fields_name, String[] fields_values
        ArrayList<String> fields_name = new ArrayList<String>();
        ArrayList<Object> fields_values = new ArrayList<Object>();
		boolean success = false;
		
		String table_name = "service_budget";
		
		fields_name.add("|id_service");
		fields_name.add("|id_budget");
		fields_name.add("|qtde_horas");
		fields_name.add("|preco");
		
		fields_values.add(b.getIdServico());
		fields_values.add(b.getIdBudget());
		fields_values.add(b.getQtdeHoras());
		fields_values.add(b.getPreco());
		
		success = DBUtils.create(table_name, fields_name.toArray(), fields_values.toArray());
        System.out.println("CREATE ServicoBudget: " + success);
	}

	public static String ReadAll()
	{
		String table_name = "service_budget";
		
		ResultSet resultado = DBUtils.read(table_name);
		ArrayList<ServicoBudget> bList = new ArrayList<ServicoBudget>();
		String jsonResponse = "";
		
		if(resultado != null)
		{
			System.out.println("READAll ServicoBudget: true");

			try 
			{
				while(resultado.next())
				{
					int id = resultado.getInt("id");
					int idservico = resultado.getInt("id_service");
					int idbudget = resultado.getInt("id_budget");
					double qtdehoras = resultado.getDouble("qtde_horas");
					double preco = resultado.getDouble("preco");
					
					ServicoBudget b = new ServicoBudget(id, idservico, idbudget, qtdehoras, preco);
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
			System.out.println("READAll ServicoBudget: false");
			return null;
		}
	}

	public static void ReadOne(int id) 
	{
		String table_name = "service_budget";
		String condition = "id = " + id;
		
		ResultSet resultado = DBUtils.read(table_name, condition);
		
		if(resultado != null)
			System.out.println("READOne ServicoBudget: true");
		else
			System.out.println("READOne ServicoBudget: false");
	}

	public static void Update() 
	{
		// TODO Auto-generated method stub
	}

	public static void Delete() 
	{
		// TODO Auto-generated method stub
	}

	public static ServicoBudget Build(int idservico, int idbudget, double qtdehoras, double preco) 
	{
		try 
		{
			ServicoBudget b = new ServicoBudget(idservico, idbudget, qtdehoras, preco);
			return b;
		} 
		catch (Exception e) 
		{
			System.out.println("Erro no ServicoBudget: " + e.getMessage());
		}
		return null;
	}

}
