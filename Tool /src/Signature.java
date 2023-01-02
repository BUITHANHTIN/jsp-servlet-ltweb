import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Signature extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFilePdf;
	private JButton btnFilePdf;
	private JTextField textKey;
	private JButton btnFileKey;
	private JButton btnSignFile;
	private JButton btnClear;
	private JTextField textHash;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signature frame = new Signature();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Signature() {
		setTitle("Signature digital sign");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select file to sign");
		lblNewLabel.setBounds(20, 33, 133, 13);
		contentPane.add(lblNewLabel);

		textFilePdf = new JTextField();
		textFilePdf.setBounds(184, 30, 349, 19);
		contentPane.add(textFilePdf);
		textFilePdf.setColumns(10);

		btnFilePdf = new JButton("File");
		btnFilePdf.setBounds(562, 29, 85, 21);
		contentPane.add(btnFilePdf);

		JLabel lblSelectYourKey = new JLabel("Select your key");
		lblSelectYourKey.setBounds(20, 84, 133, 13);
		contentPane.add(lblSelectYourKey);

		textKey = new JTextField();
		textKey.setColumns(10);
		textKey.setBounds(184, 81, 349, 19);
		contentPane.add(textKey);

		btnFileKey = new JButton("File");
		btnFileKey.setBounds(562, 80, 85, 21);
		contentPane.add(btnFileKey);

		btnSignFile = new JButton("Sign file");
		btnSignFile.setBounds(242, 142, 85, 21);
		contentPane.add(btnSignFile);

		btnClear = new JButton("Clear");
		btnClear.setBounds(402, 142, 85, 21);
		contentPane.add(btnClear);

		JLabel Signature = new JLabel("Signature");
		Signature.setBounds(20, 211, 133, 13);
		contentPane.add(Signature);

		textHash = new JTextField();
		textHash.setBounds(184, 186, 349, 91);
		contentPane.add(textHash);
		textHash.setColumns(10);
		addActionListener();
	}

	private void addActionListener() {
		btnClear.addActionListener(this);
		btnFileKey.addActionListener(this);
		btnFilePdf.addActionListener(this);
		btnSignFile.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			textFilePdf.setText("");
			textHash.setText("");
			textKey.setText("");

		} else if (e.getSource() == btnFileKey) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter txtFile = new FileNameExtensionFilter("Portable Document Format (*.bin)", "bin");
			chooser.setFileFilter(txtFile);
			chooser.setMultiSelectionEnabled(false);
			int x = chooser.showOpenDialog(this);
			if (x == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				textKey.setText("" + file);

			}

		} else if (e.getSource() == btnFilePdf) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter txtFile = new FileNameExtensionFilter("Portable Document Format (*.pdf)", "pdf");
			chooser.setFileFilter(txtFile);
			chooser.setMultiSelectionEnabled(false);
			int x = chooser.showOpenDialog(this);
			if (x == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				textFilePdf.setText("" + file);

			}

		} else if (e.getSource() == btnSignFile) {
			String txtPdf = textFilePdf.getText();
			String txtKey = textKey.getText();
			if (txtPdf.equals("") || txtKey.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui long dien day du thong tin");
			} else {
				try {
					PrivateKey key = createPrivateKey(txtKey);
					try {
						textHash.setText(signFile(txtPdf, key));
					} catch (InvalidKeyException | NoSuchProviderException | SignatureException e1) {
						e1.printStackTrace();
					}
				} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

	public PrivateKey createPrivateKey(String path)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		FileInputStream fis = new FileInputStream(path);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
		KeyFactory factory = KeyFactory.getInstance("DSA");
		return factory.generatePrivate(keySpec);
	}

	public String signFile(String pathFilePdf, PrivateKey key) throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidKeyException, SignatureException, IOException, InvalidKeySpecException {

		java.security.Signature dsa = java.security.Signature.getInstance("DSA");

		dsa.initSign(key, new SecureRandom());

		FileInputStream fis = new FileInputStream(pathFilePdf);
		byte[] b = new byte[fis.available()];
		dsa.update(b);
		byte[] bsign = dsa.sign();
		return Base64.getEncoder().encodeToString(bsign);

	}

}
