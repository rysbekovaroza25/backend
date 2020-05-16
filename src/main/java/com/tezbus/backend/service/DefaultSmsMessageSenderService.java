package com.tezbus.backend.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.tezbus.backend.dto.CreateSmsMessageDto;
import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.entity.User;
import com.tezbus.backend.enums.NotificationType;
import com.tezbus.backend.property.AmazonSnsProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultSmsMessageSenderService implements SmsMessageSenderService {

    @Autowired
    private SmsMessageService smsMessageService;

    @Autowired
    private AmazonSnsProperty amazonSnsProperty;

    @Override
    @Async
    public ReadSmsMessageDto sendOnUserRegistration(User user) {
        String content = "Здравствуйте " + user.getFirstName() + ", \n\n" +
                "Вы успешно зарегистрировались в качестве водителя на платформе Tezbus! Теперь вы можете " +
                "выкладывать ваши поездки, назначать цены на них и находить попутчиков. " +
                "Войдите в свой профиль: www.tezbus.com";

        send(content, user.getPhoneNumber());

        CreateSmsMessageDto createSmsMessageDto = new CreateSmsMessageDto();
        createSmsMessageDto.setContent(content);
        createSmsMessageDto.setNotificationType(NotificationType.REGISTRATION);
        createSmsMessageDto.setPhoneNumber(user.getPhoneNumber());

        return smsMessageService.create(createSmsMessageDto);
    }

    private void send(String content, String phoneNumber) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(amazonSnsProperty.getAccessKey(), amazonSnsProperty.getSecretKey());
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue(amazonSnsProperty.getSenderId()).withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue(amazonSnsProperty.getSmsType()).withDataType("String"));

        PublishRequest request = new PublishRequest();
        request.withMessage(content)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes);
        PublishResult result = snsClient.publish(request);
    }
}
