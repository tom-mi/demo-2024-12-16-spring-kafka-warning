# demo-2024-12-16-spring-kafka-warning

This is a very simple kafka batch listener. As the test shows, it works perfectly fine.
However, during startup, the following warning is printed:

```
[Test worker] WARN  o.s.k.l.a.BatchMessagingMessageListenerAdapter - No batch message converter is set. because record message converter is null.
```
