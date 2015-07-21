package sun.net.www.protocol.s3;

import de.jeha.s3ush.S3URLConnection;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @author jenshadlich@googlemail.com
 */
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        return new S3URLConnection(url);
    }

}
