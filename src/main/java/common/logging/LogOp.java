package common.logging;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.mule.api.ExceptionPayload;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class LogOp implements Callable
{
  private static final Logger logger = Logger.getLogger(LogOp.class);
  
  public void log(String message) 
  {
    System.out.println("Method A of Spring bean sample activated");
    logger.info("MESSAGE: " + message);
    
  }
  
  @Override
  public Object onCall(MuleEventContext eventContext) throws Exception
  {
    MuleMessage message               = eventContext.getMessage();
    
    String environment                = message.getInboundProperty("environment");
    String service                    = ((org.mule.module.launcher.MuleApplicationClassLoader)this.getClass().getClassLoader()).getAppName();
    String correlationId              = message.getMessageRootId();
    Integer errorCode                 = null;
    String errorMessage               = "";
    ExceptionPayload exceptionPayload = message.getExceptionPayload();
    String messageString              = eventContext.getMessageAsString();
    
    if (exceptionPayload != null) 
    {
      errorCode     = exceptionPayload.getCode();
      errorMessage  = exceptionPayload.getMessage();
    }
    
    Log logMessage = new Log();
    logMessage.setEnvironment(environment);
    logMessage.setService(service);
    logMessage.setCorrelationId(correlationId);
    logMessage.setErrorCode(errorCode);
    logMessage.setErrorMessage(errorMessage);
    logMessage.setMessage(messageString);
    
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    
    try 
    { 
      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(logMessage);  
      logger.info(jsonStr); 
    } 
    catch (IOException e) 
    { 
      e.printStackTrace(); 
    } 
    
    return message;
  }
}
