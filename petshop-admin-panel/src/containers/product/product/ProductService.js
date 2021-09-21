import request from "../../../common/request";




export const getProductById = (id) => {
    const productRequest = {
        method: "GET",
        path: "/api/v1/product/"+id ,
    }
    return request(productRequest);
}

export const pageProducts = (page, size) => {
    const categoriesRequest = {
        method: "GET",
        path: "/api/v1/product",
        params: ["page", "size"],
        values: [page, size]
    }
    return request(categoriesRequest);
}

export const createProduct = data => {
    const createProductRequest = {
        method: "POST",
        path: "/api/v1/product",
        data: data,
    }
    return request(createProductRequest);
}

export const updateProduct = data => {
    const updateProductRequest = {
        method: "PUT",
        path: "/api/v1/product",
        data: data,
    }
    return request(updateProductRequest);
}

export const deleteProductById = id => {
    const deleteProductRequest = {
        method: "DELETE",
        path: "/api/v1/product/" + id,
    }
    return request(deleteProductRequest);
}