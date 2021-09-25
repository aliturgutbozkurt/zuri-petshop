import React from 'react';

import Header, {Text, View, StyleSheet} from 'react-native';
import CategoryItemList from "../components/CartCategoryItem/CategoryItemList";
import ImageCarousel from "../components/ImageCarusel/ImageCarousel";

const HomeScreen = () => {
    return (
        <View style={styles.page}>
            <Text style={styles.titleText}>
                Zuri Petshop
            </Text>
            <ImageCarousel images={["https://www.petaddress.com.tr/wp-content/uploads/2021/01/evcil-hayvnalar-nerede-satilir.jpg","https://hayalakvaryum.com/wp-content/uploads/2021/04/whiskas-puch-1.png"]}/>
            <CategoryItemList/>
        </View>
    );
};

const styles = StyleSheet.create ({
    page : {
    },
    titleText: {
        fontSize: 40,
        fontWeight: "bold",
        color: "orange"
    }
})

export default HomeScreen;
