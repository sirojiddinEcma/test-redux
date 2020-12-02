import React, {Component} from 'react';
import {AvField, AvForm} from 'availity-reactstrap-validation'
import axios from 'axios';
import {TOKEN} from "../utils/constants";

class Login extends Component {

    login = (e, v) => {
        console.log(v);
        axios.post(
            'http://localhost/api/auth/login',
            v
        ).then(jovob => {
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