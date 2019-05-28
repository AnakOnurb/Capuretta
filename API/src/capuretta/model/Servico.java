package capuretta.model;

public class Servico
{
	private int id;
	private String descricao;
	private double precoHora;
	
	public Servico() {}
	public Servico(String descricao, double precoHora)
	{
		super();
		this.setDescricao(descricao);
		this.setPrecoHora(precoHora);
	}
	public Servico(int id, String descricao, double precoHora)
	{
		super();
		this.setId(id);
		this.setDescricao(descricao);
		this.setPrecoHora(precoHora);
	}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public String getDescricao() { return this.descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	   
	public double getPrecoHora() { return this.precoHora; }
	public void setPrecoHora(double precoHora) { this.precoHora = precoHora; }
	
}