package de.jeha.s3ush;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class S3ParamsExtractorTest {

    @Test
    public void test() throws IOException {
        URL url = new URL("s3://accessKey:secretKey@bucket.endpoint:8080/key");

        final S3Params s3Params = S3ParamsExtractor.extract(url);

        assertEquals("accessKey", s3Params.getAccessKey());
        assertEquals("secretKey", s3Params.getSecretKey());
        assertEquals("endpoint:8080", s3Params.getEndpoint());
        assertEquals("bucket", s3Params.getBucket());
        assertEquals("key", s3Params.getKey());
    }

}
