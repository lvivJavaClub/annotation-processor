package ua.lviv.javaclub.annotation.processor;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

public class BuilderProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (TypeElement annotation : annotations) {
      Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

      System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

      annotatedElements.stream().forEach(this::processAnnotatedClass);

      System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
    }
    return true;
  }

  private <E extends Element> void processAnnotatedClass(E element) {
    var annotatedClassPackage = element.getEnclosingElement().toString();
    var annotatedClassName = element.getSimpleName().toString();
    var builderClassName = annotatedClassName + "Builder";

    var fields = element.getEnclosedElements()
        .stream()
        .filter(e -> e.getKind() == ElementKind.FIELD)
        .collect(toList());

    var constructorArguments = fields.stream().map(f -> f.getSimpleName().toString()).collect(joining(", "));
    var builderSetters = fields.stream()
        .map(e -> generateFieldsSetters(builderClassName, e))
        .collect(Collectors.joining("\n"));

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

    var builderClassCode = builderClassCodeTemplate.formatted(annotatedClassPackage, builderClassName, annotatedClassName, builderSetters, constructorArguments);
    System.out.println(builderClassCode);

    // TODO: write to a file
    try {
      JavaFileObject builderFile = processingEnv.getFiler()
          .createSourceFile(String.join(".", annotatedClassPackage, builderClassName), element.getEnclosingElement());

      try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
        out.println(builderClassCode);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String generateFieldsSetters(String builderClassName, Element field) {
    var fieldName = field.getSimpleName().toString();
    var fieldType = field.asType().toString();

    var methodCode = """
          private %2$s %3$s;
          
          public %1$s %3$s(%2$s %3$s) {
            this.%3$s = %3$s;
            return this;
          } 
        """.formatted(builderClassName, fieldType, fieldName);

    return methodCode;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Set.of("ua.lviv.javaclub.annotation.processor.Builder");
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_11;
  }
}
