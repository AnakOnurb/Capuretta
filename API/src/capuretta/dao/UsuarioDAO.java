package capuretta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import capuretta.model.Usuario;
import capuretta.util.DBUtils;

public class UsuarioDAO
{
	public static void Create(Usuario u) 
	{
		//create - String table_name, String[] fields_name, String[] fields_values
        ArrayList<String> fields_name = new ArrayList<String>();
        ArrayList<Object> fields_values = new ArrayList<Object>();
		boolean success = false;
		
		String table_name = "users";
		
		fields_name.add("nome");
		fields_name.add("email");
		fields_name.add("telefone");
		
		fields_values.add(u.getNome());
		fields_values.add(u.getEmail());
		fields_values.add(u.getTelefone());
		
		success = DBUtils.create(table_name, fields_name.toArray(), fields_values.toArray());
        System.out.println("CREATE Usuario: " + success);
	}

	public static String ReadAll()
	{
		String table_name = "users";
		
		ResultSet resultado = DBUtils.read(table_name);
		ArrayList<Usuario> uList = new ArrayList<Usuario>();
		String jsonResponse = "";
		
		if(resultado != null)
		{
			System.out.println("READAll Usuario: true");

			try 
			{
				while(resultado.next())
				{
					int id = resultado.getInt("id");
					String nome = resultado.getString("nome");
					String email = resultado.getString("email");
					String telefone = resultado.getString("telefone");
					
					Usuario u = new Usuario(id, nome, email, telefone);
					uList.add(u);
				}
				
				Gson gson = new GsonBuilder().create();
				jsonResponse = gson.toJson(uList);
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
			System.out.println("READAll Usuario: false");
			return null;
		}
	}

	public static void ReadOne(int id) 
	{
		String table_name = "users";
		String condition = "id = " + id;
		
		ResultSet resultado = DBUtils.read(table_name, condition);
		
		if(resultado != null)
			System.out.println("READOne Usuario: true");
		else
			System.out.println("READOne Usuario: false");
	}

	public static void Update() 
	{
		// TODO Auto-generated method stub
	}

	public static void Delete() 
	{
		// TODO Auto-generated method stub
	}

	public static Usuario Build(String nome, String email, String telefone) 
	{
		try 
		{
			Usuario u = new Usuario(nome, email,telefone);
			return u;
		} 
		catch (Exception e) 
		{
			System.out.println("Erro no usuario: " + e.getMessage());
		}
		return null;
	}

}
