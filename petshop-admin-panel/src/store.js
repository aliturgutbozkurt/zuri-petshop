import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import createSagaMiddleware from 'redux-saga'
import rootReducer from './reducers/rootReducer.js';

export default function configureStore() {
    const sagaMiddleware = createSagaMiddleware();
    const middlewares = [thunk, sagaMiddleware];
    const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose
    return {
        ...createStore(rootReducer, composeEnhancers(
            applyMiddleware(...middlewares)
        )), runSaga: sagaMiddleware.run
    };
};

export const store = configureStore()



