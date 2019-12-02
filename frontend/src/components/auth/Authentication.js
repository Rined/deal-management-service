export default class Authentication {

    constructor(payload, jwt) {
        this.jwt = jwt;
        this.userName = payload.sub;
        this.userId = payload.userId;
        this.userRoles = payload.roles;
        this.userEmail = payload.email;
        localStorage.setItem('token', jwt);
    }

    get name() {
        return this.userName;
    }

    get id() {
        return this.userId;
    }

    get roles() {
        return this.userRoles;
    }

    get email() {
        return this.userEmail;
    }

    isConsumer() {
        return this.userRoles.includes('CONSUMER');
    }

    isProvider() {
        return this.userRoles.includes('PROVIDER');
    }

}

