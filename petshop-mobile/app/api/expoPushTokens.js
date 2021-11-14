import client from "./client.js";

const register = (pushToken) =>
  client.post("/expoPushTokens", { token: pushToken });

export default {
  register,
};
