package com.ll.jump.base.exception;

public class ServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private String code;
    
    public ServiceException(String code, String desc) {
        super(desc);
        this.code = code;
    }
    
    public ServiceException(String code, String desc, Throwable cause) {
        super(desc, cause);
        this.code = code;
    }

    public ServiceException(ServiceErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
    }

    public ServiceException(ServiceErrorCode errorCode, Throwable cause) {
        super(errorCode.getDesc(), cause);
        this.code = errorCode.getCode();
    }

    public ServiceException(ServiceErrorCode errorCode, String errInfo) {
        super(errorCode.getDesc() + "[" + errInfo + "]");
        this.code = errorCode.getCode();
    }

    public ServiceException(ServiceErrorCode errorCode, String errInfo, Throwable cause) {
        super(errorCode.getDesc() + "[" + errInfo + "]", cause);
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }

}
