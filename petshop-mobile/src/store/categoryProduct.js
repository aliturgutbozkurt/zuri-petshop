import {createSlice} from '@reduxjs/toolkit';
import {apiCallBegan} from "./apiactions";

const url = '/v1/category';

const slice = createSlice({
    name: 'categoryProduct',
    initialState: {
        categories:[],
        products:[],
        loading: false,
        lastFetch: null,
    },
    reducers: {
        categoryRequested: (categoryProduct, action) => {
            console.log("category requested");
            categoryProduct.loading = true;
        },

        categoryReceived: (categoryProduct, action) => {
            console.log("category received");
            categoryProduct.categories=action.payload.data;
            console.log(action.payload.data);
            categoryProduct.loading = false;
        },

        categoryRequestFailed: (categoryProduct, action) => {
            console.log("category failed");
            categoryProduct.loading = false;
        },
        productRequested: (categoryProduct, action) => {
            console.log("product requested");
            categoryProduct.loading = true;
        },

        productReceived: (categoryProduct, action) => {
            console.log("product received");
            categoryProduct.loading = false;
        },

        productRequestFailed: (categoryProduct, action) => {
            console.log("product failed");
            categoryProduct.loading = false;
        },
    },
});
export const {categoryRequested, categoryReceived, categoryRequestFailed, productRequested, productReceived, productRequestFailed} = slice.actions;


export const getCategories = (parentCategory) => {
    const params = parentCategory ? "?parentCategory="+parentCategory: "";
    return apiCallBegan({
        url: url + "/listByParentId" + params ,
        method: 'get',
        onStart: categoryRequested.type,
        onSuccess: categoryReceived.type,
        onError: categoryRequestFailed.type
    });
};
export default slice.reducer;