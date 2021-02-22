package com.egs.shopapp.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Nune
 *
 */
public class SaveFailedException extends RuntimeException {
	
	private static final long serialVersionUID = 490142924065699925L;

	public SaveFailedException(String entity) {
        super(entity + " save failed.");
    }

	public SaveFailedException(HttpStatus status, String message) {
		
	}

}
