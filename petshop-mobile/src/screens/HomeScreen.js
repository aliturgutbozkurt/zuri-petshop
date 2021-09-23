import React from 'react';

import Header, {Text, View, StyleSheet} from 'react-native';

const HomeScreen = () => {
    return (
        <View style={styles.page}>
            <Text style={styles.titleText}>
                Zuri Petshop
            </Text>
        </View>
    );
};

const styles = StyleSheet.create ({
    page : {
    },
    titleText: {
        fontSize: 40,
        fontWeight: "bold"
    }
})

export default HomeScreen;
