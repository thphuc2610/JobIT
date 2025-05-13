package com.r2s.findInternship.infracstructure.exception.exception_v2;
import com.r2s.findInternship.domain.constant.HttpStatusConstant;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class ConflictExceptionV2 extends ExceptionCustomV2 {

    public ConflictExceptionV2(Map<String, Object> errors) {
        super("DATA EXISTING", errors, getStackTraceAsString(), HttpStatusConstant.CONFLICT);
    }

    private static String getStackTraceAsString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        new Exception().printStackTrace(pw);
        return sw.toString();
    }

}
