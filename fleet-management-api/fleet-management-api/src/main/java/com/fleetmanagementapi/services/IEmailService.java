package com.fleetmanagementapi.services;
import com.fleetmanagementapi.models.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    public void sendMail(EmailDTO email, byte[] attachmentBytes) throws MessagingException;
}
