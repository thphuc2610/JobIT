package com.r2s.findInternship.infracstructure.exception.exception_v2;

import com.r2s.findInternship.domain.constant.HttpStatusConstant;

public class AccessDeniedExceptionV2 extends ExceptionCustomV2 {

    public AccessDeniedExceptionV2() {
        super("ACCESS DENIED", "NOT ENOUGH PERMISSION", HttpStatusConstant.FORBIDDEN);
    }

}
