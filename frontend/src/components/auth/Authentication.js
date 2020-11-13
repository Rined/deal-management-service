export default class Authentication {

    constructor(payload, jwt) {
        this.jwt = jwt;
        this.userName = payload.sub;
        this.userId = payload.userId;
        this.userRole = payload.role;
        this.userEmail = payload.email;
    }

    get name() {
        return this.userName;
    }

    get id() {
        return this.userId;
    }

    get role() {
        return this.userRole;
    }

    get email() {
        return this.userEmail;
    }

    isConsumer() {
        return this.userRole === 'CONSUMER';
    }

    isProvider() {
        return this.userRole === 'PROVIDER';
    }
}

