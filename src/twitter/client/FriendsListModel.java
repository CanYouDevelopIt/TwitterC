package twitter.client;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class FriendsListModel extends AbstractListModel<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> friends;
	
	public FriendsListModel() {
		friends = new ArrayList<String>();
	}

	@Override
	public String getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return friends.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return friends.size();
	}
	
	public void add(String f){
		friends.add(f);
		this.fireContentsChanged(f, 0, friends.size());
	}

	public void clear() {
		friends.clear();
	}

}
