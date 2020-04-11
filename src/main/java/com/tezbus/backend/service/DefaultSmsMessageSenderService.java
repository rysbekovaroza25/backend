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
import com.tezbus.backend.property.AmazonSnsProperty;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ReadSmsMessageDto send(CreateSmsMessageDto createSmsMessageDto) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(amazonSnsProperty.getAccessKey(), amazonSnsProperty.getSecretKey());
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue(amazonSnsProperty.getSenderId()).withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue(amazonSnsProperty.getSmsType()).withDataType("String"));

        PublishRequest request = new PublishRequest();
        request.withMessage(createSmsMessageDto.getContent())
                .withPhoneNumber(createSmsMessageDto.getPhoneNumber())
                .withMessageAttributes(smsAttributes);
        PublishResult result = snsClient.publish(request);

        return smsMessageService.create(createSmsMessageDto);
    }
}
