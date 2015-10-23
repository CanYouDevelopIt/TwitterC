package twitter.client;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TwitterW extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabel1;
	private JLabel nameLabel;
	private JLabel followersLabel;
	private JLabel followedLabel;
	private JTextField jTextField1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPaneFriends;
	private StatusListModel listStatus = new StatusListModel();
	private FriendsListModel listFriends = new FriendsListModel();
	private JList<Status> jList1 = new JList<Status>(listStatus);
	private JList<Friend> jListFriends = new JList<Friend>(listFriends);
	private boolean afficherUserTimeline = true;
	
	public TwitterW(){
		setTitle("TwitterC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		setResizable(false);
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jButton1 = new JButton("Update");
		jButton1.setBounds(750, 530, 100, 20);
		
		jButton1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				sendTweet();
			}
		});
		

		JButton jButtonFriendsTimeline = new JButton("Search");
		jButtonFriendsTimeline.setBounds(870, 530, 150, 20);
		jButtonFriendsTimeline.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				getFriendsTimelineService(jTextField1.getText());
			}
		});
		
		JButton jButtonUserTimeline = new JButton("My Timeline");
		jButtonUserTimeline.setBounds(1040, 530, 120, 20);
		
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
		jLabel1.setBounds(10, 515, 48, 48);
		
		nameLabel = new JLabel();
		nameLabel.setBounds(70, 530, 150, 20);
		
		followersLabel = new JLabel();
		followersLabel.setBounds(180, 530, 100, 20);
		
		followedLabel = new JLabel();
		followedLabel.setBounds(290, 530, 100, 20);
		
		jTextField1 = new JTextField();
		jTextField1.setBounds(435, 530, 300, 20);
		
		jScrollPane2 = new JScrollPane(jList1);
		jScrollPane2.setBounds(240, 10, 940, 500);
		
		jList1.setCellRenderer(new StatusCellRenderer());
		
		jScrollPaneFriends = new JScrollPane(jListFriends);
		jScrollPaneFriends.setBounds(10, 10, 200, 500);
		
		jListFriends.setCellRenderer(new FriendsCellRenderer());
		
		jListFriends.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Friend f = (Friend) jListFriends.getSelectedValue();
				getFriendsTimelineService(f.getScreenname());
	        }

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
	    });
		
		contentPane.add(jButton1);
		contentPane.add(jButtonFriendsTimeline);
		contentPane.add(jButtonUserTimeline);
		contentPane.add(jLabel1);
		contentPane.add(nameLabel);
		contentPane.add(followersLabel);
		contentPane.add(followedLabel);
		contentPane.add(jTextField1);
		contentPane.add(jScrollPane2);
		contentPane.add(jScrollPaneFriends);

		//getIcone();
		getUser();
		getFriends();
		
		Timer t = new Timer("Twitter Updater`", false);
		t.scheduleAtFixedRate(new TimerTask() {
		@Override public void run() {
			if(afficherUserTimeline) {
				getUserTimeline();
			}else {
				if(!jTextField1.getText().equals("")) {
					getFriendsTimelineService(jTextField1.getText());
				} else {
					Friend f = (Friend) jListFriends.getSelectedValue();
					getFriendsTimelineService(f.getScreenname());
				}
				
			}
		} }, 0, 30000);
		
		setVisible(true);
	}
	
	private void getFriends() {
	
		try {
			HttpResponse<String> response = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/friendstimeline/get")
					.header("", "json/application").asString();
			
			JSONArray JSONArrayStatus = new JSONArray(response.getBody());
			
			listFriends.clear();
			for (int i = 0; i < JSONArrayStatus.length(); i++) {
				final String name = JSONArrayStatus.getJSONObject(i).getJSONArray("name").get(0).toString();
				final String screenname = JSONArrayStatus.getJSONObject(i).getJSONArray("screenname").get(0).toString();
				final String image = JSONArrayStatus.getJSONObject(i).getJSONArray("image").get(0).toString();
				
				Friend f = new Friend(name, screenname, image);
				
				listFriends.add(f);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getUserTimeline(){
		
		afficherUserTimeline = true;
		
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
				final String image = JSONArrayStatus.getJSONObject(i).getJSONArray("image").get(0).toString();
				
				final Status s = new Status(user, text, date, image);
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						listStatus.add(s);
					} });
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void getUser(){
		HttpResponse<String> response;
		try {
			response = Unirest
					.get("http://localhost:8080/TwitterCRest/twitterc/usertimeline/user")
					.header("", "text/plain").asString();
			JSONObject JSONObjectUser = new JSONObject(response.getBody());
			String name  = JSONObjectUser.getJSONArray("name").get(0).toString();
			String image  = JSONObjectUser.getJSONArray("image").get(0).toString();
			String background  = JSONObjectUser.getJSONArray("background").get(0).toString();
			int followers = JSONObjectUser.getJSONArray("followers").getInt(0);
			int followed = JSONObjectUser.getJSONArray("followed").getInt(0);
			
			nameLabel.setText(name);
			followersLabel.setText(followers + " followers");
			followedLabel.setText(followed + " followed");
			
			URL urlIcone = new URL(image);
			jLabel1.setIcon(new ImageIcon(urlIcone));
			contentPane.setBackground(Color.decode("#"+background));
			
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
	
	public void getFriendsTimelineService(String friend){

		afficherUserTimeline = false;
		
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
				final String image = JSONArrayStatus.getJSONObject(i).getJSONArray("image").get(0).toString();
				
				final Status s = new Status(user, text, date, image);
				
				listStatus.add(s);
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
