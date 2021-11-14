import client from "./client.js";

const login = (email, password) => client.post("/auth", { email, password });

export default {
  login,
};
