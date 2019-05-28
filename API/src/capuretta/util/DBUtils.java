package capuretta.util;

import capuretta.util.DBConn;
import java.sql.*;

public class DBUtils 
{
	private static Connection getConnection()
	{
		return DBConn.getConnection();
	}
	
	public static boolean create(String table_name, Object[] fields_name, Object[] fields_values)
	{
		boolean sucesso = false;
		
		//generate generic insert string
		String sqlCommandString = "INSERT INTO " + table_name + " (";
		
		for(int i = 0; i < fields_name.length; i++)
		{
			sqlCommandString += fields_name[i].toString().replace("|","") + ",";
		}
		sqlCommandString =sqlCommandString.substring(0, sqlCommandString.length() - 1);
		
		sqlCommandString += ") VALUES (";
		
		for(int i = 0; i < fields_values.length; i++)
		{
			if(fields_name[i].toString().contains("|"))
			{
				sqlCommandString += fields_values[i] + ",";
			}
			else
			{
				sqlCommandString += "'"+fields_values[i] + "',";	
			}
		}
		sqlCommandString = sqlCommandString.substring(0, sqlCommandString.length() - 1);
		
		sqlCommandString += ");";
		
		System.out.println(sqlCommandString);
		
		Connection conn = null;
		//start connection
		try 
		{
			conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlCommandString);
			sucesso = true;
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao inserir: " + e.getMessage());
		}
		finally
		{
			try 
			{
				//conn.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
		
		return sucesso;
	}

	public static ResultSet read(String table_name, Object[] fields_name, String condition)
	{
		ResultSet rs = null;
		
		//generate generic insert string
		String sqlCommandString = "SELECT ";
		
		for(int i = 0; i < fields_name.length; i++)
		{
			sqlCommandString += fields_name[i] + ",";
		}
		sqlCommandString =sqlCommandString.substring(0, sqlCommandString.length() - 1);
		
		sqlCommandString += "FROM " + table_name + "WHERE " + condition + ";";
	
		System.out.println(sqlCommandString);
		
		Connection conn = null;
		//start connection
		try 
		{
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlCommandString);
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao ler: " + e.getMessage());
		}
		finally
		{
			try 
			{
				//conn.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
		
		return rs;
	}

	public static ResultSet read(String table_name, Object[] fields_name)
	{
		ResultSet rs = null;
		
		//generate generic insert string
		String sqlCommandString = "SELECT ";
		
		for(int i = 0; i < fields_name.length; i++)
		{
			sqlCommandString += fields_name[i] + ",";
		}
		sqlCommandString =sqlCommandString.substring(0, sqlCommandString.length() - 1);
		
		sqlCommandString += "FROM " + table_name + ";";
	
		System.out.println(sqlCommandString);
		
		Connection conn = null;
		//start connection
		try 
		{
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlCommandString);
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao ler: " + e.getMessage());
		}
		finally
		{
			try 
			{
				//conn.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
		
		return rs;
	}

	public static ResultSet read(String table_name, String condition)
	{
		ResultSet rs = null;
		
		//generate generic insert string
		String sqlCommandString = "SELECT *";
		
		sqlCommandString += "FROM " + table_name + "WHERE " + condition + ";";
	
		System.out.println(sqlCommandString);
		
		Connection conn = null;
		//start connection
		try 
		{
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlCommandString);
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao ler: " + e.getMessage());
		}
		finally
		{
			try 
			{
				//conn.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
		
		return rs;
	}
	
	public static ResultSet read(String table_name)
	{
		ResultSet rs = null;
		
		//generate generic insert string
		String sqlCommandString = "SELECT * ";
		
		sqlCommandString += "FROM " + table_name + ";";
	
		System.out.println(sqlCommandString);
		
		Connection conn = null;
		//start connection
		try 
		{
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlCommandString);
			return rs;
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao ler: " + e.getMessage());
		}
		finally
		{
			try 
			{
				//conn.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
		
		return null;
	}

	public static boolean update(String table_name, Object[] fields_name, Object[] fields_values, String condition)
	{
		boolean sucesso = false;
	
		if(fields_name.length == fields_values.length)
		{
			//generate generic insert string
			String sqlCommandString = "UPDATE " + table_name + " SET ";
			
			for(int i = 0; i < fields_name.length; i++)
			{
				sqlCommandString += fields_name[i] + " = " + fields_values[i] + ";";
			}
			sqlCommandString =sqlCommandString.substring(0, sqlCommandString.length() - 1);
			
			sqlCommandString += " WHERE " + condition + ";";
			
			System.out.println(sqlCommandString);
			
			Connection conn = null;
			//start connection
			try 
			{
				conn = getConnection();
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlCommandString);
				sucesso = true;
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao alterar: " + e.getMessage());
			}
			finally
			{
				try 
				{
					//conn.close();
				} 
				catch (Exception e) 
				{
					System.out.println("Erro ao fechar a conexão: " + e.getMessage());
				}
			}
		}
		
		return sucesso;
	} 

	public static boolean delete(String table_name, String condition)
	{
		boolean sucesso = false;
	
		//generate generic insert string
		String sqlCommandString = "DELETE FROM " + table_name + " WHERE ";
		
		sqlCommandString += " WHERE " + condition + ";";
		
		System.out.println(sqlCommandString);
		
		Connection conn = null;
		//start connection
		try 
		{
			conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlCommandString);
			sucesso = true;
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao deletar: " + e.getMessage());
		}
		finally
		{
			try 
			{
				//conn.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
		
		return sucesso;
	} 
}
