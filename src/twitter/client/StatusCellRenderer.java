package twitter.client;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class StatusCellRenderer extends JLabel implements ListCellRenderer<Status> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1401314114834848848L;
	
	 public StatusCellRenderer() {
		    setOpaque(true);
		    setIconTextGap(12);
	 }
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Status> list,
			Status s, int index, boolean isSelected, boolean cellHasFocus) {
		
		setText(s.getUser() + " : " + s.getTexte());
		
		try {
			URL url = new URL(s.getImage());
			ImageIcon icon = new ImageIcon(url, s.getUser());
			setIcon(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}

}
