# Reproducer

`x-request-id` from request headers is not being logged correctly with MDC and reactor context.

## Test

`GET http://localhost:8080/test`

#### Logs

`x-request-id` is missing in second log message.

```agsl
03:22:19.954 [reactor-http-nio-4] [x-request-id=131e6572-e4ba-4fe9-b754-a2f8a16e4a65] TRACE org.zalando.logbook.Logbook
03:22:20.743 [reactor-http-nio-5] [x-request-id=] TRACE org.zalando.logbook.Logbook
03:22:21.019 [reactor-http-nio-5] [x-request-id=131e6572-e4ba-4fe9-b754-a2f8a16e4a65] TRACE org.zalando.logbook.Logbook
03:22:21.027 [reactor-http-nio-4] [x-request-id=131e6572-e4ba-4fe9-b754-a2f8a16e4a65] TRACE org.zalando.logbook.Logbook
```

After updating the `x-request-id` header to some new value, the second log has the old request id.

```agsl
03:22:41.075 [reactor-http-nio-4] [x-request-id=6e3a1f48-47c1-4a50-a4c0-8269d3b783a3] TRACE org.zalando.logbook.Logbook
03:22:41.096 [reactor-http-nio-5] [x-request-id=131e6572-e4ba-4fe9-b754-a2f8a16e4a65] TRACE org.zalando.logbook.Logbook
03:22:41.344 [reactor-http-nio-5] [x-request-id=6e3a1f48-47c1-4a50-a4c0-8269d3b783a3] TRACE org.zalando.logbook.Logbook
03:22:41.346 [reactor-http-nio-4] [x-request-id=6e3a1f48-47c1-4a50-a4c0-8269d3b783a3] TRACE org.zalando.logbook.Logbook
```
