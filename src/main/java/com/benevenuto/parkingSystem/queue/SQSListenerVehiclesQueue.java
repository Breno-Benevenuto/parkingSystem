package com.benevenuto.parkingSystem.queue;


import com.benevenuto.parkingSystem.domain.dto.VeiculoDto;
import com.benevenuto.parkingSystem.services.VeiculoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.concurrent.ExecutionException;

@Setter
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SQSListenerVehiclesQueue {
    @Value("${aws.sqs.queue.name.veiculos}")
    @Setter(AccessLevel.PROTECTED)
    private String queueEndPoint;

    @Autowired
    private  final ObjectMapper objectMapper;

    @Autowired
    private  final VeiculoService _veiculoService;
    @Autowired
    private final SqsAsyncClient _amazonSqs;


    @Scheduled(fixedDelayString = "5000")
    public void receiveCustomer() throws ExecutionException, InterruptedException
    {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueEndPoint)
                .waitTimeSeconds(0)
                .build();

        ReceiveMessageResponse queueResponse = this._amazonSqs.receiveMessage(receiveMessageRequest).get();

        if (queueResponse.hasMessages())
        {
            queueResponse.messages().forEach(
                    msg -> {
                        try {
                            VeiculoDto message = objectMapper.readValue(msg.body(), VeiculoDto.class);

                            _veiculoService.Insert(message);

                            this.deleteMessage(msg);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    private void deleteMessage(Message message)
    {
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                .queueUrl(queueEndPoint)
                .receiptHandle(message.receiptHandle())
                .build();
        this._amazonSqs.deleteMessage(deleteMessageRequest);
    }
}
