package viandasYaTests;

import static org.junit.Assert.*;
import static org.reflections.ReflectionUtils.*;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

import com.google.common.base.Predicates;
import org.springframework.web.bind.annotation.RestController;

public class ArchTests {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testAllClasesInPackageServicesAreTransactional() {
        Reflections reflections = new Reflections("app.api");
        Set<Class<? extends Serializable>> allClasses = reflections.getSubTypesOf(Serializable.class);
        for (Class myClass : allClasses) {
            Set<Method> allMethods = getAllMethods(myClass, withModifier(Modifier.PUBLIC),
                    Predicates.and(
                            Predicates.not(withPrefix("get")),
                            Predicates.not(withPrefix("set"))));
            System.out.println(allMethods);
            this.assertAllMethodsAreTransactional(allMethods);
        }
    }

    private void assertAllMethodsAreTransactional(Set<Method> allMethods) {
        for (Method method : allMethods) {
            assertNotNull(method.getAnnotation(org.springframework.transaction.annotation.Transactional.class));
        }
    }

    @Test
    public void testAllControllerClasesHasCrossOriginAnnotation() {
        Reflections reflections = new Reflections("app.api");
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(org.springframework.web.bind.annotation.RestController.class);
        this.assertAllClassesAreCrossOrigin(allClasses);
    }

    private void assertAllClassesAreCrossOrigin(Set<Class<?>> allClasses) {
        for (Class aClass : allClasses) {
            assertTrue(aClass.getName().contains("Controller"));
            assertNotNull(aClass.getAnnotation(org.springframework.web.bind.annotation.RestController.class));
        }
    }

}