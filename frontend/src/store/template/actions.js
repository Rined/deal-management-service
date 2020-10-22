export const TEMPLATE_PROCESS_REQUEST = 'TEMPLATE_PROCESS_REQUEST';

export const setProcessRequest = isProcessRequest => ({
    type: TEMPLATE_PROCESS_REQUEST,
    payload: isProcessRequest
});