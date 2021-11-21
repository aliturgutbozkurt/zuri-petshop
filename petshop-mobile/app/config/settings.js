import Constants from "expo-constants";

const settings = {
  dev: {
    apiUrl: "http://172.24.0.1:8081/api",
  },
  staging: {
    apiUrl: "http://172.24.0.1:8081/api",
  },
  prod: {
    apiUrl: "http://172.24.0.1:8081/api",
  },
};

const getCurrentSettings = () => {
  if (__DEV__) return settings.dev;
  if (Constants.manifest.releaseChannel === "staging") return settings.staging;
  return settings.prod;
};

export default getCurrentSettings();
