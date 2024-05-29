package com.benevenuto.parkingSystem.configuration;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

@Configuration
public class awsConfiguration {

   @Value("${cloud.aws.accessKey}")
   private String accessKey;

   @Value("${cloud.aws.secretKey}")
   private String secretKey;

   @Value("${cloud.aws.region}")
   private Region region;

   @Bean
   @Primary
   public SqsAsyncClient amazonSQSAsync()
   {
      AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
      return SqsAsyncClient.builder()
              .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
              .region(this.region)
              .build();
   }
}
