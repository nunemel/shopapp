package com.egs.shopapp.exception;

/**
 * Defines an exception to be thrown when an entity is not found.
 *
 * @author Nune Melikyan
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 490142924065699925L;

	public NotFoundException(String entity) {
        super(entity + " not found");
    }
}
