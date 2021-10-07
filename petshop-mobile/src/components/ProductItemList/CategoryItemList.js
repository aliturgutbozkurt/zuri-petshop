import React, {useEffect} from 'react';

import {StyleSheet, Text, View,FlatList} from 'react-native';
import CartCategoryItem from "../CartCategoryItem/CartProductItem";
import {useDispatch, useSelector} from "react-redux";
import {getCategories} from "../../store/categoryProduct";
import ProductItem from "../ProductItem/ProductItem";

const CategoryItemList = () => {

    const dispatch = useDispatch();
    const {categoryProduct, loading} = useSelector((state) => state.entities)

    useEffect(() => {
        dispatch(getCategories());
        console.log(categoryProduct.loading);
    }, [])

    useEffect(() => {
        console.log("Category Product... : ");
        console.log(categoryProduct);
        console.log(categoryProduct.loading);
    }, [categoryProduct])

    return (
        <View  style={styles.root}>
            <ProductItem/>
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
