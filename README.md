# S3 URLStreamHandler for Java

The S3 URLStreamHandler makes it possible to retrieve a object from S3 in a simple way by registering the `s3` protocol.

E.g. with `IOUtils` from Apache `commons-io` it's very easy to retrieve the content of an object:
```
String content = IOUtils.toString(new URL("s3://accessKey:secrectKey@bucket.endpoint/key"));
```

###### URL format:
```
s3://<accessKey>:<secretKey>@<bucket>.<endpoint>/<key>
```
