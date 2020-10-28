package com.bl.ipl;

public class CSVBuilderException extends Exception {
    enum ExceptionType {
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE
    }
    ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
