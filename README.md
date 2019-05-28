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

## Configuration

You can adjust the count of used processors:

| property key | default value | Description |
|--------------|---------------|-------------|
| app.min-cpus | 2             | The minimum count of used processors. This application uses at least two processors despite of you defining less: one for the producer and one for the consumer. |
| app.max-cpus | 16            | The maximum count of used processors. This application uses at most the system's processor count despite of you defining more. |

## How it works

| Class | Description |
|-------|-------------|
| [Producer](/src/main/java/com/github/borisskert/concurrent/Producer.java) | Produces increasing integer numbers and add them as Consumers into the Queue. The Producer is the first item who gets added to the Queue and gets executed by the ExecutionService. |
| [Consumer](/src/main/java/com/github/borisskert/concurrent/Consumer.java) | Gets a integer number and will be added into Queue by the Producer. Gets executed by Java's ThreadPoolExecutor within the ExecutionService. |
| [ExecutionService](/src/main/java/com/github/borisskert/concurrent/ExecutionService.java) | Initializes Java's ThreadPoolExecutor and execute all item added into the Queue. |
| [Queue](/src/main/java/com/github/borisskert/concurrent/Queue.java) | Wrapper class around ExecutionService to provide methods to add items to Queue and look for queue-size. |
