import client from "./client";

const register = (userInfo) => client.post("/v1/users", userInfo);

export default { register };
