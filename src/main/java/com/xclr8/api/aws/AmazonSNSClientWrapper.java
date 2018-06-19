package com.xclr8.api.aws;

import java.util.HashMap;
import java.util.Map;

import com.xclr8.api.service.util.AmazonSNSPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.EndpointDisabledException;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;



public class AmazonSNSClientWrapper {

    private final Logger log = LoggerFactory.getLogger(AmazonSNSClientWrapper.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final AmazonSNS snsClient;

    public AmazonSNSClientWrapper(AmazonSNS client) {
        this.snsClient = client;
    }

    public PublishResult publishSMS(PublishRequest publishRequest) {
        return snsClient.publish(publishRequest);
    }

    public static String jsonify(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw (RuntimeException) e;
        }
    }

    private CreatePlatformApplicationResult createPlatformApplication(String applicationName,
                                                                      AmazonSNSPlatform platform, String principal, String credential) {
        CreatePlatformApplicationRequest platformApplicationRequest = new CreatePlatformApplicationRequest();
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("PlatformPrincipal", principal);
        attributes.put("PlatformCredential", credential);
        platformApplicationRequest.setAttributes(attributes);
        platformApplicationRequest.setName(applicationName);
        platformApplicationRequest.setPlatform(platform.name());
        return snsClient.createPlatformApplication(platformApplicationRequest);
    }

    private CreatePlatformEndpointResult createPlatformEndpoint(AmazonSNSPlatform platform, String customData,
                                                                String platformToken, String applicationArn) {
        CreatePlatformEndpointRequest platformEndpointRequest = new CreatePlatformEndpointRequest();
        // Custom device data - enable later
        // platformEndpointRequest.setCustomUserData(customData);
        String token = platformToken;
        String userId = null;
        if (platform == AmazonSNSPlatform.BAIDU) {
            String[] tokenBits = platformToken.split("\\|");
            token = tokenBits[0];
            userId = tokenBits[1];
            Map<String, String> endpointAttributes = new HashMap<String, String>();
            endpointAttributes.put("UserId", userId);
            endpointAttributes.put("ChannelId", token);
            platformEndpointRequest.setAttributes(endpointAttributes);
        }
        platformEndpointRequest.setToken(token);
        platformEndpointRequest.setPlatformApplicationArn(applicationArn);
        return snsClient.createPlatformEndpoint(platformEndpointRequest);
    }

    private void deletePlatformApplication(String applicationArn) {
        DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn(applicationArn);
        snsClient.deletePlatformApplication(request);
    }

    private PublishResult publish(String endpointArn, AmazonSNSPlatform platform,
                                  Map<AmazonSNSPlatform, Map<String, MessageAttributeValue>> attributesMap, String message)
        throws EndpointDisabledException, Exception {
        PublishRequest publishRequest = new PublishRequest();
        Map<String, MessageAttributeValue> notificationAttributes = getValidNotificationAttributes(
            attributesMap.get(platform));
        if (notificationAttributes != null && !notificationAttributes.isEmpty()) {
            publishRequest.setMessageAttributes(notificationAttributes);
        }
        publishRequest.setMessageStructure("json");

        Map<String, String> messageMap = new HashMap<String, String>();
        message = getPlatformSampleMessage(platform, message);
        messageMap.put(platform.name(), message);
        message = jsonify(messageMap);
        publishRequest.setTargetArn(endpointArn);

        // Display the message that will be sent to the endpoint/
        log.debug("{Message Body: " + message + "}");
        StringBuilder builder = new StringBuilder();
        builder.append("{Message Attributes: ");
        for (Map.Entry<String, MessageAttributeValue> entry : notificationAttributes.entrySet()) {
            builder.append("(\"" + entry.getKey() + "\": \"" + entry.getValue().getStringValue() + "\"),");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        log.debug(builder.toString());
        log.debug("---------------------------------------" + message);
        publishRequest.setMessage(message);
        return snsClient.publish(publishRequest);
    }

    public void pushNotification(AmazonSNSPlatform platform, String principal, String credential, String platformToken,
                                 String applicationName, Map<AmazonSNSPlatform, Map<String, MessageAttributeValue>> attrsMap,
                                 String message) {

        snsClient.listPlatformApplications().getPlatformApplications().stream()
            .filter(app -> app.getPlatformApplicationArn().contains(applicationName)).forEach(app -> {
            String platformAppArn = app.getPlatformApplicationArn();
            CreatePlatformEndpointResult platformEndpointResult = createPlatformEndpoint(platform,
                "custom data", platformToken, platformAppArn);
            try {
                PublishResult publishResult = publish(platformEndpointResult.getEndpointArn(), platform,
                    attrsMap, message);
                log.debug("Message pushed to device with message ID : {}", publishResult.getMessageId());

            } catch (Exception e) {
                log.debug("Exception Sending push notification : {}", e.getMessage());

            }

        });

    }

    private String getPlatformSampleMessage(AmazonSNSPlatform platform, String message) {
        switch (platform) {
            case APNS:
                Map<String, Object> appleMessageMap = new HashMap<String, Object>();
                Map<String, Object> appMessageMap = new HashMap<String, Object>();
                appMessageMap.put("alert", message);
                appMessageMap.put("sound", "default");
                appMessageMap.put("badge", 1);
                appleMessageMap.put("aps", appMessageMap);
                return jsonify(appleMessageMap);
            case GCM:
                Map<String, Object> androidMessageMap = new HashMap<String, Object>();
                Map<String, String> payload = new HashMap<String, String>();
                payload.put("text", message);
                payload.put("sound", "default");
                androidMessageMap.put("notification", payload);
                return jsonify(androidMessageMap);
            default:
                return "";
        }
    }

    public static Map<String, MessageAttributeValue> getValidNotificationAttributes(
        Map<String, MessageAttributeValue> notificationAttributes) {
        Map<String, MessageAttributeValue> validAttributes = new HashMap<String, MessageAttributeValue>();

        if (notificationAttributes == null)
            return validAttributes;

        for (Map.Entry<String, MessageAttributeValue> entry : notificationAttributes.entrySet()) {
            if (entry.getValue().getStringValue() != null && !entry.getValue().getStringValue().trim().isEmpty()) {
                validAttributes.put(entry.getKey(), entry.getValue());
            }
        }
        return validAttributes;
    }
}
