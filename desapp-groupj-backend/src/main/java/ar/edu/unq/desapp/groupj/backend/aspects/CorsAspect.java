package ar.edu.unq.desapp.groupj.backend.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import javax.ws.rs.core.Response;

@Aspect
public class CorsAspect {
	   
	@AfterReturning(pointcut = "execution(* ar.edu.unq.desapp.groupj.backend.rest..*(..))", returning= "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		Response response = (Response) result;
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers",
                "x-requested-with, origin, content-type, accept, authorization");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}
}
