package twitter.client;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class FriendsCellRenderer extends JLabel implements ListCellRenderer<Friend>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
	
	public FriendsCellRenderer() {
	    setOpaque(true);
	    setIconTextGap(12);
 }

	@Override
	public Component getListCellRendererComponent(JList<? extends Friend> list,
			Friend f, int index, boolean isSelected, boolean cellHasFocus) {


		setText(f.getScreenname());
		
		try {
			URL url = new URL(f.getImage());
			ImageIcon icon = new ImageIcon(url, f.getName());
			setIcon(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		
		return this;
	}

}
