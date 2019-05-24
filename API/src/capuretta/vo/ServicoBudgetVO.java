package capuretta.vo;

public class ServicoBudgetVO 
{
	private int id;
	private int idServico;
	private int idBudget;
	private double qtdeHoras;
	private double preco;
		
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public int getIdServico() { return this.idServico; }
	public void setIdServico(int id) { this.idServico = idServico; }
	
	public int getIdBudget() { return this.idBudget; }
	public void setIdBudget(int id) { this.idBudget = idBudget; }
	
	public double getQtdeHoras() { return this.qtdeHoras; }
	public void setQtdeHoras(int petId) { this.qtdeHoras = qtdeHoras; }
	
	public double getPreco() { return this.preco; }
	public void setPreco(int petId) { this.preco = preco; }
}
