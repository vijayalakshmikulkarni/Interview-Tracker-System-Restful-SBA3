package com.wellsfargo.fsd.its.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ItsException extends Exception {

	public ItsException(String errMsg) {
		super(errMsg);
	}

}
