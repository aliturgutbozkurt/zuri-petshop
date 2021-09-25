import React from 'react';

import {Text, View, StyleSheet, ScrollView, Image, Dimensions} from 'react-native';
import CategoryItemList from "../components/CartCategoryItem/CategoryItemList";
import ImageCarousel from "../components/ImageCarusel/ImageCarousel";

const HomeScreen = () => {
    return (
        <ScrollView style={styles.page}>
            <View style={styles.imageView}>
                <Image style={styles.image}
                       source={{uri: "https://firebasestorage.googleapis.com/v0/b/zuri-petshop.appspot.com/o/common-images%2Flogo.png?alt=media&token=0f64d743-3696-4dec-aebe-21e023906e3a"}}/>
            </View>
            <View>
                <ImageCarousel
                    images={["https://www.petaddress.com.tr/wp-content/uploads/2021/01/evcil-hayvnalar-nerede-satilir.jpg", "https://hayalakvaryum.com/wp-content/uploads/2021/04/whiskas-puch-1.png"]}/>
            </View>

            <View>
                <CategoryItemList/>
            </View>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    page: {},
    imageView:{
        flexDirection:"column",
        margin: Dimensions.get('window').width * 0.05,

        justifyContent: 'center',
        alignItems: 'center',
    },
    image: {
        width: Dimensions.get('window').width * 0.8,
        height: Dimensions.get('window').height * 0.15,
        resizeMode: 'contain',
    },
    titleText: {
        fontSize: 40,
        fontWeight: "bold",
        color: "orange"
    }
})

export default HomeScreen;
