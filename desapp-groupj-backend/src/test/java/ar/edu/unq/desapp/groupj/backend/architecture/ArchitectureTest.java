package ar.edu.unq.desapp.groupj.backend.architecture;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationNotRequired;
import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import ar.edu.unq.desapp.groupj.backend.services.GenericService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

public class ArchitectureTest {

    @Test
    public void testRestServicesAuthenticationCheck() throws SecurityException, NoSuchMethodException {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        final Set<BeanDefinition> classes = provider.findCandidateComponents("ar.edu.unq.desapp.groupj.backend.rest");

        classes.forEach( bean -> {
            try {
                Class<?> resourceClass = Class.forName(bean.getBeanClassName());
                Arrays.stream(resourceClass.getDeclaredMethods()).forEach( method -> {
                    if( methodRequiresAuthenticationControl(method) ) {
                        Assert.assertTrue("Rest service class " + bean.getBeanClassName() + ", method " + method.getName() + " does not have annotation for user authentication.",
                                methodHasAuthenticationControl(method) );
                    }
                });
            }
            catch( Exception ex ) {
                Assert.assertNull( ex.getMessage(), ex );
            }
        });
    }

    @Test
    public void testWebServicesTransactionalCheck() throws SecurityException, NoSuchMethodException {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        final Set<BeanDefinition> classes = provider.findCandidateComponents("ar.edu.unq.desapp.groupj.backend.services");

        classes.forEach( bean -> {
            try {
                Class<?> resourceClass = Class.forName(bean.getBeanClassName());
                if( resourceClass.getSuperclass().getName().equals(GenericService.class.getName()) ) {
                    Arrays.stream(resourceClass.getDeclaredMethods()).forEach(method -> {
                        if (methodRequiresTransactionalControl(method)) {
                            Assert.assertTrue("Webservice class " + bean.getBeanClassName() + ", method " + method.getName() + " does not have @Transactional annotation.", methodHasTransactionalControl(method));
                        }
                    });
                }
            }
            catch( Exception ex ) {
                Assert.assertNull( ex.getMessage(), ex );
            }
        });
    }

    private boolean methodRequiresAuthenticationControl(Method method) {
        return ( method.getAnnotation(GET.class) != null ||
                method.getAnnotation(PUT.class) != null ||
                method.getAnnotation(DELETE.class) != null );
    }

    private boolean methodHasAuthenticationControl(Method method) {
        return ( method.getAnnotation(UserAuthenticationRequired.class) != null ||
                method.getAnnotation(UserAuthenticationNotRequired.class) != null );
    }

    private boolean methodRequiresTransactionalControl(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }
    private boolean methodHasTransactionalControl(Method method) {
        return ( method.getAnnotation(Transactional.class) != null );
    }

}