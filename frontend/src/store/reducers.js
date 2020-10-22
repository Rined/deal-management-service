import {combineReducers} from "redux";
import {addTemplateReducer} from "./template/reducers";

export default combineReducers({
   template: addTemplateReducer
});