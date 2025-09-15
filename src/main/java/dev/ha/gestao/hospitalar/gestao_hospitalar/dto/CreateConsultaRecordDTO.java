package dev.ha.gestao.hospitalar.gestao_hospitalar.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateConsultaRecordDTO(
         UUID medicoId,
         UUID pacienteId,
         LocalDateTime dataHora,
         String descricao
) {
}
