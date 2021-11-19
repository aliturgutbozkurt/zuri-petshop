import React, {useState} from "react";
import {StyleSheet} from "react-native";
import * as Yup from "yup";

import Screen from "../components/Screen";
import usersApi from "../api/users";
import authApi from "../api/auth";
import useAuth from "../auth/useAuth";
import {
    ErrorMessage,
    Form,
    FormField,
    SubmitButton,
} from "../components/forms";
import useApi from "../hooks/useApi";
import ActivityIndicator from "../components/ActivityIndicator";
import {useFormikContext} from "formik";

const validationSchema = Yup.object().shape({
    fullName: Yup.object().shape({
            name: Yup.string().required("Adı alanı gerekli"),
            lastName: Yup.string().required("Soyadı alanı gerekli"),
        }).required().label("Full Name"),
    email: Yup.string().required("Email alanı gerekli").email("Geçerli bir email giriniz").label("Email"),
    password: Yup.string().required("Şifre alanı gerekli").min(6,"Şifre en az 6 hane olmalıdır").label("Password"),
});

function RegisterScreen() {
    const registerApi = useApi(usersApi.register);
    const loginApi = useApi(authApi.login);
    const auth = useAuth();
    const [error, setError] = useState();

    const handleSubmit = async (userInfo) => {
        const result = await registerApi.request(userInfo);

        if (!result.ok) {
            if (result.data) setError(result.data.error);
            else {
                setError("Kayıt sırasında hata oluştu.");
                console.log(result);
            }
            return;
        }

        const loginResult = await loginApi.request(
            userInfo.email,
            userInfo.password
        );
        auth.logIn(loginResult);
    };

    return (
        <>
            <ActivityIndicator visible={registerApi.loading || loginApi.loading}/>
            <Screen style={styles.container}>
                <Form
                    initialValues={{fullName: {name: "", lastName: ""}, email: "", password: ""}}
                    onSubmit={handleSubmit}
                    validationSchema={validationSchema}
                >
                    <ErrorMessage error={error} visible={error}/>
                    <FormField
                        required
                        autoCorrect={false}
                        icon="account"
                        name="fullName.name"
                        placeholder="Ad"
                    />
                    <FormField
                        required
                        autoCorrect={false}
                        icon="account"
                        name="fullName.lastName"
                        placeholder="Soyad"
                    />
                    <FormField
                        required
                        autoCapitalize="none"
                        autoCorrect={false}
                        icon="email"
                        keyboardType="email-address"
                        name="email"
                        placeholder="Email"
                        textContentType="emailAddress"
                    />

                    <FormField
                        required
                        autoCapitalize="none"
                        autoCorrect={false}
                        icon="lock"
                        name="password"
                        placeholder="Şifre"
                        secureTextEntry
                        textContentType="password"
                    />
                    <SubmitButton title="Kayıt Ol"/>
                </Form>
            </Screen>
        </>
    );
}

const styles = StyleSheet.create({
    container: {
        padding: 10,
    },
});

export default RegisterScreen;
