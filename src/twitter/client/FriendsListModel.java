package twitter.client;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class FriendsListModel extends AbstractListModel<Friend>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Friend> friends;
	
	public FriendsListModel() {
		friends = new ArrayList<Friend>();
	}

	@Override
	public Friend getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return friends.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return friends.size();
	}
	
	public void add(Friend f){
		friends.add(f);
		this.fireContentsChanged(f, 0, friends.size());
	}

	public void clear() {
		friends.clear();
	}

}
