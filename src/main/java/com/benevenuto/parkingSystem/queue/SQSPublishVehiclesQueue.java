package com.benevenuto.parkingSystem.queue;

import com.benevenuto.parkingSystem.Utils.JsonUtil;
import com.benevenuto.parkingSystem.domain.Veiculo;
import com.benevenuto.parkingSystem.domain.dto.VeiculoDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SQSPublishVehiclesQueue {

    @Value("${aws.sqs.queue.name.veiculos}")
    @Setter(AccessLevel.PROTECTED)
    private String queueEndPoint;

    @Autowired
    private final SqsAsyncClient _amazonSqs;

    public void publish(VeiculoDto veiculoDto)
    {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        MessageAttributeValue messageAttributeValue = MessageAttributeValue.builder()
                .stringValue("This is an attribute")
                .dataType("String")
                .build();

        messageAttributes.put("AttributeOne", messageAttributeValue);

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueEndPoint)
                .messageBody(JsonUtil.toJson(veiculoDto))
                .delaySeconds(10)
                .messageAttributes(messageAttributes)
                .build();

        _amazonSqs.sendMessage(sendMessageRequest);
    }

}
