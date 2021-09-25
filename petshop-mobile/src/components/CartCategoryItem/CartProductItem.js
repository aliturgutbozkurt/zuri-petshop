import React from 'react';

import {Text, View, Image, StyleSheet, Dimensions} from 'react-native';

const CartCategoryItem = (props) => {
    const {name,url} = props;

    return (
        <View style={styles.root}>
            <Image style={styles.image} source={{uri:url}}/>
            <Text style={styles.titleText}>{name}</Text>
        </View>
    );
};

const styles = StyleSheet.create ({
    root: {
      flexDirection:"column",
        margin: Dimensions.get('window').width * 0.005,
        padding: Dimensions.get('window').width *0.005,
        borderWidth:1,
        borderColor: "#d1d1d1",
        justifyContent: 'center',
        alignItems: 'center',
    },
    image : {
        width: Dimensions.get('window').width / 3.9,
        height: Dimensions.get('window').width / 3.9
    },
    title: {
        fontSize: 15,
        fontWeight: "bold"
    }
})

export default CartCategoryItem;
