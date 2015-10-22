package twitter.client;

public class Friend {

	private String name;
	private String screenname;
	private String image;
	
	public Friend(String name, String screenname, String image) {
		this.name = name;
		this.screenname = screenname;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
