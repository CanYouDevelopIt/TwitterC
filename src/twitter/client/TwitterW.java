package twitter.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TwitterW extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JScrollPane jScrollPane2;
	private DefaultListModel<String> listStatus = new DefaultListModel<String>();
	private JList<String> jList1 = new JList<String>(listStatus);	
	private boolean afficherUserTimeline = true;
	
	public TwitterW(){
		setTitle("TwitterC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		setResizable(false);
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jButton1 = new JButton("Update");
		jButton1.setBounds(310, 330, 100, 20);
		
		jButton1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				sendTweet();
			}
		});
		

		JButton jButtonFriendsTimeline = new JButton("My Friend TimeLine");
		jButtonFriendsTimeline.setBounds(430, 330, 150, 20);
		jButtonFriendsTimeline.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				getFriendsTimelineService();
			}
		});
		
		JButton jButtonUserTimeline = new JButton("My Timeline");
		jButtonUserTimeline.setBounds(600, 330, 120, 20);
		
		jButtonUserTimeline.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				getUserTimeline();
			}
		});
		
		jButtonUserTimeline.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				getUserTimeline();
			}
		});
		
		jLabel1 = new JLabel("Icone");
		jLabel1.setBounds(30, 315, 48, 48);
		
		jTextField1 = new JTextField();
		jTextField1.setBounds(90, 330, 200, 20);
		
		//jList1.setCellRenderer(new StatusCellRenderer());
		//jList1.setBounds(25, 10, 350, 150);
		
		jScrollPane2 = new JScrollPane(jList1);
		jScrollPane2.setBounds(25, 10, 750, 300);
		
		contentPane.add(jButton1);
		contentPane.add(jButtonFriendsTimeline);
		contentPane.add(jButtonUserTimeline);
		contentPane.add(jLabel1);
		contentPane.add(jTextField1);
		contentPane.add(jScrollPane2);

		getIcone();
		
		Timer t = new Timer("Twitter Updater`", false);
		t.scheduleAtFixedRate(new TimerTask() {
		@Override public void run() {
			if(afficherUserTimeline)
				getUserTimeline();
			else
				getFriendsTimelineService();
		} }, 0, 30000);
		
		setVisible(true);
	}
	
	public void getUserTimeline(){
		
		afficherUserTimeline = true;
		System.out.println("GO");
		
		try {
			HttpResponse<String> response = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/usertimeline/get")
					.header("", "json/application").asString();
			
			JSONArray JSONArrayStatus = new JSONArray(response.getBody());
			
			listStatus.clear();
			for (int i = 0; i < JSONArrayStatus.length(); i++) {
				final String date = JSONArrayStatus.getJSONObject(i).getJSONArray("date").get(0).toString();
				final String text = JSONArrayStatus.getJSONObject(i).getJSONArray("text").get(0).toString();
				final String user = JSONArrayStatus.getJSONObject(i).getJSONArray("user").get(0).toString();
				URL urlImage = new URL(JSONArrayStatus.getJSONObject(i).getJSONArray("image").get(0).toString());

				final JLabel jLabel = new JLabel(); 
				jLabel.setIcon(new ImageIcon(urlImage));
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						listStatus.addElement(jLabel + " -> @" + user + " : " + text);
					} });
				
				//listStatus.addElement(date + " -> @" + user + " : " + text);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void getIcone(){
		
		HttpResponse<String> responseIcone;
		try {
			responseIcone = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/usertimeline/icon")
					.header("", "text/plain").asString();
			
			URL urlIcone = new URL(responseIcone.getBody());

			jLabel1.setIcon(new ImageIcon(urlIcone));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void getFriendsTimelineService(){

		afficherUserTimeline = false;
		String friend = jTextField1.getText();
		
		try {
			HttpResponse<String> response = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/friendstimeline/get/" + friend)
					.header("", "json/application").asString();
			
			JSONArray JSONArrayStatus = new JSONArray(response.getBody());
			
			listStatus.clear();
			for (int i = 0; i < JSONArrayStatus.length(); i++) {
				String date = JSONArrayStatus.getJSONObject(i).getJSONArray("date").get(0).toString();
				String text = JSONArrayStatus.getJSONObject(i).getJSONArray("text").get(0).toString();
				String user = JSONArrayStatus.getJSONObject(i).getJSONArray("user").get(0).toString();
				URL urlImage = new URL(JSONArrayStatus.getJSONObject(i).getJSONArray("image").get(0).toString());

				JLabel jLabel = new JLabel(); 
				jLabel.setIcon(new ImageIcon(urlImage));
				listStatus.addElement( jLabel + " -> @" + user + " : " + text);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendTweet(){
		
		String tweet = jTextField1.getText();
		if(tweet != ""){
			try {
				Unirest.get("http://localhost:8080/TwitterCRest/twitterc/update/send/" + tweet)
						.header("", "json/application").asString();
				
				getUserTimeline();
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
