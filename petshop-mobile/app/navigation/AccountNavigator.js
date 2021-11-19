import React from "react";
import {createStackNavigator} from "@react-navigation/stack";
import AccountScreen from "../screens/AccountScreen";
import MessagesScreen from "../screens/MessagesScreen";
import {MaterialCommunityIcons} from "@expo/vector-icons";

const Stack = createStackNavigator();

const AccountNavigator = () => (
    <Stack.Navigator>
        <Stack.Screen name="Account"
                      component={AccountScreen}
                      options={{headerShown:false}}/>
        <Stack.Screen name="Messages" component={MessagesScreen}/>
    </Stack.Navigator>
);

export default AccountNavigator;
