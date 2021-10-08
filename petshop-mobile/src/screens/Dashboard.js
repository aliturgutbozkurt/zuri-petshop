import React, {useEffect} from 'react';

import {Dimensions, Image, ScrollView, StyleSheet, View} from 'react-native';
import CategoryItemList from "../components/CategoryItemList/CategoryItemList";
import ImageCarousel from "../components/ImageCarusel/ImageCarousel";
import Button from "../components/Button";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "../store/login";
import {getCategories} from "../store/category";
import ProductItemList from "../components/ProductItemList/ProductItemList";

const Dashboard = ({navigation}) => {
    const dispatch = useDispatch();
    const {login} = useSelector((state) => state.entities)

    const logoutUser = () => {
        dispatch(logout({data: {token: "", refreshToken: ""}}));
    }

    useEffect(() => {
        if (!login.loggedIn) {
            navigation.reset({
                index: 0,
                routes: [{name: 'StartScreen'}],
            })
        }
    }, [login.loggedIn])

    useEffect(() => {
        dispatch(getCategories());
    }, [])

    return (
        <ScrollView style={styles.page}>
            <View>
                <ImageCarousel
                    images={["https://www.petaddress.com.tr/wp-content/uploads/2021/01/evcil-hayvnalar-nerede-satilir.jpg", "https://hayalakvaryum.com/wp-content/uploads/2021/04/whiskas-puch-1.png"]}/>
            </View>

            <View>
                <CategoryItemList/>
                <ProductItemList/>
            </View>
            <Button
                mode="outlined"
                onPress={() =>
                    logoutUser()
                }
            >
                Logout
            </Button>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    page: {},
    imageView: {
        flexDirection: "column",
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

export default Dashboard;
