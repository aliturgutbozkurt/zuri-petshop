import React from "react";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {MaterialCommunityIcons} from "@expo/vector-icons";

import AccountNavigator from "./AccountNavigator";
import FeedNavigator from "./FeedNavigator";
import ListingEditScreen from "../screens/ListingEditScreen";
import NewListingButton from "./NewListingButton";
import routes from "./routes";
import useNotifications from "../hooks/useNotifications";

const Tab = createBottomTabNavigator();

const AppNavigator = () => {
    useNotifications();

    return (
        <Tab.Navigator>
            <Tab.Screen
                name="Ürünler"
                component={FeedNavigator}
                options={{
                    tabBarIcon: ({color, size}) => (
                        <MaterialCommunityIcons name="home" color={color} size={size}/>
                    ),
                    headerShown:false,
                }}
            />
            <Tab.Screen
                name="ListingEdit"
                component={ListingEditScreen}
                options={({navigation}) => ({
                    tabBarButton: () => (
                        <NewListingButton
                            onPress={() => navigation.navigate(routes.LISTING_EDIT)}
                        />
                    ),
                    tabBarIcon: ({color, size}) => (
                        <MaterialCommunityIcons
                            name="plus-circle"
                            color={color}
                            size={size}
                        />
                    ),
                })}
            />
            <Tab.Screen
                name="Hesabım"
                component={AccountNavigator}
                options={{
                    title: "Hesabım",
                    tabBarIcon: ({color, size}) => (
                        <MaterialCommunityIcons name="account" color={color} size={size}/>
                    ),
                }}
            />
        </Tab.Navigator>
    );
};

export default AppNavigator;
