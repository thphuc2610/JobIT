package com.r2s.findInternship.infracstructure.exception.exception_v2;

import com.r2s.findInternship.domain.constant.HttpStatusConstant;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class ValidationExceptionV2 extends ExceptionCustomV2{

    public ValidationExceptionV2(Map<String, Object> errors) {
        super("DATA INVALID", errors, getStackTraceAsString(), HttpStatusConstant.BAD_REQUEST);
    }

    private static String getStackTraceAsString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        new Exception().printStackTrace(pw);
        return sw.toString();
    }

}
