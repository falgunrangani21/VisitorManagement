package com.efive.VisitorManagement.common;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


//import javax.mail.internet.InetAddress;
public class MailAlerts {

	public static void sendSimpleMessage(String[] to, String subject,
			String messagebody, String from, String password, String hostname,
			int port, String filepath) {

		try {

			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			mailSender.setHost(hostname);
			mailSender.setPort(port);

			mailSender.setUsername(from);
			mailSender.setPassword(password);

			Properties props = mailSender.getJavaMailProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.debug", "true");
			// SimpleMailMessage message = new SimpleMailMessage();
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(messagebody);
			if (filepath != null && filepath.trim().length() > 0) {
				File f = new File(filepath);

				FileSystemResource file = new FileSystemResource(f);
				helper.addAttachment(f.getName(), file);
			}
			mailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}