import React from "react";
import {View, StyleSheet, TouchableOpacity} from "react-native";
import {AntDesign} from "@expo/vector-icons";

import colors from "../config/colors";

function NewListingButton({onPress}) {
    return (
        <TouchableOpacity onPress={onPress}>
            <View style={styles.container}>
                <AntDesign name="shoppingcart" size={40} color={colors.white} />
            </View>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
        backgroundColor: colors.primary,
        borderColor: colors.white,
        borderRadius: 40,
        borderWidth: 10,
        bottom: 20,
        height: 80,
        justifyContent: "center",
        width: 80,
    },
});

export default NewListingButton;
