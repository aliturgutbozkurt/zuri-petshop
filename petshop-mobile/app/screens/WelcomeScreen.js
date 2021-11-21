import React from "react";
import {ImageBackground, StyleSheet, View, Image, Text} from "react-native";

import Button from "../components/Button";
import routes from "../navigation/routes";

function WelcomeScreen({navigation}) {
    return (
        <View
            style={styles.background}
        >
            <View style={styles.logoContainer}>
                <Image style={styles.logo} source={require("../assets/logo.png")}/>
                <Text style={styles.tagline}>Evcil hayvanlarınız için aradığınız herşey burada.</Text>
            </View>
            <View style={styles.buttonsContainer}>
                <Button
                    title="Giriş"
                    onPress={() => navigation.navigate(routes.LOGIN)}
                />
                <Button
                    title="Kayıt Ol"
                    color="secondary"
                    onPress={() => navigation.navigate(routes.REGISTER)}
                />
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    background: {
        flex: 1,
        justifyContent: "flex-end",
        alignItems: "center",
    },
    buttonsContainer: {
        padding: 20,
        width: "100%",
    },
    logo: {
        width: 300,
        height: 150
    },
    logoContainer: {
        position: "absolute",
        top: 170,
        padding: 20,
        alignItems: "center",
        justifyContent: "center"
    },
    tagline: {
        fontSize: 18,
        fontWeight: "600",
        paddingVertical: 20,
    },
});

export default WelcomeScreen;
