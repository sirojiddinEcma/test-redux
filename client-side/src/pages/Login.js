import React, {Component} from 'react';
import {AvField, AvForm} from 'availity-reactstrap-validation'
import {TOKEN} from "../utils/constants";
import request from "../utils/request";
import api from "../utils/api";

class Login extends Component {
    componentDidMount() {
        if (localStorage.getItem(TOKEN)) {
            this.props.history.push('/cabinet')
        }
    }

    login = (e, v) => {
        request({
            url: api.loginUrl,
            method: 'POST',
            data: v
        }).then(jovob => {
            localStorage.setItem(TOKEN, 'Bearer ' + jovob.data.object)
            this.props.history.push('/cabinet')
        }).catch(err => {
            alert(err.response.data.message)
        })

    }

    render() {
        return (
            <div>
                <h1 className="text-center">Login</h1>
                <AvForm onValidSubmit={this.login}>
                    <AvField
                        label="Telefon raqam"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos telefon raqamini kiriting'}
                        }}
                        name="phoneNumber"/>
                    <AvField
                        label="Parol"
                        validate={{
                            required: {value: true, errorMessage: 'Iltimos parolni kiriting'}
                        }}
                        type="password"
                        name="password"/>
                    <button className={"btn btn-success"}>Kirish
                    </button>
                    <button className={"btn btn-primary"}>Ro'yxatdan o'tish</button>
                </AvForm>
            </div>
        );
    }
}

export default Login;