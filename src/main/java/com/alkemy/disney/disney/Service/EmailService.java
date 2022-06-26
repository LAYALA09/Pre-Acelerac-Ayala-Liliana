package com.alkemy.disney.disney.Service;

public interface EmailService {
    /**
     * Sends a welcome email to the recipient received as parameter using SendGrid
     * @param to email of a new user
     */
    void sendWelcomeEmailTo(String to);
}
