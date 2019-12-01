package ar.edu.unq.desapp.groupj.backend.services;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSenderService {

    private String smtpServer;
    private String smtpPort;
    private String smtpAuth;
    private String smtpStartTLS;
    private String userName;
    private String password;
    private String from;
    private Thread workingThread;

    public void send( String to, String toCC,
                      String subject, String text )  {

        Properties prop = System.getProperties();
        prop.put( "mail.smtp.host", this.smtpServer );
        prop.put( "mail.smtp.auth", this.smtpAuth );
        prop.put( "mail.smtp.port", this.smtpPort );
        prop.put( "mail.smtp.starttls.enable", this.smtpStartTLS );

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            // from
            msg.setFrom(new InternetAddress(from));

            // to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));

            // cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(toCC, false));

            // subject
            msg.setSubject(subject);

            // content
            msg.setText(text);

            msg.setSentDate(new Date());

            // Get SMTPTransport
            Transport transport = session.getTransport("smtp");

            // connect
            transport.connect(this.smtpServer, this.userName, this.password);

            // send
            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();

        } catch (Exception e) {
            throw new InternalError( "Error sending email to '" + to + "'." + e.getMessage() );
        }
    }

    public void backgroundSend( String to, String toCC, String subject, String text ) {
        this.workingThread = new Thread() {
            public void run() {
                send( to, toCC, subject, text );
            }
        };
        this.workingThread.start();
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public void setSmtpStartTLS(String smtpStartTLS) {
        this.smtpStartTLS = smtpStartTLS;
    }
}