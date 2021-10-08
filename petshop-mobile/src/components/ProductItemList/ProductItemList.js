import React, {useEffect} from 'react';

import {StyleSheet, Text, View,FlatList} from 'react-native';
import CartCategoryItem from "../CartCategoryItem/CartProductItem";
import {useDispatch, useSelector} from "react-redux";
import {getCategories} from "../../store/category";
import ProductItem from "../ProductItem/ProductItem";

const ProductItemList = () => {

    const dispatch = useDispatch();
    const {product, loading} = useSelector((state) => state.entities)

    useEffect(() => {
    }, [])

    useEffect(() => {
        console.log("Category Product... : ");
        console.log(product);
        console.log(product.productLoading);
    }, [product])

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

export default ProductItemList;
