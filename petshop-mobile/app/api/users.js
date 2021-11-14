import client from "./client.js";

const register = (userInfo) => client.post("/users", userInfo);

export default { register };
