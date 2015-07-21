package de.jeha.s3ush;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author jenshadlich@googlemail.com
 */
public class S3URLConnection extends URLConnection {

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    public S3URLConnection(URL url) {
        super(url);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        final String userAgent = System.getProperty("s3.handler.userAgent", null);
        final String protocol = System.getProperty("s3.handler.protocol", "https").toLowerCase();
        final String signerOverride = System.getProperty("s3.handler.signerOverride", null);

        S3Params s3Params = S3ParamsExtractor.extract(url);

        AWSCredentials credentials = new BasicAWSCredentials(s3Params.getAccessKey(), s3Params.getSecretKey());

        ClientConfiguration clientConfig = new ClientConfiguration()
                .withProtocol("https".equals(protocol)
                        ? Protocol.HTTPS
                        : Protocol.HTTP);

        if (userAgent != null) {
            clientConfig.setUserAgent(userAgent);
        }

        if (signerOverride != null) {
            clientConfig.setSignerOverride(signerOverride);
        }

        AmazonS3 s3Client = new AmazonS3Client(credentials, clientConfig);
        s3Client.setEndpoint(s3Params.getEndpoint());

        S3Object object = s3Client.getObject(s3Params.getBucket(), s3Params.getKey());
        return object.getObjectContent();
    }

    @Override
    public void connect() throws IOException {
        // do nothing
    }

}
