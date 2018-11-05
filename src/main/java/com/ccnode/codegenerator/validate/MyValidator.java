package com.ccnode.codegenerator.validate;

import com.ccnode.codegenerator.validate.handler.ProjectRequest;
import com.ccnode.codegenerator.validate.request.CheckConnectionRequest;
import com.ccnode.codegenerator.validate.request.CheckValidRequest;
import com.ccnode.codegenerator.validate.request.OfflineActivatioinRequest;
import com.ccnode.codegenerator.validate.request.UnBindRequest;
import com.ccnode.codegenerator.validate.response.CheckConnectionResponse;

import java.lang.reflect.Field;

public class MyValidator {
    public MyValidator() {
    }

    public static void validateTheProject(ProjectRequest request) {
        request.setValid(true);
    }

    public static boolean isValid(CheckValidRequest request) {
        request.setValid(true);
        return true;
    }

    public static void unBind(UnBindRequest request) {
        try {
            Field valid = request.getClass().getDeclaredField("valid");
            valid.setBoolean(request, true);
        } catch (Exception e) {
        }
    }


    public static CheckConnectionResponse checkConnection(CheckConnectionRequest request) {
        return new CheckConnectionResponse(true, (String) null);
    }

    public static boolean offlineActivation(OfflineActivatioinRequest request) {
        request.setValid(true);
        return request.getValid();
    }
}
