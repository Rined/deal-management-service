import {TEMPLATE_PROCESS_REQUEST} from "./actions";

const defaultState = {
    disable: false,
    loading: false
};

export const addTemplateReducer = (state = defaultState, action) => {
    switch (action.type) {
        case TEMPLATE_PROCESS_REQUEST:
            return {
                ...state,
                disable: action.payload,
                loading: action.payload
            }
    }
    return state;
};