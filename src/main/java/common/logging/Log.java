package common.logging;

import org.mule.api.MuleMessage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

public class Log 
{
  private String environment;
  private String service;
  private String correlationId;
  private Integer errorCode;
  private String errorMessage;
  private String message;
  
  public String getEnvironment()
  {
    return environment;
  }
  public void setEnvironment(String environment)
  {
    this.environment = environment;
  }
  public String getService()
  {
    return service;
  }
  public void setService(String service)
  {
    this.service = service;
  }
  public String getCorrelationId()
  {
    return correlationId;
  }
  public void setCorrelationId(String correlationId)
  {
    this.correlationId = correlationId;
  }
  public Integer getErrorCode()
  {
    return errorCode;
  }
  public void setErrorCode(Integer errorCode)
  {
    this.errorCode = errorCode;
  }
  public String getErrorMessage()
  {
    return errorMessage;
  }
  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }
  public String getMessage()
  {
    return message;
  }
  public void setMessage(String message)
  {
    this.message = message;
  }
}
