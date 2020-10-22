import React from 'react';
import AddTemplate from "./AddTemplate";
import {connect} from 'react-redux';
import {setProcessRequest} from './../../../../store/template/actions';

function AddTemplateContainer(props) {
    return (
        <AddTemplate
            loading={props.loading}
            disable={props.disable}
            setProcessRequest={props.setProcessRequest}
            auth={props.auth}/>
    );
}

const mapStateToProps = state => {
    return {
        disable: state.template.disable,
        loading: state.template.loading
    };
};

const mapDispatchToProps = {
    setProcessRequest
};

export default connect(mapStateToProps, mapDispatchToProps)(AddTemplateContainer)