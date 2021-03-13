# annotation-processor
A demo project for the java compile time annotation processing and code generation

# Java Compilation Process
![Java Compilation Process](https://openjdk.java.net/groups/compiler/doc/compilation-overview/javac-flow.png)

Compile from CLI (cd to project root):
```shell
javac -classpath lib/target/annotations-processor-lib-1.0-SNAPSHOT.jar\
 -processor ua.lviv.javaclub.annotation.processor.CoffeeJugMagicBuilderProcessor\
 -sourcepath client-example/src/main/java/\
 -d client-example/target/javac\
 client-example/src/main/java/ua/lviv/javaclub/annotation/processor/example/Address.java
 ```

Additional compiler options
```shell
-XprintProcessorInfo - Print information about which annotations a processor is asked to process.
-XprintRounds - Print information about initial and subsequent annotation processing rounds.
```


## Related Resources
* http://hannesdorfmann.com/annotation-processing/annotationprocessing101
* https://openjdk.java.net/groups/compiler/doc/compilation-overview/index.html
* https://github.com/square/javapoet
* https://www.baeldung.com/java-annotation-processing-builder
* https://github.com/gunnarmorling/awesome-annotation-processing
