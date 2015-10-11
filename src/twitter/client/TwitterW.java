package twitter.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TwitterW extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton jButton1;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JScrollPane jScrollPane2;
	private DefaultListModel<String> listStatus = new DefaultListModel<String>();
	private JList<String> jList1 = new JList<String>(listStatus);	
	
	public TwitterW(){
		setTitle("TwitterC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jButton1 = new JButton("Update");
		jButton1.setBounds(320, 230, 100, 20);
		
		jButton1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				sendTweet();
			}
		});
		
		jLabel1 = new JLabel("Icone");
		jLabel1.setBounds(30, 215, 48, 48);
		
		jTextField1 = new JTextField();
		jTextField1.setBounds(90, 230, 200, 20);
		
		//jList1.setCellRenderer(new StatusCellRenderer());
		jList1.setBounds(25, 10, 350, 150);
		
		jScrollPane2 = new JScrollPane(jList1);
		jScrollPane2.setBounds(25, 10, 400, 200);
		
		contentPane.add(jButton1);
		contentPane.add(jLabel1);
		contentPane.add(jTextField1);
		contentPane.add(jScrollPane2);
		
		initUserInfo();
		getUserTimeline();
		
		setVisible(true);
	}
	
	public void getUserTimeline(){
		
		try {
			HttpResponse<String> response = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/usertimeline/get")
					.header("", "json/application").asString();
			
			JSONArray JSONArrayStatus = new JSONArray(response.getBody());		
			for (int i = 0; i < JSONArrayStatus.length(); i++) {
				String date = JSONArrayStatus.getJSONObject(i).getJSONArray("date").get(0).toString();
				String text = JSONArrayStatus.getJSONObject(i).getJSONArray("text").get(0).toString();
				String user = JSONArrayStatus.getJSONObject(i).getJSONArray("user").get(0).toString();
				listStatus.addElement(date + " -> @" + user + " : " + text);
			}
			
			HttpResponse<String> responseIcone = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/usertimeline/icon")
					.header("", "text/plain").asString();
			
			URL urlIcone = new URL(responseIcone.getBody());	
			jLabel1.setIcon(new ImageIcon(urlIcone));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void sendTweet(){
		
		String tweet = jTextField1.getText();
		if(tweet != ""){
			try {
				HttpResponse<String> response = Unirest
						.get("http://localhost:8080/TwitterCRest/twitterc/update/send/" + tweet)
						.header("", "json/application").asString();
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void initUserInfo(){
		
	}
	
	
}
