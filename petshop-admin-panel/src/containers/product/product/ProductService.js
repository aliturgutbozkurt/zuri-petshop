import request from "../../../common/request";

export const createProduct = data => {
    const createProductRequest = {
        method: "POST",
        path: "/api/v1/product",
        data: data,
    }
    return request(createProductRequest);
}