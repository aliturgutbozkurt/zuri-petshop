import request from "../../../common/request";


export const pageCategoriesByParentId = (parentId, page, size) => {
    const categoriesRequest = {
        method: "GET",
        path: "/api/v1/category/pageByParentId",
        params: ["parentId", "page", "size"],
        values: [parentId, page, size]
    }
    return request(categoriesRequest);
}

export const listCategoriesByParentId = parentId => {
    const categoriesRequest = {
        method: "GET",
        path: "/api/v1/category/listByParentId",
        params: ["parentId"],
        values: [parentId]
    }
    return request(categoriesRequest);
}

export const createProductCategory = data => {
    const createProductCategoryRequest = {
        method: "POST",
        path: "/api/v1/category",
        data: data,
    }
    return request(createProductCategoryRequest);
}
export const updateProductCategory = data => {
    const updateProductRequest = {
        method: "PUT",
        path: "/api/v1/category",
        data: data,
    }
    return request(updateProductRequest);
}

export const deleteCategoryById = id => {
    const deleteCategoryRequest = {
        method: "DELETE",
        path: "/api/v1/category/" + id,
    }
    return request(deleteCategoryRequest);
}

export const getCategoryById = id => {
    const getProductRequest = {
        method: "GET",
        path: "/api/v1/category/" + id,
    }
    return request(getProductRequest);
}

export const createCategoryImage= data => {
    const formData = new FormData();
    formData.append('file', data);

    const createImageRequest = {
        method: "POST",
        path: "/api/v1/file",
        data: formData,
        params: ["firebasePath"],
        values: ["category-images"]
    }
    return request(createImageRequest);
}