package ua.lviv.javaclub.annotation.processor;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import com.google.auto.service.AutoService;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class CoffeeJugMagicBuilderProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.printf("++++++++++++++++++++++++++++++++++++ Starting %s %n", this.getClass().getName());
    for (TypeElement annotation : annotations) {
      Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
      annotatedElements.forEach(this::processAnnotatedClass);
    }
    System.out.printf("------------------------------------ %s finished%n", this.getClass().getName());
    return true;
  }

  private void processAnnotatedClass(Element element) {
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

    var annotatedClassPackage = element.getEnclosingElement();
    var annotatedClassName = element.getSimpleName().toString();
    var builderClassName = annotatedClassName + "Builder";
    var fields = element.getEnclosedElements().stream()
        .filter(e -> e.getKind().isField())
        .collect(toList());

    var constructorArguments = fields.stream()
        .map(f -> f.getSimpleName().toString())
        .collect(joining(", "));

    String fieldsAndSetters = fields.stream()
        .map(f -> generateFieldAndSetter(builderClassName, f))
        .collect(joining("\n"));

    var builderClassCode = builderClassCodeTemplate
        .formatted(annotatedClassPackage, builderClassName, annotatedClassName, fieldsAndSetters, constructorArguments);

    System.out.println(builderClassCode);
  }

  private String generateFieldAndSetter(String builderClassName, Element f) {
    var fieldType = f.asType().toString();
    var fieldName = f.getSimpleName().toString();

    var fieldAndMethodCode = """
          private %2$s %3$s;
          
          public %1$s %3$s(%2$s %3$s) {
            this.%3$s = %3$s;
            return this;
          } 
        """;

    return fieldAndMethodCode.formatted(builderClassName, fieldType, fieldName);
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Set.of("ua.lviv.javaclub.annotation.processor.CoffeeJugMagic");
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
