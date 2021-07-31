package com.turkninja.petshop.v1.handler;

import com.turkninja.petshop.api.response.exception.ApiExceptionResponse;
import com.turkninja.petshop.appException.AppExceptionService;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppMessageDescription;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;

@ControllerAdvice
@RestController
@Slf4j
public class ApplicationExceptionHandler {

    private final AppExceptionService appExceptionService;
    private Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    public ApplicationExceptionHandler(AppExceptionService appExceptionService) {
        this.appExceptionService = appExceptionService;
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiExceptionResponse> handleApplicationException(ApplicationException exception) {
        logger.error("Application Exception : " + exception.getClass() + ": " + exception.getMessage(), exception);
        ApiExceptionResponse response = new ApiExceptionResponse();
        AppMessageDescription appMessageDescription =
                appExceptionService.generateExceptionMessageByCode(exception);
        response.setDescription(appMessageDescription);
        response.setCode(exception.getCode());
        response.setTimestamp(exception.getTimestamp());
        response.setStatusCode(exception.getStatusCode());
        HttpStatus httpStatus = exception.getStatusCode();
        return new ResponseEntity<>(response, httpStatus);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ApiExceptionResponse response = new ApiExceptionResponse();
        AppMessageDescription appMessageDescription = new AppMessageDescription("","");
        StringJoiner joiner = new StringJoiner(", ");
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            joiner.add(fieldError.getDefaultMessage());
        }
        appMessageDescription.setTr(joiner.toString());
        response.setDescription(appMessageDescription);
        response.setTimestamp(System.currentTimeMillis());
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleException(Exception exception) {
        logger.error("Exception : " + exception.getClass() + ": " + exception.getMessage(), exception);
        ApplicationException applicationException = new ApplicationException(AppMessage.INTERNAL_SYSTEM_ERROR,
                AppParameter.get("message", exception.getMessage() != null ? exception.getMessage() : exception.toString()));
        ApiExceptionResponse response = new ApiExceptionResponse();
        AppMessageDescription appMessageDescription =
                appExceptionService.generateExceptionMessageByCode(applicationException);
        response.setDescription(appMessageDescription);
        response.setCode(applicationException.getCode());
        response.setTimestamp(applicationException.getTimestamp());
        response.setStatusCode(applicationException.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
