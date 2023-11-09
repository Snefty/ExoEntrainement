package jwProject;

public class Article {
	private int idArticle;
	private String designation;
	private int pu;
	private int qty;
	private int idCategorie;
	private String nameImg;

public Article(String designation, int pu, int qty, int idCategorie) {
	super();
	this.designation = designation;
	this.pu = pu;
	this.qty = qty;
	this.idCategorie = idCategorie;
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
