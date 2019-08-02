package com.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.filter.CallBackBoolean;

public class SendMail 
{
	private static final String HOST = "smtp.naver.com";
	private static final String FROM = "yeonamchun@naver.com";
	private static final String PORT = "465";
	private static final String PROTOCOL = "smtp";
	private String MAIL_ID = "";
	private String MAIL_PASS = "";
	
	/**
	 * 생성자 메일 서버 아이디와 메일 패스워드를 받는다.
	 * @param _mailId	메일 아이디
	 * @param _mailPass	메일 패스워드
	 */
	public SendMail(String _mailId, String _mailPass)
	{
		this.MAIL_ID = _mailId;
		this.MAIL_PASS = _mailPass;
	}
	
	/**
	 * Nave SMTP 를 이용한 메일 보내기
	 * @param _sender	보내는 사람 정보
	 * @param _subject	메일 제목
	 * @param _toMail	받는 사람 메일 주소
	 * @param _toMsge	메일 본문 내용
	 * @return boolean 정상 : true / 실패 : false
	 */
	public boolean send(String _sender, String _subject, String _toMail, String _toMsge,  CallBackBoolean callbackboolean)
	{
		boolean result = false;
	
		try
		{
			//프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			//네이버 SMTP 사용시
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.transport.protocol",this.PROTOCOL);
			props.put("mail.smtp.host", this.HOST);
			props.put("mail.smtp.port", this.PORT);  // 보내는 메일 포트 설정
			props.put("mail.smtp.user", this.FROM);
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", this.PORT);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
		
			Message msg = new MimeMessage(
					Session.getDefaultInstance(props, new MailAuth(
							this.MAIL_ID, 
							this.MAIL_PASS
							)
					)
			);
			
			msg.setFrom(new InternetAddress(this.FROM, MimeUtility.encodeText(_sender,"UTF-8","B"))); //보내는 사람 설정
			InternetAddress[] address = {new InternetAddress(_toMail)};
	    
			msg.setRecipients(Message.RecipientType.TO, address); //받는 사람설정
			msg.setSubject(_subject); //제목설정
			msg.setSentDate(new java.util.Date()); //보내는 날짜 설정
			msg.setContent(_toMsge,"text/html; charset=UTF-8"); //내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); //메일 보내기
			result = true;
			
			callbackboolean.bool(result);
		}
		catch(MessagingException ex)
		{
			System.out.println("mail send error : "+ex.getMessage());
			ex.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("error : "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}

class MailAuth extends javax.mail.Authenticator 
{
	private String ID;
	private String PASS;
	
	/**
	 * 접속 정보
	 * @param _mailId naver 메일 아이디
	 * @param _mailPass naver 메일 패스워드
	 */
	public MailAuth(String _mailId, String _mailPass)
	{
		this.ID = _mailId;
		this.PASS = _mailPass;
	}
	
	/*
	 * 메일 접속 정보 객체화
	 */
	public PasswordAuthentication getPasswordAuthentication() 
	{
		PasswordAuthentication pa = null;
		/*
		 *   네이버에서 메일 환경설정에서 POP3/SMTP 사용함으로 설정해야 된다.
		 */
		// 네이버나 Gmail 사용자 계정 설정.
		// Gmail의 경우 @gmail.com을 제외한 아이디만 입력한다.
		if(this.ID.length() > 0 && this.PASS.length() > 0)
		{
			pa = new PasswordAuthentication(this.ID, this.PASS);
		}
		
		return pa;
	}
}
