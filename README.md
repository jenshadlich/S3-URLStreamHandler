# S3 URLStreamHandler for Java

The S3 URLStreamHandler makes it possible to retrieve a object from S3 in a simple way by providing a handler for the `s3` protocol.

Java does not provide a good standard way to add protocols. This solution relies on internal implementation details which is kind of bad: the handler must be a class named `Handler` inside the `sun.net.www.protocol.s3` package. Works at least with Java 8 :smile:. Let's see what will happen in future versions :grimacing:.

E.g. with `IOUtils` from Apache `commons-io` it's very easy to retrieve the content of an object:
```
String content = IOUtils.toString(new URL("s3://accessKey:secrectKey@bucket.endpoint/key"));
```

##### URL format:
```
s3://<accessKey>:<secretKey>@<bucket>.<endpoint>/<key>
```
The endpoint would normally be `s3.amazonaws.com` but if you want to use some other S3 service (like Ceph with radosgw) you are free to do so :smile:

##### S3 client configuration
The S3 client configuration is not directly accessible because of the nature the protocol handler. My current solution is to use system properties.

The following properties are supported:
```
s3.handler.userAgent
s3.handler.protocol (default: "https")
s3.handler.signerOverride
```
Just call e.g. `System.setProperty("s3.handler.protocol", "http")` in your main.
