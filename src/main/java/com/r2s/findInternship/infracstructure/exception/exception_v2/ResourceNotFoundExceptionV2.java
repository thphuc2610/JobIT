package com.r2s.findInternship.infracstructure.exception.exception_v2;

import com.r2s.findInternship.domain.constant.HttpStatusConstant;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class ResourceNotFoundExceptionV2 extends ExceptionCustomV2 {

    public ResourceNotFoundExceptionV2(Map<String, Object> errors) {
        super("DATA NOT FOUND", errors, getStackTraceAsString(), HttpStatusConstant.NOT_FOUND);
    }

    private static String getStackTraceAsString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        new Exception().printStackTrace(pw);
        return sw.toString();
    }
}