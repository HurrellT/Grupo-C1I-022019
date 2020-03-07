package app.aspects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(1)
public class LoggerAnnotation {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static Logger logger = LoggerFactory.getLogger(LoggerAnnotation.class);

    @Around("execution(* *(..)) && @annotation(app.aspects.Logger)")
    public Object logMethods(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        logMethodInvocationAndParameters(jp);

        long startTime = new Date().getTime();
        Object result = jp.proceed(jp.getArgs());
        long endTime = new Date().getTime();

        logger.info("\nExecution time: " + (endTime - startTime) + "ms", true);
        logger.info("<- " + methodName + " returns \n" + gson.toJson(result) + "\n", true);

        return result;
    }

    private void logMethodInvocationAndParameters(ProceedingJoinPoint jp) {
        String[] argNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Object[] values = jp.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (argNames.length != 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], values[i]);
            }
        }

        logger.info("-> method " + jp.getSignature().getName() + " invocation", true);
        if (!params.isEmpty()) logger.info(gson.toJson(params), true);
    }

}