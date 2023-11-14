package jwProject;

public class Article {
	private int idArticle;
	private String designation;
	private int pu;
	private int qty;
	private int idCategorie;
	private String nameCategorie;
	private String nameImg;

	public Article(int idArticle, String designation, int pu, int qty, int idCategorie,String nameCategorie) {
		super();
		this.idArticle = idArticle;
		this.designation = designation;
		this.pu = pu;
		this.qty = qty;
		this.idCategorie = idCategorie;
		this.nameCategorie = nameCategorie;
	}

	public Article(String designation, int pu, int qty, int idCategorie) {
		this(0,designation,pu,qty,idCategorie,"null");
	}
	
	public String getNameCategorie() {
		return nameCategorie;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getPu() {
		return pu;
	}

	public void setPu(int pu) {
		this.pu = pu;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

}
