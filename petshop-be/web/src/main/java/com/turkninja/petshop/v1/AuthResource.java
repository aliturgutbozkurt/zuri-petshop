package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.user.UserLoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin("*")
public class AuthResource {

    @PostMapping(path = "login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void authenticate(@RequestBody @Valid UserLoginRequest userLoginRequest, HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello World!");
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
