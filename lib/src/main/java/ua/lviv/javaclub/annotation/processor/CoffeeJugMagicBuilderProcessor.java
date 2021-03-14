package ua.lviv.javaclub.annotation.processor;

import com.google.auto.service.AutoService;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class CoffeeJugMagicBuilderProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.println("++++++++++++++++++++++++++++++++++++");
    return false;
  }

/*
    var builderClassCodeTemplate = """
        package %1$s;

        public class %2$s {
          %4$s

          private %2$s() {}

          public static %2$s builder() {
            return new %2$s();
          }

          public %3$s build() {
            return new %3$s(%5$s);
          }
        }
        """;

    var fieldAndMethodCode = """
          private %2$s %3$s;

          public %1$s %3$s(%2$s %3$s) {
            this.%3$s = %3$s;
            return this;
          }
        """;
*/

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Set.of("ua.lviv.javaclub.annotation.processor.CoffeeJugMagic");
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
