package tr.comagem.arya.element;

public class ElementModel {

	private String id;
	private String name;
	private String clazz;
	
	public ElementModel() {
	}
	
	public ElementModel(String id, String name, String clazz) {
		super();
		this.id = id;
		this.name = name;
		this.clazz = clazz;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public String toString() {
		return "{id:"+this.id+" name:"+this.name+" class:"+this.clazz+"}\n";
	}
	
}
