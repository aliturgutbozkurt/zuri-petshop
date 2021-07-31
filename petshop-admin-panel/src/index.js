import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {BrowserRouter as Router} from "react-router-dom";
import { Provider } from "react-redux";
import configureStore from "./store";
import rootSaga from "./sagas";

export const store = configureStore();
store.runSaga(rootSaga);

ReactDOM.render(
    <Provider store={store}>
        <React.StrictMode>

                <App/>

        </React.StrictMode>
    </Provider>,
    document.getElementById('root')
);
