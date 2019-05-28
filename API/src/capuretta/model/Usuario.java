package capuretta.model;

public class Usuario 
{
	private int id;
	private String nome;
	private String email;
	private String telefone;
	
	public Usuario() {}
	public Usuario(String nome, String email, String telefone)
	{
		super();
		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
	}
	public Usuario(int id, String nome, String email, String telefone)
	{
		super();
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
	}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return this.nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getTelefone() { return this.telefone; }
	public void setTelefone(String telefone) { this.telefone = telefone; }
}
