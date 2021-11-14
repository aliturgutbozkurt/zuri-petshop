import React, { useState } from "react";
import { NavigationContainer } from "@react-navigation/native";
import AppLoading from 'expo-app-loading';

import navigationTheme from "./app/navigation/navigationTheme.js";
import AppNavigator from "./app/navigation/AppNavigator.js";
import OfflineNotice from "./app/components/OfflineNotice.js";
import AuthNavigator from "./app/navigation/AuthNavigator.js";
import AuthContext from "./app/auth/context.js";
import authStorage from "./app/auth/storage.js";
import { navigationRef } from "./app/navigation/rootNavigation.js";

export default function App() {
  const [user, setUser] = useState();
  const [isReady, setIsReady] = useState(false);

  const restoreUser = async () => {
    const user = await authStorage.getUser();
    if (user) setUser(user);
  };

  if (!isReady)
    return (
      <AppLoading startAsync={restoreUser} onError={()=>{}} onFinish={() => setIsReady(true)} />
    );

  return (
    <AuthContext.Provider value={{ user, setUser }}>
      <OfflineNotice />
      <NavigationContainer ref={navigationRef} theme={navigationTheme}>
        {user ? <AppNavigator /> : <AuthNavigator />}
      </NavigationContainer>
    </AuthContext.Provider>
  );
}
