package audioManipulation;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.UIManager;

public class MainForm extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane;
	private JTextField TF_File;


	public MainForm() 
	{
		setTitle("Genre Classification by Audio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 688, 390);
		mainContentPane = new JPanel();
		mainContentPane.setBackground(UIManager.getColor("Button.darkShadow"));
		mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(null);
		
		JLabel LBL_ChooseFile = new JLabel("Choose File:");
		LBL_ChooseFile.setForeground(new Color(255, 153, 0));
		LBL_ChooseFile.setBounds(34, 46, 83, 16);
		LBL_ChooseFile.setFont(new Font("Sitka Text", Font.BOLD, 12));
		mainContentPane.add(LBL_ChooseFile);
		
		TF_File = new JTextField();
		TF_File.setBounds(122, 43, 386, 20);
		TF_File.setEditable(false);
		mainContentPane.add(TF_File);
		TF_File.setColumns(10);
		
		JButton BTN_OpenFile = new JButton("Open File");
		BTN_OpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					TF_File.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		BTN_OpenFile.setBounds(521, 42, 89, 23);
		BTN_OpenFile.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BTN_OpenFile.setFont(new Font("Sitka Text", Font.BOLD, 12));
		mainContentPane.add(BTN_OpenFile);
		
		Box HB_File = Box.createHorizontalBox();
		HB_File.setBounds(25, 31, 601, 76);
		HB_File.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainContentPane.add(HB_File);
		
		JButton BTN_Extract = new JButton("Extract");
		BTN_Extract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String ffmpegloc = "\"" + "C:\\Users\\Pheebz\\Desktop\\ffmpeg\\bin\\ffmpeg.exe" + "\"";
				String source = "\"" + TF_File.getText() + "\"";
				String target = "\"" + "C:\\Users\\Pheebz\\Desktop\\test.wav" + "\"";
				
				try {
					Runtime.getRuntime().exec(ffmpegloc + " -i " + source + " -ab 160k -ac 2 -ar 44100 -vn " + target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		BTN_Extract.setFont(new Font("Sitka Text", Font.BOLD, 13));
		BTN_Extract.setBounds(94, 74, 89, 23);
		mainContentPane.add(BTN_Extract);
	}
}
