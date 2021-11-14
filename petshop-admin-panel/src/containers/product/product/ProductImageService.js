import request from "../../../common/request.js";

export const createProductImage= data => {
    const formData = new FormData();
    formData.append('file', data);

    const createImageRequest = {
        method: "POST",
        path: "/api/v1/file",
        data: formData,
        params: ["firebasePath"],
        values: ["product-images"]
    }
    return request(createImageRequest);
}