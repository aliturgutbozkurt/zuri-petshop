import { combineReducers } from 'redux';
import loginReducer from './login';
import categoryReducer from './category';
import productReducer from './products';

export default combineReducers({
  login: loginReducer,
  category: categoryReducer,
  product:productReducer
});
