import {createSlice} from '@reduxjs/toolkit';
import {apiCallBegan} from "./apiactions";

const url = '/v1/product';

const slice = createSlice({
    name: 'product',
    initialState: {
        products: [],
        productLoading: false,
        lastFetch: null,
    },
    reducers: {
        productRequested: (product, action) => {
            console.log("product requested");
            product.loading = true;
        },

        productReceived: (product, action) => {
            console.log("product received");
            product.products = action.payload.data;
            console.log(action.payload.data);
            product.loading = false;
        },

        productRequestFailed: (product, action) => {
            console.log("product failed");
            product.loading = false;
        },
    },
});
export const {productRequested, productReceived, productRequestFailed} = slice.actions;


export const getProducts = (data) => {
    const params = "?parentCategory=" + data.categoryId + "&page=" + data.page + "&size=" + data.size;
    return apiCallBegan({
        url: url + "/listByParentId" + params,
        method: 'get',
        onStart: productRequested.type,
        onSuccess: productReceived.type,
        onError: productRequestFailed.type
    });
};
export default slice.reducer;