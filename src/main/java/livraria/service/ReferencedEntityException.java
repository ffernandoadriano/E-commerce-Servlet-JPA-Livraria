package livraria.service;

import webf.service.ServiceException;

public class ReferencedEntityException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ReferencedEntityException() {
		super();
	}

	public ReferencedEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReferencedEntityException(String message) {
		super(message);
	}

	public ReferencedEntityException(Throwable cause) {
		super(cause);
	}

}
