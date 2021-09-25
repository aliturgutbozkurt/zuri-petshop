import {StatusBar} from 'expo-status-bar';
import React from 'react';
import {View, StyleSheet, Dimensions} from 'react-native';

import Icon from 'react-native-vector-icons/FontAwesome';
import HomeScreen from "./src/screens/HomeScreen";
const myIcon = <Icon name="rocket" size={30} color="#900"/>;

export default function App() {


    const styles = StyleSheet.create({
        backgroundStyle : {
            margin: Dimensions.get('window').width * 0.05,
        }
    })

    return (
        <View style={styles.backgroundStyle}>
            <StatusBar style="auto"/>
            <HomeScreen/>
        </View>
    );



}
