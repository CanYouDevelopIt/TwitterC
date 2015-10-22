package twitter.client;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class StatusListModel extends AbstractListModel<Status>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1379571021355219559L;
	
	private ArrayList<Status> status;
	
	public StatusListModel() {
		status = new ArrayList<Status>();
	}
	
	@Override
	public Status getElementAt(int arg0) {
		return status.get(arg0);
	}

	@Override
	public int getSize() {
		return status.size();
	}
	
	public void add(Status s){
		status.add(s);
		this.fireContentsChanged(s, 0, status.size());
	}

	public void clear() {
		status.clear();
	}
	
}
