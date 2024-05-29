# Sistema de Estacionamento com AWS SQS

Este projeto é um sistema de gerenciamento de estacionamento desenvolvido para fins de estudo. Ele utiliza Amazon SQS (Simple Queue Service) para comunicação assíncrona entre seus componentes. A aplicação é construída em Java utilizando o AWS SDK para Java.

## Descrição

O sistema de estacionamento gerencia operações básicas de entrada de veículos e utiliza Amazon SQS para comunicação entre diferentes partes da aplicação. O SQS permite a enfileiramento de mensagens de eventos, garantindo que esses eventos sejam processados de forma confiável e desacoplada.

### Funcionalidades

- **Envio de Mensagens**: Eventos de entrada e saída de veículos são enviados para uma fila SQS.
- **Recebimento de Mensagens**: Os eventos na fila SQS são recebidos e processados por componentes dedicados.

### Tecnologias Utilizadas

- **Java 11**
- **Maven 3.6**
- **AWS SDK para Java 2.x**
- **Amazon SQS**

### Estrutura do Projeto

O projeto inclui duas principais classes:

1. **SQSListenerVehiclesQueue**: Responsável por enviar mensagens de eventos para a fila SQS.
2. **SQSPublishVehiclesQueue**: Responsável por receber e processar mensagens da fila SQS.

Este projeto é ideal para aprender como integrar Amazon SQS em uma aplicação Java e entender os conceitos básicos de comunicação assíncrona usando filas.
