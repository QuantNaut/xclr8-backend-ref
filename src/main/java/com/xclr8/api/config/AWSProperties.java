package com.xclr8.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to AWS.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "aws", ignoreUnknownFields = false)
public class AWSProperties {

    private final S3 s3 = new S3();

    private final SNS sns = new SNS();

    public S3 getS3() {
        return s3;
    }

    public SNS getSns() {
        return sns;
    }

    public static class S3 {

        private String bucket = "";

        private String accessKey = "ACCESS_KEY";

        private String secretKey = "SECRET_KEY";

        private String baseUrl = "";

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

    }

    public static class SNS {

        private String region = "";

        private String topicArn = "";

        private String topicOwner = "";

        private String displayName = "";

        private String accessKey = "ACCESS_KEY";

        private String secretKey = "SECRET_KEY";

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getTopicArn() {
            return topicArn;
        }

        public void setTopicArn(String topicArn) {
            this.topicArn = topicArn;
        }

        public String getTopicOwner() {
            return topicOwner;
        }

        public void setTopicOwner(String topicOwner) {
            this.topicOwner = topicOwner;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }

}
