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
import com.tezbus.backend.dto.SendPassengerDetailsDto;
import com.tezbus.backend.entity.Trip;
import com.tezbus.backend.entity.User;
import com.tezbus.backend.enums.NotificationType;
import com.tezbus.backend.property.AmazonSnsProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    @Override
    @Async
    public ReadSmsMessageDto sendOnPassengerReservation(Trip trip, SendPassengerDetailsDto sendPassengerDetailsDto, User user) {
        String content = "Здравствуйте, "+ user.getFirstName() + " "+ user.getLastName() +"!\n" +
                "\n" +
                "Новое бронирование.\n" +
                "\n" +
                "* * *\n" +
                "\n" +
                "Вы получили запрос на поездку:\n" +
                "\n" +
                "" + trip.getDepartureAddress().getCity().getName() +", "+trip.getDepartureAddress().getStreetName()+" -\n" +
                "" + trip.getDestinationAddress().getCity().getName() +", "+trip.getDestinationAddress().getStreetName() +"\n" +
                "\n" +
                ""+ DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(trip.getStartTime())+"\n" +
                "\n" +
                "От: "+ sendPassengerDetailsDto.getPassengerName() +"\n" +
                "Номер телефона: "+ sendPassengerDetailsDto.getPassengerPhone() +"\n" +
                "Желаемое количество мест: "+ sendPassengerDetailsDto.getDesiredSeatCount() +"\n" +
                "\n" +
                "Ваши дальнейшие действия:\n" +
                "\n" +
                "- Созвониться с пассажиром,\n" +
                "- Договориться о месте встречи,\n" +
                "- Отправиться в путь!\n" +
                "\n" +
                "С наилучшими пожеланиями,\n" +
                "Команда Tezbus\n";

        send(content, user.getPhoneNumber());

        CreateSmsMessageDto createSmsMessageDto = new CreateSmsMessageDto();
        createSmsMessageDto.setContent(content);
        createSmsMessageDto.setNotificationType(NotificationType.RESERVATION);
        createSmsMessageDto.setPhoneNumber(user.getPhoneNumber());
        createSmsMessageDto.setTripId(trip.getId());

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
