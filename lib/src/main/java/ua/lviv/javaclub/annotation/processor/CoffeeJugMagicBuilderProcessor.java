package ua.lviv.javaclub.annotation.processor;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

public class CoffeeJugMagicBuilderProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.println("++++++++++++++++++++++++++++++++++++");
    return false;
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
