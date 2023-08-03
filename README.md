# spring-boot-save-logs-from-rabbitmq

## Sending a Message to RabbitMQ

You can send a StandardMessage to RabbitMQ via the command line using curl. First, make sure that the RabbitMQ Management Plugin is enabled and accessible. Then use the following command:
```bash
curl -i -u user:password \
     -H "content-type:application/json" \
     -X POST http://localhost:15672/api/exchanges/%2f/amq.default/publish \
     -d '{
          "properties": {},
          "routing_key": "your-routing-key",
          "payload": "{ \"uuid\": \"123\", \"replyToUuid\": \"456\", \"dateSent\": \"2023-08-03T00:00:00Z\", \"sender\": \"test sender\", \"topic\": \"test topic\", \"exchange\": \"test exchange\", \"msgContent\": \"test content\", \"targetObject\": \"test object\" }",
          "payload_encoding": "string"
        }'
```
Please replace user:password with your RabbitMQ username and password, and replace "your-routing-key" with your actual routing key. The payload should be the StandardMessage as a JSON string.

Also note that the %2f in the URL is URL-encoded form of /, which represents the default vhost. If you're using a different vhost, please adjust accordingly.

Lastly, the URL and port (15672 is the default port for the management plugin) should match your RabbitMQ server's configuration.
