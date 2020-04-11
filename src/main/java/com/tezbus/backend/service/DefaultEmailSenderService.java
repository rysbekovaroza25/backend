package com.tezbus.backend.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.tezbus.backend.dto.CreateEmailDto;
import com.tezbus.backend.dto.ReadEmailDto;
import com.tezbus.backend.property.AmazonSesProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailSenderService implements EmailSenderService {

	@Autowired
	private AmazonSesProperty amazonSesProperty;

	@Autowired
	private EmailService emailService;

	@Override
	public ReadEmailDto send(CreateEmailDto createEmailDto) {
		try {
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials(amazonSesProperty.getAccessKey(), amazonSesProperty.getSecretKey());
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.EU_CENTRAL_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

			SendEmailRequest request = new SendEmailRequest()
					.withDestination(
							new Destination().withToAddresses(createEmailDto.getRecipientEmail()))
					.withMessage(new Message()
							.withBody(new Body()
									.withText(new Content()
											.withCharset("UTF-8").withData(createEmailDto.getBody())))
							.withSubject(new Content()
									.withCharset("UTF-8").withData(createEmailDto.getTitle())))
					.withSource(amazonSesProperty.getMailFrom());
			client.sendEmail(request);

			System.out.println("Email sent!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return emailService.create(createEmailDto);
	}

}
