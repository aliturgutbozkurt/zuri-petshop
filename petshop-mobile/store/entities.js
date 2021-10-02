import { combineReducers } from 'redux';
import userReducer from './users';

export default combineReducers({
  users: userReducer
});
