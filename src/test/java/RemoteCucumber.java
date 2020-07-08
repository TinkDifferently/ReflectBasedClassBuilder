import VirtualFileWrapper.CucumberClassBuilder;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.runner.RunnerSupplier;
import cucumber.runtime.junit.JUnitOptions;
import gherkin.events.PickleEvent;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;
import tests.LaunchAction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RemoteCucumber {
    @Test
    public void playArgentina() throws InitializationError {
        Class<?> clazz=new CucumberClassBuilder().withPackageName("generated.types.cucumber")
                .withClassName("Auto" + UUID.randomUUID().toString().replace("-", ""))
                .withFeaturePath("src/test/resources/features")
                .withGlue("my.stepdefs")
                .withImport(CucumberOptions.class)
                .withTags("@Argentina")
                .build();
        new Cucumber(clazz).run(new RunNotifier());
    }

    @Test
    public void playBrazil() throws InitializationError {
        Class<?> clazz=new CucumberClassBuilder().withPackageName("generated.types.cucumber")
                .withClassName("Auto" + UUID.randomUUID().toString().replace("-", ""))
                .withFeaturePath("src/test/resources/features")
                .withGlue("my.stepdefs")
                .withImport(CucumberOptions.class)
                .withTags("@Brazil")
                .build();
        new Cucumber(clazz).run(new RunNotifier());
    }
}
