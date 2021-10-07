import React, {useEffect} from 'react';

import {StyleSheet, Text, View, FlatList} from 'react-native';
import CartCategoryItem from "../CartCategoryItem/CartProductItem";
import {useDispatch, useSelector} from "react-redux";
import {getCategories} from "../../store/categoryProduct";

const CategoryItemList = () => {

    const {categoryProduct, loading} = useSelector((state) => state.entities)

    return (
        <View style={styles.root}>
            {categoryProduct.categories.map((category,index) => (
                <CartCategoryItem key={index} name={category.name} url={category.photoUrl}/>
            ))}

        </View>
    );
};

const styles = StyleSheet.create({
    root: {
        flexDirection: "row",
        justifyContent: "space-around",
        flexWrap: "wrap",
    }
})

export default CategoryItemList;
