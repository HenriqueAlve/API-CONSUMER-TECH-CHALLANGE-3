# 🏥 Gestão Hospitalar - Consumer

Sistema de gestão hospitalar desenvolvido com Spring Boot e RabbitMQ para processamento assíncrono de consultas médicas e envio de notificações por e-mail.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5.5**
- **RabbitMQ** - Mensageria assíncrona
- **JavaMailSender** - Envio de e-mails
- **Maven** - Gerenciamento de dependências
- **Jackson** - Processamento JSON

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **JDK 21** ou superior
- **Maven 3.x**
- **RabbitMQ Server** (rodando e acessível)
- **Servidor SMTP** configurado para envio de e-mails

## ⚙️ Configuração

### Variáveis de Ambiente

Configure as seguintes variáveis de ambiente no seu sistema:

**Windows (CMD):**
```cmd
set SPRING_RABBITMQ_USERNAME=seu_usuario
set SPRING_RABBITMQ_PASSWORD=sua_senha
set SERVER_PORT=8082
```

**Windows (PowerShell):**
```powershell
$env:SPRING_RABBITMQ_USERNAME="seu_usuario"
$env:SPRING_RABBITMQ_PASSWORD="sua_senha"
$env:SERVER_PORT="8082"
```

**Linux/Mac:**
```bash
export SPRING_RABBITMQ_USERNAME=seu_usuario
export SPRING_RABBITMQ_PASSWORD=sua_senha
export SERVER_PORT=8082
```

### Configuração do RabbitMQ

O projeto está configurado para conectar ao RabbitMQ com as seguintes configurações padrão:

- **Host:** 192.168.100.3
- **Porta:** 5672
- **Fila:** consulta_queue
- **Username:** Configurado via variável `SPRING_RABBITMQ_USERNAME` (padrão: teste)
- **Password:** Configurado via variável `SPRING_RABBITMQ_PASSWORD` (padrão: senha123)

### Configuração da Porta do Servidor

A porta do servidor pode ser configurada através da variável de ambiente `SERVER_PORT`. Se não configurada, a porta padrão será **8082**.

## 🔧 Instalação

1. **Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd API-CONSUMER-TECH-CHALLANGE-3
```

2. **Configure as variáveis de ambiente** (veja seção acima)

3. **Compile o projeto:**
```bash
mvnw clean install
```

## ▶️ Execução

### Modo Desenvolvimento

```bash
mvnw spring-boot:run
```

### Executar o JAR

```bash
java -jar target/consumer.jar
```

A aplicação estará disponível na porta configurada (padrão: **8082**).

## 📚 Funcionalidades

### 🔔 Consumer de Consultas

O sistema escuta a fila `consulta_queue` do RabbitMQ e processa mensagens de consultas médicas automaticamente.

**Estrutura da Mensagem (JSON):**
```json
{
  "descricao": "Consulta de rotina",
  "dataHora": "2025-10-07T14:30:00",
  "pacienteId": "12345"
}
```

### 📧 Notificações por E-mail

Quando uma mensagem é recebida, o sistema:
1. Deserializa a mensagem JSON
2. Extrai os dados da consulta
3. Envia um e-mail HTML formatado para o paciente
4. Registra o envio no console

### ⚡ Processamento Assíncrono

Utiliza RabbitMQ para processamento assíncrono de mensagens, garantindo:
- **Desacoplamento** entre sistemas
- **Escalabilidade** horizontal
- **Resiliência** no processamento
- **Garantia de entrega** das mensagens

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/dev/ha/gestao/hospitalar/gestao_hospitalar/
│   │   ├── GestaoHospitalarApplication.java      # Classe principal
│   │   ├── configuration/
│   │   │   └── ConsultaConsumer.java              # Consumer RabbitMQ
│   │   ├── dto/
│   │   │   └── CreateConsultaRecordDTO.java       # DTO de Consulta
│   │   └── service/
│   │       ├── MailConfig.java                    # Configuração de E-mail
│   │       └── MailService.java                   # Serviço de E-mail
│   └── resources/
│       ├── application.properties                 # Configurações básicas
│       └── application.yml                        # Configurações RabbitMQ
└── test/
    └── java/dev/ha/gestao/hospitalar/gestao_hospitalar/
        └── GestaoHospitalarApplicationTests.java  # Testes
```

## 🧪 Testes

Execute os testes com:

```bash
mvnw test
```

## 🐰 Testando com RabbitMQ

Para testar o consumer, você pode enviar uma mensagem para a fila usando:

1. **Interface Web do RabbitMQ** (http://localhost:15672)
2. **Comando via CLI:**
```bash
# Publicar mensagem na fila
rabbitmqadmin publish routing_key=consulta_queue payload='{"descricao":"Consulta Teste","dataHora":"2025-10-07T14:30:00","pacienteId":"123"}'
```

## 🔐 Segurança

- Credenciais do RabbitMQ configuradas via variáveis de ambiente
- Não commitar senhas ou credenciais no repositório
- Usar variáveis de ambiente em produção

## 📝 Logs

O sistema registra no console:
- Mensagens recebidas do RabbitMQ
- E-mails enviados com sucesso
- Erros de processamento

## 🛠️ Troubleshooting

### Erro de conexão com RabbitMQ
- Verifique se o RabbitMQ está rodando
- Confirme o IP e porta configurados
- Valide as credenciais de acesso

### E-mails não enviados
- Verifique a configuração do servidor SMTP
- Confirme as credenciais de e-mail
- Verifique os logs de erro

## 📄 Licença

Este projeto é parte do Tech Challenge 3 - FIAP - Fase 3

## 👥 Autores

Desenvolvido para o desafio técnico da FIAP - Gestão Hospitalar

---

**Versão:** 1.0.0  
**Última atualização:** Outubro 2025

