import request from "../../common/request.js";

/**
 * Retrieves paged list of users.
 * @param page Page number.
 * @param size Page size.
 * @returns {Promise<* | void>} Http status code returned from the server.
 */
export const pageUsers = (page, size) => {
    let options = {
        method: "GET",
        path: "/api/v1/users",
        params: ["page", "size"],
        values: [page, size],
    }

    return request(options);
}

/**
 * Retrieves user data from API.
 * @param userId Id of the user.
 * @returns {Promise<* | void>} User data as JSON.
 */
export const getUserById = (userId) => {
    let options = {
        method: "GET",
        path: "/api/v1/users/" + userId,
    }

    return request(options);
}

/**
 * Requests a user status change from API.
 * @param id Id of the user.
 * @returns {Promise<* | void>} Http status code returned from API.
 */
export const deleteUserById = (id) => {
    let options = {
        method: "DELETE",
        path: "/api/v1/users/" + id,
    }

    return request(options)
}

/**
 * Sends a user image addition request to API.
 * @param data Image data.
 * @returns {Promise<* | void>} Http status code returned from API.
 */
export const addUserImage= data => {
    const formData = new FormData();
    formData.append('file', data);

    const addUserImageRequest = {
        method: "POST",
        path: "/api/v1/file",
        data: formData,
        params: ["firebasePath"],
        values: ["product-images"]
    }
    return request(addUserImageRequest);
}