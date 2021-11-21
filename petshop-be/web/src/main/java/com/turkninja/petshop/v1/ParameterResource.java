package com.turkninja.petshop.v1;


import com.turkninja.petshop.api.request.parameter.UpdateParameterRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.parameter.ParameterResponse;
import com.turkninja.petshop.parameter.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/parameter")
public class ParameterResource {

    private final ParameterService parameterService;
    public ParameterResource(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping("create-parameter")
    public ResponseEntity<ParameterResponse> createParameter(@RequestParam String key, @RequestParam String value){
        ParameterResponse resp = parameterService.createParameter(key,value);
        return new ResponseEntity(resp, HttpStatus.CREATED);
    }

    @GetMapping("get-all-parameters")
    public ResponseEntity<List<ParameterResponse>> getAllParameters(){
        List<ParameterResponse> parameters = parameterService.getAllParameters();
        return new ResponseEntity(parameters,HttpStatus.OK);
    }
    @PutMapping("update-parameter")
    public ResponseEntity<ParameterResponse> updateParameter(@RequestBody UpdateParameterRequest updateParameterRequest){
        ParameterResponse response = parameterService.updateParameter(updateParameterRequest);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("get-parameter-by-id")
    public ResponseEntity<ParameterResponse> getParameterById(@RequestParam Long parameterId){
        return new ResponseEntity(parameterService.getParameterById(parameterId),HttpStatus.OK);
    }




}
