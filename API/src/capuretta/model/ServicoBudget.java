package capuretta.model;

public class ServicoBudget 
{
	private int id;
	private int idServico;
	private int idBudget;
	private double qtdeHoras;
	private double preco;
		
	public ServicoBudget() {}
	public ServicoBudget(int idServico, int idBudget, double qtdeHoras, double preco)
	{
		super();
		this.setIdServico(idServico);
		this.setIdBudget(idBudget);
		this.setQtdeHoras(qtdeHoras);
		this.setPreco(preco);
	}
	public ServicoBudget(int id, int idServico, int idBudget, double qtdeHoras, double preco)
	{
		super();
		this.setId(id);
		this.setIdServico(idServico);
		this.setIdBudget(idBudget);
		this.setQtdeHoras(qtdeHoras);
		this.setPreco(preco);
	}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public int getIdServico() { return this.idServico; }
	public void setIdServico(int idServico) { this.idServico = idServico; }
	
	public int getIdBudget() { return this.idBudget; }
	public void setIdBudget(int idBudget) { this.idBudget = idBudget; }
	
	public double getQtdeHoras() { return this.qtdeHoras; }
	public void setQtdeHoras(double qtdeHoras) { this.qtdeHoras = qtdeHoras; }
	
	public double getPreco() { return this.preco; }
	public void setPreco(double preco) { this.preco = preco; }
}
