package com.fleetmanagementapi.services;

import com.fleetmanagementapi.models.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmailServiceImplTest {
    @Test
    public void testSendMail() throws MessagingException {
        // Preparamos los mocks para el servicio de emails
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        EmailServiceImpl emailService = new EmailServiceImpl(javaMailSender, null); // Mocking TemplateEngine as it's not used in this test

        // Mockeamos mimeMessage la clase necesaria para que java reciba los correos
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // 2. Creamos un input de prueba como esqueleto para el correo o body
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setDestinatario("maricelafuentes100@gmail.com");
        emailDTO.setAsunto("TEST");
        emailDTO.setMensaje("Esto es un test");

        byte[] attachmentBytes = "LocacionesTest".getBytes();

        // Cuando se quiera enviar el mensaje con mockito mockeamos la llamada
        doNothing().when(mimeMessage).setContent(any());
        doNothing().when(javaMailSender).send(mimeMessage);

        // Invocamos el sendEmail
        emailService.sendMail(emailDTO, attachmentBytes);

        // verificamos que que se haya enviado el correo o email
        verify(javaMailSender, times(1)).createMimeMessage();
        verify(mimeMessage, times(1)).setContent(any());
        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}