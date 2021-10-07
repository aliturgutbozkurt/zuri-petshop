import { combineReducers } from 'redux';
import loginReducer from './login';
import categoryProductReducer from './categoryProduct';

export default combineReducers({
  login: loginReducer,
  categoryProduct: categoryProductReducer
});
