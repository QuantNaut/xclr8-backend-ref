package com.xclr8.api.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.amazonaws.services.s3.AmazonS3Client;
import com.xclr8.api.aws.AmazonSNSClientWrapper;
import com.xclr8.api.config.AWSProperties;
import com.xclr8.api.service.util.AmazonSNSPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.sns.model.MessageAttributeValue;


@Service
@Transactional
public class AWSService {

    private final Logger log = LoggerFactory.getLogger(AWSService.class);

    public String pemCertificate = "";

    public String pemKey = "";

    public static final Map<AmazonSNSPlatform, Map<String, MessageAttributeValue>> attributesMap = new HashMap<AmazonSNSPlatform, Map<String, MessageAttributeValue>>();
    static {
        attributesMap.put(AmazonSNSPlatform.ADM, null);
        attributesMap.put(AmazonSNSPlatform.GCM, null);
        attributesMap.put(AmazonSNSPlatform.APNS, null);
        attributesMap.put(AmazonSNSPlatform.APNS_SANDBOX, null);
    }


    @Autowired
    private AmazonS3Client amazonS3Client;

    @Autowired
    private AmazonSNSClientWrapper amazonSNSClientWrapper;

    @Inject
    private AWSProperties awsProperties;

    /*@PostConstruct
    public void initializeAppleCertificates() {
        StringBuilder resultCertificate = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("pem/ezydevwithoutkey.pem").getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                resultCertificate.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            log.debug("Exception reading PEM certificate : {}", e);
        }
        pemCertificate = resultCertificate.toString();

        StringBuilder resultKey = new StringBuilder("");
        ClassLoader classLoaderKey = getClass().getClassLoader();
        File keyFile = new File(classLoaderKey.getResource("pem/ezydevwithkey.pem").getFile());
        try (Scanner scanner = new Scanner(keyFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                resultKey.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            log.debug("Exception reading PEM Key : {}", e);
        }
        pemKey = resultKey.toString();
    }

    public String getS3BaseUrl() {
        return awsProperties.getS3().getBaseUrl();
    }

    public void sendAppleAppNotification(String applicationName, String deviceToken, String message) {
        log.debug("APPLE PEM Certificate : {}", pemCertificate);
        log.debug("APPLE PEM Key : {}", pemKey);

        amazonSNSClientWrapper.pushNotification(AmazonSNSPlatform.APNS, pemCertificate, pemKey, deviceToken,
            applicationName, attributesMap, message);
    }
*/
    public void sendAndroidAppNotification(String serverAPIKey, String applicationName, String registrationId,
                                           String message) {
        amazonSNSClientWrapper.pushNotification(AmazonSNSPlatform.GCM, "", serverAPIKey, registrationId,
            applicationName, attributesMap, message);
    }
}
