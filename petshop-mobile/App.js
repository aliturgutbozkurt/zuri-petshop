import {StatusBar} from 'expo-status-bar';
import React from 'react';
import {StyleSheet, Dimensions} from 'react-native';
import {Provider as PaperProvider} from 'react-native-paper'
import {NavigationContainer} from '@react-navigation/native'
import {createStackNavigator} from '@react-navigation/stack'
import {theme} from './src/core/theme'
import {Provider} from 'react-redux'

import Icon from 'react-native-vector-icons/FontAwesome';

const myIcon = <Icon name="rocket" size={30} color="#900"/>;
import {
    StartScreen,
    LoginScreen,
    RegisterScreen,
    ResetPasswordScreen,
    Dashboard,
} from './src/screens'


import {createStore} from "./src/store/configureStore";

const store = createStore();


export default function App() {


    const styles = StyleSheet.create({
        backgroundStyle: {
            margin: Dimensions.get('window').width * 0.05,
        }
    })

    const Stack = createStackNavigator()

    return (
        <Provider store={store}>

            <PaperProvider theme={theme}>
                <StatusBar/>
                <NavigationContainer>
                    <Stack.Navigator
                        initialRouteName="StartScreen"
                        screenOptions={{
                            headerShown: false,
                        }}
                    >
                        <Stack.Screen name="StartScreen" component={StartScreen}/>
                        <Stack.Screen name="LoginScreen" component={LoginScreen}/>
                        <Stack.Screen name="RegisterScreen" component={RegisterScreen}/>
                        <Stack.Screen name="Dashboard" component={Dashboard}/>
                        <Stack.Screen
                            name="ResetPasswordScreen"
                            component={ResetPasswordScreen}
                        />
                    </Stack.Navigator>
                </NavigationContainer>
            </PaperProvider>
        </Provider>
    );


}
