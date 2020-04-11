package com.tezbus.backend.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AmazonSnsProperty {
	@Value("${aws.sns.sms.access-key}")
	private String accessKey;

	@Value("${aws.sns.sms.secret-key}")
	private String secretKey;

	@Value("${aws.sns.sms.sender-id}")
	private String senderId;

	@Value("${aws.sns.sms.sms-type}")
	private String smsType;
}
