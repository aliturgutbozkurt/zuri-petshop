import request from "../../common/request";

export const getParameterById = (parameterId)=>{
    const requestData= {
        method: "GET",
        path: "/api/v1/parameter/get-parameter-by-id",
        params: ["parameterId"],
        values: [parameterId]
    }
    return request(requestData)
}

export const createParameter = (key,value) =>{
    const requestData= {
        method: "GET",
        path: "/api/v1/parameter/create-parameter",
        params: ["key","value"],
        values: [key,value]
    }
    return request(requestData)
}

export const updateParameter = (id,key,value,type) =>{
    var data = {
        id:id,
        key:key,
        value:value,
        type: type
    }
    const requestData= {
        method: "PUT",
        path: "/api/v1/parameter/update-parameter",
        data: data,
    }
    return request(requestData)
}

export const getAllParameters = (page,size) =>{

    const requestData= {
        method: "GET",
        path: "/api/v1/parameter/get-all-parameters",
        params: ["page","size"],
        values: [page,size]
    }
    return request(requestData)
}

export const updateImage= data => {
    const formData = new FormData();
    formData.append('file', data);

    const updateImageRequest = {
        method: "POST",
        path: "/api/v1/file",
        data: formData,
        params: ["firebasePath"],
        values: ["category-images"]
    }
    return request(updateImageRequest);
}