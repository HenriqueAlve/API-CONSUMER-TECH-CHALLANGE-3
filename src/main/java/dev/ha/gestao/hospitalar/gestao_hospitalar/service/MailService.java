package dev.ha.gestao.hospitalar.gestao_hospitalar.service;

import dev.ha.gestao.hospitalar.gestao_hospitalar.dto.CreateConsultaRecordDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailConsulta(String para, String descricao, String dataHora, UUID pacienteId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(para);
        helper.setSubject("Nova Consulta Agendada");

        String corpoHtml = """
    <html>
    <body style="font-family:Arial,sans-serif;background-color:#f4f4f4;padding:20px;">
        <div style="max-width:600px;margin:auto;background:white;padding:30px;border-radius:10px;box-shadow:0 4px 10px rgba(0,0,0,0.1);">
            <h2 style="color:#4CAF50;text-align:center;">Você tem uma nova consulta!</h2>
            <div style="text-align:center;margin:20px 0;">
                <img src='https://cdn-icons-png.flaticon.com/512/3135/3135715.png' style="width:80px;height:80px;">
            </div>
            <p><strong>Data/Hora:</strong> %s</p>
            <p><strong>Descrição:</strong> %s</p>
            <p><strong>Paciente ID:</strong> %s</p>
            <div style="text-align:center;margin-top:30px;">
                <a href='https://seusite.com/consultas' style="background-color:#4CAF50;color:white;padding:12px 25px;text-decoration:none;border-radius:5px;font-weight:bold;">Ver Consultas</a>
            </div>
            <p style="margin-top:30px;font-size:12px;color:#888;text-align:center;">
                Caso não tenha agendado esta consulta, por favor ignore este e-mail.
            </p>
        </div>
    </body>
    </html>
    """.formatted(dataHora, descricao, pacienteId);
        helper.setText(corpoHtml, true);
        mailSender.send(message);
    }


}
