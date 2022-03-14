# REST_ShortUrl
RESTful Spring application for shortening url.

I plan to implement the following features:
1. QR-code generation
2. time-limited urls
3. manual input
4. statistics tracking 

As for now, this application has 1 feature - short url generation and further redirection.

POST -  generates short url
GET - redirects to the original url

As this is just my pet project I run it only on localhost.

POST example:

```
curl -X POST --data "{"originalUrl": "https://duckduckgo.com"}" "http://localhost:8080/" --header "Content-Type:application/json"
```

Result example:
```
{"id":3,"wrappedUrl":"pdNfc","originalUrl":"https://duckduckgo.com"}
```

GET example:
```
curl -X GET localhost:8080/pdNfc -v
```

Result example:

```Note: Unnecessary use of -X or --request, GET is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /pdNfc HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.79.1
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 302
< Location: https://mail.google.com/mail/u/0/#spam
< Content-Length: 0
< Date: Mon, 14 Mar 2022 16:33:13 GMT
<
* Connection #0 to host localhost left intact
```

