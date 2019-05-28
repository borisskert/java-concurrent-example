# java concurrent api with spring-boot

This project shows an example to use Java's concurrent API in spring-boot

## Build this solution

```bash
$ mvn clean package
```

## Run this solution

```bash
$ mvn spring-boot:run
```

With 'fast' profile:

```bash
$ mvn spring-boot:run -Dspring.profiles.active=fast
```

## Configuration

You can adjust the count of used processors:

| property key | default value | Description |
|--------------|---------------|-------------|
| app.min-cpus | 2             | The minimum count of used processors. This application uses at least two processors despite of you defining less: one for the producer and one for the consumer. |
| app.max-cpus | 16            | The maximum count of used processors. This application uses at most the system's processor count despite of you defining more. |
| app.producer.min-wait-time | 250 | The minimum wait time the producer will wait until producing an consumer. This properties simulating asynchronous created work items. |
| app.producer.max-wait-time | 1000 | The maximum wait time the producer will wait until producing an consumer. |
| app.consumer.min-wait-time | 1000 | The minimum wait time the consumer will wait until producing an consumer. This simulates processor's workload. |
| app.consumer.max-wait-time | 4000 | The maximum wait time the consumer will wait until producing an consumer. |

## How it works

| Class | Description |
|-------|-------------|
| [Producer](/src/main/java/com/github/borisskert/concurrent/Producer.java) | Produces increasing integer numbers and add them as Consumers into the Queue. The Producer is the first item who gets added to the Queue and gets executed by the ExecutionService. |
| [Consumer](/src/main/java/com/github/borisskert/concurrent/Consumer.java) | Gets a integer number and will be added into Queue by the Producer. Gets executed by Java's ThreadPoolExecutor within the ExecutionService. |
| [ExecutionService](/src/main/java/com/github/borisskert/concurrent/ExecutionService.java) | Initializes Java's ThreadPoolExecutor and execute all item added into the Queue. |
| [Queue](/src/main/java/com/github/borisskert/concurrent/Queue.java) | Wrapper class around ExecutionService to provide methods to add items to Queue and look for queue-size. |
