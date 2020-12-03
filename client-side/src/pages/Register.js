import React, {Component} from 'react';
import {AvField, AvForm} from "availity-reactstrap-validation";
import request from "../utils/request";
import api from "../utils/api";
import {toast} from "react-toastify";

class Register extends Component {

    register = (e, v) => {
        console.log(v);
        request({
            url: api.registerUrl,
            method: 'POST',
            data: v
        }).then(res => {
            toast.success(res.data.message);
            // localStorage.setItem(TOKEN, 'Bearer ' + res.data.object);
            // this.props.history.push('/cabinet')
        })
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Login</h1>
                <AvForm onValidSubmit={this.register}>
                    <AvField
                        label="Ism"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos ismingizni kiriting'}
                        }}
                        name="firstName"/>
                    <AvField
                        label="Familya"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos ismingizni kiriting'}
                        }}
                        name="lastName"/>
                    <AvField
                        label="Telefon raqam"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos telefon raqamini kiriting'}
                        }}
                        name="phoneNumber"/>
                    <AvField
                        label="Email raqam"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos telefon raqamini kiriting'}
                        }}
                        name="email"/>
                    <AvField
                        label="Kompaniya"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos ismingizni kiriting'}
                        }}
                        name="companyName"/>
                    <AvField
                        label="Manzil"
                        name="address"/>
                    <AvField
                        label="Parol"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos parolni kiriting'}
                        }}
                        type="password"
                        name="password"/>
                    <AvField
                        label="Parol takrori"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos parolni kiriting'}
                        }}
                        type="password"
                        name="prePassword"/>
                    <button className={"btn btn-success"}>Ro'yxatdan o'tish
                    </button>
                    <button className={"btn btn-primary"}>Menda account bor</button>
                </AvForm>
            </div>
        );
    }
}

export default Register;