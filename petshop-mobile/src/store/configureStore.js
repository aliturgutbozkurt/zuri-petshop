import { configureStore } from '@reduxjs/toolkit';
import reducer from './reducer';
import logger from './middleware/logger';
import toast from './middleware/toast';
import api from './middleware/api';


export const createStore = () => configureStore({
    reducer,
    middleware: (getDefaultMiddleware) => [...getDefaultMiddleware(), api,logger,toast],
});


