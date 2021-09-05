package com.turkninja.petshop.validation.util;

import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.ApplicationException;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;

/**
 * @author ali turgut bozkurt
 * Created at 9/5/2021
 */

public class ValidationUtil {

    public static boolean isEmptyOrNull(String str) {
        return Objects.isNull(str) || str.equals("");
    }


    public static boolean isNullOrNegative(Long number) {
        return Objects.isNull(number) || number < 0;
    }

}
