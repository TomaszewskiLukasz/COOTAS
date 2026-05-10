package com.tomaszewski.cootas.admin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("MailServiceImpl Unit Tests")
class MailServiceImplTest {

    private MailServiceImpl mailService;

    @Mock
    private MailSender mailSender;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mailService = new MailServiceImpl(mailSender);
        ReflectionTestUtils.setField(mailService, "sender", "noreply@cootas.com");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Should send email with correct recipient, subject, and body")
    void shouldSendEmailWithCorrectFields() {
        // Arrange
        String to = "user@example.com";
        String subject = "Session Activation";
        String text = "Please click the link to activate your session";

        // Act
        mailService.sendMail(to, subject, text);

        // Assert
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage sentMessage = captor.getValue();

        //noinspection ConstantConditions
        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(text, sentMessage.getText());
    }

    @Test
    @DisplayName("Should use configured sender email address")
    void shouldUseConfiguredSenderEmail() {
        // Arrange
        ReflectionTestUtils.setField(mailService, "sender", "custom@cootas.com");

        // Act
        mailService.sendMail("user@example.com", "Subject", "Body");

        // Assert
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        assertEquals("custom@cootas.com", captor.getValue().getFrom());
    }

    @Test
    @DisplayName("Should handle mail sending exceptions gracefully without throwing")
    void shouldHandleMailExceptionGracefully() {
        // Arrange
        doThrow(new MailException("SMTP connection failed") {})
            .when(mailSender).send(any(SimpleMailMessage.class));

        // Act & Assert - Should not throw
        mailService.sendMail("user@example.com", "Subject", "Body");

        verify(mailSender).send(any(SimpleMailMessage.class));
    }
}








