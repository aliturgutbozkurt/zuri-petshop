import { useEffect } from "react";
import * as Notifications from "expo-notifications";
import * as Permissions from "expo-permissions";

import expoPushTokensApi from "../api/expoPushTokens.js";

const useNotifications = (notificationListener) => {
  useEffect(() => {
    registerForPushNotifications().then(x=>{
      if (notificationListener) Notifications.addNotificationReceivedListener(notificationListener);
    })
  }, []);

  const registerForPushNotifications = async () => {
    try {
      const permission = await Permissions.askAsync(Permissions.NOTIFICATIONS);
      if (!permission.granted) return;

      const token = await Notifications.getExpoPushTokenAsync();
      await expoPushTokensApi.register(token);
    } catch (error) {
      console.log("Error getting a push token", error);
    }
  };
};

export default useNotifications;
