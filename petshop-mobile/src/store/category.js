import {createSlice} from '@reduxjs/toolkit';
import {apiCallBegan} from "./apiactions";

const url = '/v1/category';

const slice = createSlice({
    name: 'category',
    initialState: {
        categories: [],
        categoryLoading: false,
        lastFetch: null,
    },
    reducers: {
        categoryRequested: (category, action) => {
            console.log("category requested");
            category.loading = true;
        },

        categoryReceived: (category, action) => {
            console.log("category received");
            category.categories = action.payload.data;
            console.log(action.payload.data);
            category.loading = false;
        },

        categoryRequestFailed: (category, action) => {
            console.log("category failed");
            category.loading = false;
        },
    },
});
export const {categoryRequested, categoryReceived, categoryRequestFailed} = slice.actions;


export const getCategories = (parentCategory) => {
    const params = parentCategory ? "?parentCategory=" + parentCategory : "";
    return apiCallBegan({
        url: url + "/listByParentId" + params,
        method: 'get',
        onStart: categoryRequested.type,
        onSuccess: categoryReceived.type,
        onError: categoryRequestFailed.type
    });
};
export default slice.reducer;