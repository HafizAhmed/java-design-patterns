## FindBugs and PMD report

I use an Intellij plugin - QAPlug to run Findbugs and PMD report.

FindBugs has no error.
PMD has two errors but those two errors don't make any sense. 

The error is "Ensure that resources like this FileReader object are closed after use". It looks like that the PMD simply tries to find the following pattern from my code.
```java
try{
  //do something
} catch (Exception e) {
  //do something
}finally{
  // close files
}
```
The producer has two functions. Produce is responsible for producing resources and close is responsible for releasing resources. The LenderUtils in loan pattern lends the resources produced by producers to consumers and then release the resources after the consumer consumes. Thus, it is not possible to close the resourses in one single function (produce function). 

Here is the code of LenderUtils. The producer is finally closed in LenderUtils regardless of whether exceptions occur or not in try. If producer.close throws exceptions, the LenderUtils will throw those exceptions to users and users need to resolve the issue based on the exceptions. 
```java
try{
    T resource = producer.produce(consumer.getParameter());
    consumer.consume(resource);
} catch (Exception e) {
    throw new LoanException(e.getMessage(),e);
} finally{
    producer.close();
}
```

Screenshot result:
![alt text](./etc/FindBugs_PMD.png "FindBugs and PMD report screenshot")
