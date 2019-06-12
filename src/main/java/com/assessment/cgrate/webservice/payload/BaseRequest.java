package com.assessment.cgrate.webservice.payload;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public abstract class BaseRequest implements Serializable {

    private static final transient String NOT_NULL_MSG_PATTERN = "property [%s] cannot be null";
    private static final transient String NOT_NULL_OR_EMPTY_ERROR_MSG_PATTERN = "property [%s] cannot be null or empty";
    private static final String DEFAULT_PROPERTY_NAME = "unknown";

    @Getter
    private transient String errorMessage;

    public boolean isValid() {
        if(errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = performValidation();
        }
        return errorMessage == null || errorMessage.isEmpty();
    }

    protected abstract String performValidation();

    protected String getNotNullMessage(String property) {
        return String.format(NOT_NULL_MSG_PATTERN, StringUtils.isEmpty(property) ? DEFAULT_PROPERTY_NAME : property);
    }

    protected String getNotNullOrEmptyErrorMessage(String property) {
        return String.format(NOT_NULL_OR_EMPTY_ERROR_MSG_PATTERN, StringUtils.isEmpty(property) ? DEFAULT_PROPERTY_NAME : property);
    }
}
