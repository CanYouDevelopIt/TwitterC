package twitter.client;

public class Status {
	
	private String user;
	private String texte;
	private String date;
	private String image;
	
	public Status(String _user, String _texte, String _date, String _image){
		user = _user;
		texte = _texte;
		date = _date;
		image = _image;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
