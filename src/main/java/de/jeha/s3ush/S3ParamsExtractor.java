package de.jeha.s3ush;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @author jenshadlich@googlemail.com
 */
class S3ParamsExtractor {

    public static S3Params extract(URL url) throws IOException {

        //aws credentials
        String accessKey = null;
        String secretKey = null;

        if (url.getUserInfo() != null) {
            String[] credentials = url.getUserInfo().split("[:]");
            accessKey = URLDecoder.decode(credentials[0], "UTF-8");
            secretKey = URLDecoder.decode(credentials[1], "UTF-8");
        }

        // bucket
        String bucket = url.getHost().substring(0, url.getHost().indexOf("."));

        // key
        String key = url.getPath().substring(1);

        // endpoint
        String endpoint = url.getHost().substring(url.getHost().indexOf(".") + 1);
        if (url.getPort() != 80 && url.getPort() != 443) {
            endpoint += ":" + url.getPort();
        }

        return new S3Params(accessKey, secretKey, endpoint, bucket, key);
    }

}
