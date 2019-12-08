package ar.edu.unq.desapp.groupj.backend.architecture;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationNotRequired;
import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

public class ArchitectureTest {

    @Test
    public void testRestServicesAuthenticationCheck() throws SecurityException, NoSuchMethodException {

        // create scanner and disable default filters (that is the 'false' argument)
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        // add include filters which matches all the classes (or use your own)
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));


        // get matching classes defined in the package
        final Set<BeanDefinition> classes = provider.findCandidateComponents("ar.edu.unq.desapp.groupj.backend.rest");

        // this is how you can load the class type from BeanDefinition instance
        for (BeanDefinition bean: classes) {
            try {
                Class<?> resourceClass = Class.forName(bean.getBeanClassName());
                Arrays.stream(resourceClass.getDeclaredMethods()).filter( m -> m.getName().contains("get") ).forEach( m -> {
                    if( methodRequiresAuthenticationControl(m) ) {
                        Assert.assertTrue("Class " + bean.getBeanClassName() + ", method " + m.getName() + " does not have annotation for user authentication.", methodHasAuthenticationControl(m) );
                    }
                });
            }
            catch( Exception ex ) {
                Assert.assertNull( ex.getMessage(), ex );
            }

        }
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
}