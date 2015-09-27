package twitter.client;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import twitter4j.Status;

public class StatusCellRenderer extends JLabel implements ListCellRenderer<Status> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1401314114834848848L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Status> list,
			Status s, int index, boolean isSelected, boolean cellHasFocus) {
		
		setText(s.getUser().getName() + " : " + s.getText());
		
//        if (isSelected) {
//            setBackground(list.getSelectionBackground());
//            setForeground(list.getSelectionForeground());
//        } else {
//            setBackground(list.getBackground());
//            setForeground(list.getForeground());
//        }
		
		return this;
	}

}
