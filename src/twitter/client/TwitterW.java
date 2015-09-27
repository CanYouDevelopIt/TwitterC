package twitter.client;

import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

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
	private StatusListModel listStatus = new StatusListModel();
	private JList<Status> jList1 = new JList<Status>(listStatus);	
	
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
		
		jLabel1 = new JLabel("Icone");
		jLabel1.setBounds(30, 215, 48, 48);
		
		jTextField1 = new JTextField();
		jTextField1.setBounds(90, 230, 200, 20);
		
		jList1.setCellRenderer(new StatusCellRenderer());
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
		
		Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses;
	    
		try {
			statuses = twitter.getHomeTimeline();
			
		    for (Status status : statuses) {
			    listStatus.add(status);
		    }
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initUserInfo(){
		Twitter twitter = TwitterFactory.getSingleton();
		try {
			User user = twitter.showUser(twitter.getId());
			URL url = new URL(user.getProfileImageURL());
			System.out.println(url);
			ImageIcon img = new ImageIcon(url);
			jLabel1.setIcon(img);		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
}
