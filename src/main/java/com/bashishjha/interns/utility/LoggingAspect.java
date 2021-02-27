package com.bashishjha.interns.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.bashishjha.interns.exception.InternException;

@Component
@Aspect
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	public void logServiceException(InternException exception) {
		LOGGER.error(exception.getMessage(), exception);
	}

}
