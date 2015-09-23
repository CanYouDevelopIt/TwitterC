package twitter.client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TwitterW extends JFrame{
	
	private JPanel contentPane;
	private JButton jButton1;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JScrollPane jScrollPane2;
	private JList<String> jList1;	
	
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
		
		jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(25, 10, 400, 200);
		
		jList1 = new JList<String>();
		//jList1.setBounds(25, 10, 350, 150);
		jScrollPane2.add(jList1);
		
		contentPane.add(jButton1);
		contentPane.add(jLabel1);
		contentPane.add(jTextField1);
		contentPane.add(jScrollPane2);
		
		setVisible(true);
	}
	
}
