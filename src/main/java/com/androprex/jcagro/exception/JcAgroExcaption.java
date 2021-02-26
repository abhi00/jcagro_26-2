package com.androprex.jcagro.exception;

public class JcAgroExcaption extends RuntimeException {
	
	
	public JcAgroExcaption(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public JcAgroExcaption(String exMessage) {
        super(exMessage);
    }
}
