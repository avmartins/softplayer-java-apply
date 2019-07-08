package br.com.softplan.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1032664457746601907L;
	
	private static final Logger logger = Logger.getLogger(DataInvalidaException.class);
	
	private String id;

	public DataInvalidaException(String id) {
		super(String.format("Data Invalido : '%s'", id));
		this.id = id;
		logger.error(String.format("Data Invalido : '%s'", id));
	}

	public String getId() {
		return this.id;
	}

}
