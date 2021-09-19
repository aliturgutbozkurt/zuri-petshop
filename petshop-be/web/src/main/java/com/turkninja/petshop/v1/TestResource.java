package com.turkninja.petshop.v1;

import com.turkninja.petshop.constants.ParameterConstants;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tests")
public class TestResource {

    @GetMapping("hello")

    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @GetMapping("exception")
    public void exception(){
        throw new ApplicationException(AppMessage.ALREADY_EXIST,
                AppParameter.get(ParameterConstants.USER_NAME, "test"));
    }

}
