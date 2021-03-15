package ua.lviv.javaclub.annotation.processor;

import static java.util.stream.Collectors.toList;

import com.google.auto.service.AutoService;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic.Kind;

@AutoService(Processor.class)
public class CoffeeJugMagicToStringValidatorProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.printf("++++++++++++++++++++++++++++++++++++ Starting %s %n", this.getClass().getName());
    for (TypeElement annotation : annotations) {
      Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
      annotatedElements.forEach(this::processAnnotatedClass);
    }
    System.out.printf("------------------------------------ %s finished%n", this.getClass().getName());
    return false;
  }

  private void processAnnotatedClass(Element element) {
    System.out.printf("Processing %s annotated class%n", element.getSimpleName().toString());
    var toStringMethods = element.getEnclosedElements().stream()
        .filter(e -> e.getKind() == ElementKind.METHOD)
        .filter(e -> e.getSimpleName().toString().equals("toString"))
        .map(e -> (ExecutableType) e.asType())
        .collect(toList());

    var zeroArgsToString = toStringMethods.stream().anyMatch(m -> m.getParameterTypes().size() == 0);

    if (!zeroArgsToString) {
      processingEnv.getMessager().printMessage(Kind.ERROR, "Zero-args toString() method is missing", element);
    }
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
