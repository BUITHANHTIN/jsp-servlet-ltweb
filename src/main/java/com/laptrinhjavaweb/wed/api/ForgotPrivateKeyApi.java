package com.laptrinhjavaweb.wed.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.controller.wed.Key;
import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.service.IAccountService;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/api-forgotprivate" })
public class ForgotPrivateKeyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IAccountService accountService;

	public ForgotPrivateKeyApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");

		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		String path = "E:\\LAPTRINHDAIHOC\\New folder (2)\\jsp-servlet-ltweb-main\\src\\main\\webapp\\FileKey\\private.bin";
		Key key = new Key();
		try {

			key.gennerickey();
		} catch (NoSuchAlgorithmException e1) {

			e1.printStackTrace();
		}
		try {
			boolean check = accountService.updatePublicKey(acount.getId(),
					Base64.getEncoder().encodeToString(key.getPublicKey().getEncoded()));
			key.savePrivateKey(key.getPrivateKey(), path);
		} catch (NoSuchAlgorithmException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
//gui mail
		final String fromEmail = "19130234@st.hcmuaf.edu.vn";
		// Mat khai email cua ban
		final String password = "buithanhtin2001";
		// dia chi email nguoi nhan
		final String toEmail = acount.getUser();
		System.out.println(toEmail);
		final String subject = "Web ban hang";
		BodyPart messageBodyPart1 = new MimeBodyPart();
		try {
			messageBodyPart1.setText("Private Key moi cua ban");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		DataSource source = new FileDataSource(path);
		try {
			messageBodyPart2.setDataHandler(new DataHandler(source));
		} catch (MessagingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			messageBodyPart2.setFileName(path);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Multipart multipart = new MimeMultipart();
		try {
			multipart.addBodyPart(messageBodyPart1);
		} catch (MessagingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			multipart.addBodyPart(messageBodyPart2);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		MimeMessage msg = new MimeMessage(session);
		// set message headers
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
			msg.setReplyTo(InternetAddress.parse(fromEmail, false));
			msg.setSubject(subject, "UTF-8");
			// msg.setText(body, "UTF-8");
			msg.setContent(multipart);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO: handle exception
		}

	}

}
