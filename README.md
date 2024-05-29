# Sistema de Estacionamento com AWS SQS

Este projeto é um sistema de gerenciamento de estacionamento desenvolvido para fins de estudo. Ele utiliza Amazon SQS (Simple Queue Service) para comunicação assíncrona entre seus componentes. A aplicação é construída em Java utilizando o AWS SDK para Java.

## Descrição

O sistema de estacionamento gerencia operações básicas de entrada de veículos e utiliza Amazon SQS para comunicação entre diferentes partes da aplicação. O SQS permite a enfileiramento de mensagens de eventos, garantindo que esses eventos sejam processados de forma confiável e desacoplada.

### Funcionalidades

- **Envio de Mensagens**: Eventos de entrada de veículos são enviados para uma fila SQS.
- **Recebimento de Mensagens**: Os eventos na fila SQS são recebidos e processados por componentes dedicados.

### Tecnologias Utilizadas

- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
- ![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
- ![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
- ![SQS](https://img.shields.io/badge/AWS%20SQS-FF9900?style=for-the-badge&logo=amazon-aws&logoColor=white)

### Estrutura do Projeto

O projeto inclui duas principais classes:

1. **SQSListenerVehiclesQueue**: Responsável por enviar mensagens de eventos para a fila SQS.
2. **SQSPublishVehiclesQueue**: Responsável por receber e processar mensagens da fila SQS.

Este projeto é ideal para aprender como integrar Amazon SQS em uma aplicação Java e entender os conceitos básicos de comunicação assíncrona usando filas.
