package com.turkninja.petshop.exception;

import com.turkninja.petshop.util.ToStringBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.turkninja.petshop.util.TypeUtils.isNotEmpty;


public class ApplicationException extends RuntimeException implements Supplier<ApplicationException> {
    private static final long serialVersionUID = 1L;

    private final long timestamp;
    private final String code;
    private final HttpStatus statusCode;
    private final Map<String, Serializable> params;


    public ApplicationException(AppMessage message, AppParameter... params) {
        this(System.currentTimeMillis(), message, params);
    }

    private ApplicationException(long timestamp, AppMessage message, AppParameter... params) {
        this(timestamp, message, toMap(params));
    }

    private ApplicationException(long timestamp, AppMessage message, Map<String, Serializable> params) {
        super(createMessage(timestamp, message, params));
        this.code = message.getCode();
        this.statusCode = message.getStatusCode();
        this.timestamp = timestamp;
        this.params = params;
    }

    public Map<String, Serializable> getParams() {
        return params;
    }

    public Object getParam(String name) {
        return params != null ? params.get(name) : null;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    private static Map<String, Serializable> toMap(AppParameter... params) {
        HashMap<String, Serializable> map;
        if (isNotEmpty(params)) {
            map = new HashMap<>();
            for (AppParameter param : params) {
                map.put(param.getName(), param.getValue());
            }
        } else {
            map = null;
        }
        return map;
    }

    private static String createMessage(long timestamp, AppMessage message, Map<String, Serializable> params) {
        ToStringBuilder sb = new ToStringBuilder();

        if (message != null) {
            sb.add("code", message.getCode());
            sb.add("statusCode", message.getStatusCode());
        }

        if (isNotEmpty(params)) {
            sb.add("params", Arrays.toString(params.entrySet().toArray()));
        }

        if (timestamp > 0) {
            sb.add("timestamp", timestamp);
        }

        return sb.toString();
    }

    @Override
    public ApplicationException get() {
        return this;
    }

}
