import client from "./client";

const login = (email, password) => client.post("/v1/auth/login", { email, password });

export default {
  login,
};
