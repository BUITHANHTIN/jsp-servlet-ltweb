package com.laptrinhjavaweb.wed.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.service.IAccountService;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/api-login" })
public class LoginApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IAccountService accountService;

	public LoginApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");

		String user = request.getParameter("user");
		// quen pass or doi mat khau
		String direc = request.getParameter("direc");
		// khi bam vao quen mat khau
		if (direc.equals("forgetPass")) {
			String checkUser = accountService.UserExist(user);

			// 0 la false else 1 true
			int check;
			if (checkUser == null) {
				check = 0;
				out.println(check);
			} else {
				// co ton tai
				check = 1;

				// gui mail
				final String fromEmail = "19130234@st.hcmuaf.edu.vn";
				// Mat khai email cua ban
				final String password = "buithanhtin2001";
				// dia chi email nguoi nhan
				final String toEmail = user;
				final String subject = "Web bán hàng online";
				// noi dung
				Random random = new Random();
				int randomToEmail = random.nextInt(10000) + 10000;
				SessionUtils.getInstance().putValue(request, "randomToEmail", randomToEmail);
				final String body = "Ma xac nhan OTP cua ban la: " + randomToEmail;
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
					msg.setText(body, "UTF-8");
					msg.setSentDate(new Date());
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
					Transport.send(msg);
				} catch (MessagingException e) {
					// TODO: handle exception
				}
				out.println(check);
			}
		} else if (direc.equals("editPass")) {
			String code = request.getParameter("code");
			String yourNewPassword = request.getParameter("yourNewPassword");
			String aNewPassword = request.getParameter("aNewPassword");

			// -0:code k bang nhau or 2 pass k trung
			// 1:thanh cong
			int check = 0;
			String rd = String.valueOf(SessionUtils.getInstance().getValue(request, "randomToEmail"));
			if (!code.equals(rd) || !yourNewPassword.equals(aNewPassword)) {
				check = 0;
			} else {
				accountService.updatePass(user, yourNewPassword);
				check = 1;

			}
			out.println(check);
		}

	}

}
