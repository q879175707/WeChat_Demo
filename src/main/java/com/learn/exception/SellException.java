package com.learn.exception;

import com.learn.enums.ResultEnum;

/**
 * 2017-06-11 18:55
 */
public class SellException extends RuntimeException{
	private static final long serialVersionUID = 2142870824374260362L;
	private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
