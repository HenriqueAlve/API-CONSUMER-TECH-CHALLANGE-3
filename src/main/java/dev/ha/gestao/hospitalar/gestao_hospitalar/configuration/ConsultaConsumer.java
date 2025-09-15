package dev.ha.gestao.hospitalar.gestao_hospitalar.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ha.gestao.hospitalar.gestao_hospitalar.dto.CreateConsultaRecordDTO;
import dev.ha.gestao.hospitalar.gestao_hospitalar.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsultaConsumer {

    private final ObjectMapper objectMapper;
    private final MailService mailService;

    public ConsultaConsumer(MailService mailService) {
        this.mailService = mailService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @RabbitListener(queues = "consulta_queue")
    public void processarMensagem(String mensagem) throws Exception {
        CreateConsultaRecordDTO consulta = objectMapper.readValue(mensagem, CreateConsultaRecordDTO.class);


        mailService.enviarEmailConsulta(
                "teste@gmail.com",
                consulta.descricao(),
                consulta.dataHora().toString(),
                consulta.pacienteId()
        );

        System.out.println("📧 E-mail HTML enviado para: teste@gmail.com");
    }

}
