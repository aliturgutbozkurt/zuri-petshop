import React from "react";
import {useFormikContext} from "formik";

import TextInput from "../TextInput";
import ErrorMessage from "./ErrorMessage";

function AppFormField({name, width, ...otherProps}) {
    const {
        setFieldTouched,
        setFieldValue,
        errors,
        touched,
        values,
    } = useFormikContext();

    const nameArray = name.includes(".") ? name.split(".") : [];

    return (
        <>
            <TextInput
                onBlur={() => setFieldTouched(name)}
                onChangeText={(text) => setFieldValue(name, text)}
                value={values[name]}
                width={width}
                {...otherProps}
            />
            {nameArray.length == 2 &&
            < ErrorMessage error={errors[nameArray[0]] && errors[nameArray[0]][nameArray[1]]}
                           visible={errors[nameArray[0]] && touched[nameArray[0]][nameArray[1]]}/>
            }
            <ErrorMessage error={errors[name]} visible={touched[name]}/>

        </>
    );
}

export default AppFormField;
