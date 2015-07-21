package de.jeha.s3ush;

/**
 * @author jenshadlich@googlemail.com
 */
class S3Params {

    private final String accessKey;
    private final String secretKey;
    private final String endpoint;
    private final String bucket;
    private final String key;

    S3Params(String accessKey, String secretKey, String endpoint, String bucket, String key) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
        this.bucket = bucket;
        this.key = key;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public String getKey() {
        return key;
    }

}
