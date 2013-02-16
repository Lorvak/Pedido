/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.Pessoa;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Junior
 */
public class EnviaEmail {

    public static void mandaEmail(Pessoa pessoa) throws AddressException, MessagingException {

        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("tjunior103@hotmail.com", "j1v1s3lv34");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tjunior103@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(pessoa.getEmail())); //Destinatário(s)

//            message.setSentDate(new Date());
            message.setSubject("Dados de usuário do Senac ");
            message.setText("Olá " + pessoa.getNome() + "\n\nSegue os dados de usuários do "
                    + "Sistema SIESD: \nLogin: " + pessoa.getUsuario().getLogin() + "\nSenha: "
                    + pessoa.getUsuario().getSenha() + "\n\natenciosamente \nSilvio Junior \nSecretário(a)");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
           
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
