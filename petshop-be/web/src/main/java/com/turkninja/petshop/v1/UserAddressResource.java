package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.user.UserAddressCreateRequest;
import com.turkninja.petshop.api.request.user.UserAddressUpdateRequest;
import com.turkninja.petshop.api.response.user.UserAddressCreateResponse;
import com.turkninja.petshop.api.response.user.UserAddressGetResponse;
import com.turkninja.petshop.api.response.user.UserAddressUpdateResponse;
import com.turkninja.petshop.user.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/addresses")
@RequiredArgsConstructor
public class UserAddressResource {
    private final UserAddressService service;

    @PostMapping
    public ResponseEntity<UserAddressCreateResponse> create(@RequestBody @Valid UserAddressCreateRequest request) {
        UserAddressCreateResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserAddressUpdateResponse> update(@PathVariable(value = "id") Long id,
                                                            @RequestBody @Valid UserAddressUpdateRequest request) {
        UserAddressUpdateResponse response = service.update(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserAddressGetResponse> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressGetResponse> get(@PathVariable(value = "id") Long id) {
        UserAddressGetResponse response = service.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserAddressGetResponse>> list(@RequestParam(name = "userId") Long userId) {
        List<UserAddressGetResponse> response = service.getByUserId(userId);
        return ResponseEntity.ok().body(response);
    }
}

