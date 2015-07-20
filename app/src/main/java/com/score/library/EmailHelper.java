package com.score.library;

import java.util.Properties;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by myves.stvictor on 2015-06-30.
 */
public class EmailHelper {
    final private String SENDER_EMAIL = "sms.manager.application.email@gmail.com";
    final private String SENDER_PASSWORD = "beaconsfield";

    private String to;
    private String subject;
    private String bodyMessage;

    public EmailHelper(){}

    public EmailHelper( String to, String subject, String bodyMessage ){
        this.to = to;
        this.subject = subject;
        this.bodyMessage = bodyMessage;
    }

    public boolean sendEmail(){
        if(subject != null && bodyMessage != null) {
            Properties props = new Properties();
            props.put( "mail.smtp.host", "smtp.gmail.com" );
            props.put( "mail.smtp.socketFactory.port", "465" );
            props.put( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" );
            props.put( "mail.smtp.auth", "true" );
            props.put( "mail.smtp.port", "465" );

            Session session = Session.getDefaultInstance( props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication( SENDER_EMAIL, SENDER_PASSWORD );
                }
            } );

            try {
                final Message message = new MimeMessage( session );
                message.setFrom( new InternetAddress( "sms.manager.application.email@gmail.com" ) );
                if(to != null)
                    message.setRecipients( Message.RecipientType.TO, InternetAddress.parse( to ));
                message.setRecipients( Message.RecipientType.CC, InternetAddress.parse( "sms.manager.application.email@gmail.com" ) );
                message.setSubject( subject );
                message.setContent( bodyMessage, "text/html; charset=utf-8" );

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Transport.send( message );
                        } catch( MessagingException e ) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();

                return true;

            } catch( MessagingException e ) {
                throw new RuntimeException( e );
            }
        } else {
            return false;
        }
    }
}
