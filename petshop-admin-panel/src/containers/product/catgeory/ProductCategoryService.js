import request from "../../../common/request";


export const listCategoriesByParentIdNull = () =>{
    const categoriesRequest = {
        method: "GET",
        path: "/api/v1/product/listCategories",
    }
    return request(categoriesRequest);
}

export const listCategoriesByParentId = parentId =>{
    const categoriesRequest = {
        method: "GET",
        path: "/api/v1/product/listCategories",
        params: ["parentId"],
        values: [parentId]
    }
    return request(categoriesRequest);
}

export const createProductCategory = data =>{
    const createProductRequest = {
        method: "POST",
        path: "/api/v1/product/category",
        data: data,
    }
    return request(createProductRequest);
}