import React from 'react';

import {StyleSheet, Text, View,FlatList} from 'react-native';
import CartCategoryItem from "./CartProductItem";

const CategoryItemList = () => {
    return (
        <View  style={styles.root}>
            <CartCategoryItem name={"Mamalar"} url={"https://hayalakvaryum.com/wp-content/uploads/2021/04/whiskas-puch-1.png"}/>
            <CartCategoryItem name={"Aksesuarlar"} url={"https://st3.myideasoft.com/idea/ff/16/myassets/products/822/ferplast-venere-large-seramik-mama-kabi.jpg?revision=1621591384"}/>
            <CartCategoryItem name={"Oyuncaklar"} url={"https://st3.myideasoft.com/idea/ff/16/myassets/products/596/gigwi-plush-friendz-suda-yuzen-sesli-kopek-oyuncagi-mavi.JPG?revision=1591348759"}/>
            <CartCategoryItem name={"Kafesler"} url={"https://productimages.hepsiburada.net/s/2/550/9561714982962.jpg/format:webp"}/>
            <CartCategoryItem name={"Evcil Hayvanlar"} url={"https://www.petaddress.com.tr/wp-content/uploads/2021/01/evcil-hayvnalar-nerede-satilir.jpg"}/>
            <CartCategoryItem name={"Akvaryum"} url={"https://www.biolifeurunleri.com/Uploads/UrunResimleri/buyuk/biolife-floryum-ozel-imalat-konik-akvary-5ced.jpg"}/>
        </View >
    );
};

const styles = StyleSheet.create ({
    root: {
        flexDirection:"row",
        justifyContent: "space-around",
        flexWrap: "wrap",
    }
})

export default CategoryItemList;
