package capuretta.model;

public class Budget 
{
	private int id;
	private int usuarioid;
	private String observacoes;
	
	public Budget() {}
	public Budget(int usuarioid, String observacoes)
	{
		super();
		this.setUsuarioid(usuarioid);
		this.setObservacoes(observacoes);
	}
	public Budget(int id, int usuarioid, String observacoes)
	{
		super();
		this.setId(id);
		this.setUsuarioid(usuarioid);
		this.setObservacoes(observacoes);
	}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public int getUsuarioid() { return this.usuarioid; }
	public void setUsuarioid(int usuarioid) { this.usuarioid = usuarioid; }
	
	public String getObservacoes() { return this.observacoes; }
	public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
