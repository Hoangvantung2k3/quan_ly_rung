package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.User;
import com.example.quanlyrung.service.UserService;
import com.sun.mail.imap.Utility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @GetMapping("/forgot_password")
    public String getForgotPasswordView() {
        return "forgot-password-form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();

        try {
            userService.updateResetPasswordToken(token, email);
            String siteURL = request.getRequestURL().toString();
            String site = siteURL.replace(request.getServletPath(), "");
            String resetLink = site + "/reset_password?token=" + token;
            sendEmail(email, resetLink);
            model.addAttribute("message", "Link đặt lại mật khẩu đã được gửi tới email của bạn");
        } catch (UsernameNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());
        } catch (UnsupportedEncodingException | MessagingException exception) {
            model.addAttribute("error", "Error while sending email");
        }
        return "forgot-password-form";
    }

    public void sendEmail(String email, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("duongbach20231@gmail.com", "Support");
        helper.setTo(email);

        String subject = "Đây là link đặt lại mật khẩu";
        String content = "<p>Xin chào,</p>"
                +"<p>Click vào link dưới đây để đặt lại mật khẩu:</p>"
                +"<p><a href=\"" + link + "\">Đặt lại mật khẩu</a></p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);

    }

    @GetMapping("/reset_password")
    public String getResetPasswordView(@Param(value = "token") String token, Model model) {
        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("message", "Invalid token");
            return "message";
        }
        return "reset-password-form";
    }
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getUserByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid token");
            return "message";
        } else {
            userService.updatePassword(user, password);
            model.addAttribute("message", "Đổi mật khẩu thành công");
        }
        return "message";
    }
}
