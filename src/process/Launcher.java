package process;

import javax.swing.JOptionPane;
import twitter.client.TwitterW;

public class Launcher {
	
	public static void main(String[] args) {
		
        String pin = (String) JOptionPane.showInputDialog(
               null,
               "<html>Saisir code <b>pin</b> : </html>",
               "Authentification",
               JOptionPane.PLAIN_MESSAGE,
               null,
               null,
               "");
		
        
        
		new TwitterW();
		
	}
	
}
