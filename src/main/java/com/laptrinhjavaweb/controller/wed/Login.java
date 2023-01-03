package com.laptrinhjavaweb.controller.wed;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.service.IAccountService;
import com.laptrinhjavaweb.service.ICardService;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/j_security_check" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	IAccountService account;
	@Inject
	ICardService card;
	ResourceBundle resource = ResourceBundle.getBundle("message");

	public Login() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// code bÃªn form login
		String code = request.getParameter("code");
		if (code != null && code.equals("login")) {
			String mess = request.getParameter("message");
			String messageRegistration = request.getParameter("messageRe");
			String alert = request.getParameter("alert-danger");
			// in ra loi
			if (mess != null) {
				if (mess.equals("message")) {
					request.setAttribute("message", resource.getString(mess));
				} else if (mess.equals("not_permission")) {
					request.setAttribute("message", resource.getString(mess));
				} else if (mess.equals("not_login")) {
					request.setAttribute("message", resource.getString(mess));
				}
			}
			if (messageRegistration != null) {
				request.setAttribute("messageRe", resource.getString(messageRegistration));
			}
			if (alert != null) {
				request.setAttribute("danger", alert);
			}
			// lay Cooki day len form login
			Cookie[] arr = request.getCookies();
			if (arr != null) {
				for (Cookie cookie : arr) {
					if (cookie.getName().equals("usernameCookie")) {
						request.setAttribute("username", cookie.getValue());
					}
					if (cookie.getName().equals("passwordCookie")) {
						request.setAttribute("password", cookie.getValue());
					}
				}
			}
			request.getRequestDispatcher("/views/client/login.jsp").forward(request, response);
		} else if (code != null && code.equals("thoat")) {
			SessionUtils.getInstance().removeValue(request, "USERMODEL");
			SessionUtils.getInstance().removeValue(request, "giohangs");
			response.sendRedirect(request.getContextPath() + "/trangchu");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String user = request.getParameter("j_username");
		String pass = request.getParameter("j_password");
		String name = request.getParameter("name");
		if (action != null && action.equals("login")) {
			Account oneAccount = account.getOneAccount(user, pass);
			if (oneAccount != null) {
				SessionUtils.getInstance().putValue(request, "USERMODEL", oneAccount);
				// luu Cooki
				Cookie username = new Cookie("usernameCookie", user);
				Cookie password = new Cookie("passwordCookie", pass);
				username.setMaxAge(60 * 60 * 24 * 365);
				password.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(username);
				response.addCookie(password);

				if (oneAccount.getIsAdmin() == 1) {
					response.sendRedirect(request.getContextPath() + "/admin-home");
				} else if (oneAccount.getIsAdmin() == 0) {
					response.sendRedirect(request.getContextPath() + "/trangchu");
				}

			} else if (oneAccount == null) {
				response.sendRedirect(
						request.getContextPath() + "/j_security_check?code=login&message=message&alert-danger=alert");
			}
		} else if (action != null && action.equals("registration")) {
			// kiá»ƒm tra cÃ³ tÃªn trong account ch
			if (account.UsernameExist(name) != null) {
				response.sendRedirect(
						request.getContextPath() + "/j_security_check?code=login&messageRe=messageRegistration");
			} else {
				try {
					Key key = new Key();
					key.gennerickey();
				} catch (NoSuchAlgorithmException e1) {

					e1.printStackTrace();
				}
				Account acc = new Account();
				acc.setUsername(name);
				acc.setUser(user);
				acc.setPass(pass);
				acc.setIsAdmin(0);
				acc.setPublicKey(Base64.getEncoder().encodeToString(Key.getPublicKey().getEncoded()));
				// lÆ°u private
				String path = "C:\\Users\\Admin\\Desktop\\Git-ATBM\\jsp-servlet-ltweb\\src\\main\\webapp\\Pdf\\private.bin";
				try {
					Key.savePrivateKey(Key.getPrivateKey(), path);
				} catch (NoSuchAlgorithmException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				// insert vÃ  gá»­i mail thÃ nh cÃ´ng
				long checkInsert = account.InsertAccount(acc);
//gui mail
				final String fromEmail = "19130234@st.hcmuaf.edu.vn";
				// Mat khai email cua ban
				final String password = "buithanhtin2001";
				// dia chi email nguoi nhan
				final String toEmail = user;
				final String subject = "Web ban hang";
				BodyPart messageBodyPart1 = new MimeBodyPart();
				try {
					messageBodyPart1.setText("Dang ki thanh cong");
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
					msg.setContent(multipart);
					msg.setSentDate(new Date());
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
					Transport.send(msg);
				} catch (MessagingException e) {
					// TODO: handle exception
				}

				response.sendRedirect(request.getContextPath() + "/j_security_check	?code=login");

			}
		}

	}

}
