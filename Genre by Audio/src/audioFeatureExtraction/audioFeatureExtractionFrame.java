package audioFeatureExtraction;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import jAudioFeatureExtractor.DataModel;
import jAudioFeatureExtractor.DataTypes.RecordingInfo;
import jAudioFeatureExtractor.jAudioTools.AudioSamples;

public class audioFeatureExtractionFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane;
	private JTextField TF_File;
	private String path = "";
	public audioFeatureExtractionFrame() {
		setTitle("Genre Classification by Audio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 688, 390);
		mainContentPane = new JPanel();
		mainContentPane.setBackground(UIManager.getColor("Button.darkShadow"));
		mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(null);
		getContentPane().setLayout(null);
		
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
					path = fileChooser.getSelectedFile().getAbsolutePath();
					TF_File.setText(path);
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
		
		JButton btnExtractAudioFeatures = new JButton("Extract audio features");
		btnExtractAudioFeatures.setBounds(28, 118, 141, 23);
		mainContentPane.add(btnExtractAudioFeatures);
		
		btnExtractAudioFeatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractAudioFeature(path);
			}
		});
		
	}
	
	
	public void extractAudioFeature(String path){
		File extractedFiletoTest = new File(path);

        String randomID = Integer.toString((int) Math.random());

        AudioSamples sampledExampleFile = null;
		try {
			sampledExampleFile = new AudioSamples(extractedFiletoTest,randomID,false);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
        RecordingInfo[] samplefileInfo = new RecordingInfo[5];
        samplefileInfo[1] = new RecordingInfo(randomID, path, sampledExampleFile, true);

        double samplingrate= sampledExampleFile.getSamplingRateAsDouble();
        int windowsize= 4096;

        try {
        	OutputStream valsavepath = new FileOutputStream(".\\values");
        	OutputStream defsavepath = new FileOutputStream(".\\definitions");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        boolean[] featurestosaveamongall = new boolean[10];
        Arrays.fill(featurestosaveamongall, Boolean.TRUE);

        DataModel mfccDM = new DataModel("C:/Users/TimothyJohn/Downloads/jAudio/features.xml",null);
       
        try {
        	mfccDM.extract(windowsize, 0.5, samplingrate, true, true, false, samplefileInfo, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /// invokes the writeValuesARFFHeader function.
	}
}
