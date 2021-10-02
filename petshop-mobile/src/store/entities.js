import { combineReducers } from 'redux';
import loginReducer from './login';

export default combineReducers({
  login: loginReducer
});
