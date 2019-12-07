package ar.edu.unq.desapp.groupj.backend.aspects;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unq.desapp.groupj.backend.model.User;
import ar.edu.unq.desapp.groupj.backend.services.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.ws.rs.core.Response;

import static java.lang.System.getenv;

@Aspect
public class UserAuthAspect implements ApplicationContextAware {

	private static final String GOOGLE_CLIENT_ID = "VIANDASYA_GOOGLE_CLIENT_ID";
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
	    this.applicationContext = applicationContext;
	}
	
	
	@Around("@annotation(ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired)")
	public Response userAuthenticationRequiredWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable  {
        ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		String authenticationHeader = request.getHeader("Authorization");

		if( authenticationHeader == null ||
            authenticateUser( authenticationHeader ) == null )
		{
            return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).entity( "Unauthorized access." ).build();
        }

		return (Response)joinPoint.proceed();
	}
	
	
	// Map id token to business User model...
	protected User authenticateUser(String idTokenString) throws Throwable {
		User user = null;
		GoogleIdToken idToken = verifyIdToken(idTokenString);
		
		if( idToken != null )
		{
			GoogleIdToken.Payload payload = idToken.getPayload();
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			
			if( emailVerified ) {
				UserService userService = (UserService)this.applicationContext.getBean("services.user");
				if( userService != null ) {
					user = userService.findBySocialId(email);
					userService.setLoggedUser(user);
				}
			}
		}
		
		return user;
	}
	
	
	// Verify user token against Google... 
	private GoogleIdToken verifyIdToken(String idTokenString) throws Throwable {
		GoogleIdToken idToken = null;
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),new JacksonFactory())
		    // Specify the CLIENT_ID of the app that accesses the backend:
		    .setAudience(Collections.singletonList(getenv(GOOGLE_CLIENT_ID)))
		    .build();

		try
		{
			idToken = verifier.verify(idTokenString);
		}
		catch( Exception ex ) {
            idToken = null;
		}
		
		return idToken;
	}
}


