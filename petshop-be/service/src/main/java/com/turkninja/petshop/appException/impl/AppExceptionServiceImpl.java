package com.turkninja.petshop.appException.impl;

import com.turkninja.petshop.AppExceptionRepository;
import com.turkninja.petshop.appException.AppExceptionService;
import com.turkninja.petshop.entity.applicationexception.AppExceptionEntity;
import com.turkninja.petshop.exception.AppMessageDescription;
import com.turkninja.petshop.exception.ApplicationException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@Service
public class AppExceptionServiceImpl implements AppExceptionService {

    private final AppExceptionRepository appExceptionRepository;

    public AppExceptionServiceImpl(AppExceptionRepository appExceptionRepository) {
        this.appExceptionRepository = appExceptionRepository;
    }

    @Cacheable(value = "cacheException")
    @Override
    public AppMessageDescription generateExceptionMessageByCode(ApplicationException exception) {
        Optional<AppExceptionEntity> appExceptionOptional =
                appExceptionRepository.findByCode(exception.getCode());
        AppMessageDescription appMessageDescription = new AppMessageDescription("", "");
        if (appExceptionOptional.isPresent()) {
            AppExceptionEntity appExceptionEntity = appExceptionOptional.get();
            String message = appExceptionEntity.getMessage();
            Map<String, Serializable> params = exception.getParams();
            if (params != null) {
                for (Map.Entry<String, Serializable> entry : params.entrySet()) {
                    if (message != null) {
                        message = message.replace("#{" + entry.getKey() + "}", entry.getValue().toString());
                    }
                }
            }
            appMessageDescription.setTr(message);
        }
        return appMessageDescription;
    }
}
