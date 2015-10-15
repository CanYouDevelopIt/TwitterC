package process;

import javax.swing.JOptionPane;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import twitter.client.TwitterW;

public class Launcher {
	
	public static void main(String[] args) {
	
//        String pin = (String) JOptionPane.showInputDialog(
//               null,
//               "<html>Saisir code <b>pin</b> : </html>",
//               "Authentification",
//               JOptionPane.PLAIN_MESSAGE,
//               null,
//               null,
//               "");
       
        //if(pin.equals("634219313") && OAuth()){
        	new TwitterW();
        //}
	}
	
	public static boolean OAuth(){
		
		try {
			HttpResponse<String> response = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/usertimeline/auth")
					.header("", "text/plain").asString();
			
			return response.getBody().equals("true");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
