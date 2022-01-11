import { AddressModel } from "./address-model";

export class UserModel {
    constructor(
        public id: number,
        public name: string,
        public username: string,
        public email: string,
        public password: string,
        public number: number,
        public gender: string,
        public address: AddressModel,
        public role: string
    ){}
}
