package com.xclr8.api.config;

import javax.inject.Inject;

import com.xclr8.api.service.util.XCLR8ConfigurationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.xclr8.api.aws.AmazonSNSClientWrapper;



@Configuration
public class AWSConfiguration {

    @Inject
    private AWSProperties awsProperties;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(XCLR8ConfigurationConstants.AWS_SNS_ACCESS_KEY, XCLR8ConfigurationConstants.AWS_SNS_SECRET_KEY);
    }

    @Bean
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.US_EAST_1));
        return amazonS3Client;
    }

    @Bean
    public AmazonSNSClientWrapper amazonSNSClient(AWSCredentials awsCredentials) {
        AmazonSNSClient amazonSNSClient = new AmazonSNSClient(awsCredentials);
        amazonSNSClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        AmazonSNSClientWrapper snsClientWrapper = new AmazonSNSClientWrapper(amazonSNSClient);
        return snsClientWrapper;
    }
}
