package twitter.client;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class StatusListModel extends AbstractListModel<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1379571021355219559L;
	
	private ArrayList<String> status;
	
	public StatusListModel() {
		status = new ArrayList<String>();
	}
	
	@Override
	public String getElementAt(int arg0) {
		return status.get(arg0);
	}

	@Override
	public int getSize() {
		return status.size();
	}
	
	public void add(String s){
		status.add(s);
		this.fireContentsChanged(s, 0, status.size());
	}

	public void clear() {
		status.clear();
	}
	
}
