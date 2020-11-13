export class Validator {
    constructor(validations, setErrorText) {
        this.validations = validations;
        this.setErrorText = setErrorText;
    }

    validate() {
        let isValid = true;
        for (let key in this.validations) {
            if (this.validations.hasOwnProperty(key))
                isValid &= this.validations[key].validate();
        }
        return isValid;
    }

    showError(errorText) {
        this.setErrorText(errorText);
    }

    clear() {
        this.setErrorText('');
    }
}

export class Validation {
    constructor(field, showError, errorText) {
        this.field = field;
        this.showError = showError;
        this.errorText = errorText;
    }

    validate() {
        if (this.field.length === 0) {
            this.showError(this.errorText);
            return false;
        }
        return true;
    }

    clear() {
        this.showError('');
    }
}