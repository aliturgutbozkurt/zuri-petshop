package com.turkninja.petshop.appException;

import com.turkninja.petshop.exception.AppMessageDescription;
import com.turkninja.petshop.exception.ApplicationException;

public interface AppExceptionService {

    AppMessageDescription generateExceptionMessageByCode(ApplicationException exception);
}
