package capuretta.model;

public class Candidato 
{
	private int id;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private String descricao;
	
	public Candidato() {}
	public Candidato(String nome, String cpf, String endereco, String telefone, String descricao)
	{
		super();
		this.setNome(nome);
		this.setCpf(cpf);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setDescricao(descricao);
	}
	public Candidato(int id, String nome, String cpf, String endereco, String telefone, String descricao)
	{
		super();
		this.setId(id);
		this.setNome(nome);
		this.setCpf(cpf);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setDescricao(descricao);
	}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return this.nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getCpf() { return this.cpf; }
	public void setCpf(String cpf) { this.cpf = cpf; }
	
	public String getEndereco() { return this.endereco; }
	public void setEndereco(String endereco) { this.endereco = endereco; }
	
	public String getTelefone() { return this.telefone; }
	public void setTelefone(String telefone) { this.telefone = telefone; }
	
	public String getDescricao() { return this.descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
}
