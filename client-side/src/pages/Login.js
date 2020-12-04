import React, {Component} from 'react';
import {AvField, AvForm} from 'availity-reactstrap-validation'
import {TOKEN} from "../utils/constants";
import request from "../utils/request";
import api from "../utils/api";
import {firebaseAuth} from "../utils/firebase";
import firebase from 'firebase/app';

class Login extends Component {

    state = {
        reCaptcha: '',
        smsSent: false,
        data: '',
        confirmationResult: ''
    }

    componentDidMount() {
        if (localStorage.getItem(TOKEN)) {
            this.props.history.push('/cabinet')
        } else {
            let cont = document.getElementById('reCaptcha');
            this.recaptchaVerifier = new firebase.auth.RecaptchaVerifier(cont, {
                'size': 'invisible',
                'callback': (res) => {
                },
                'expired-callback': () => {
                },
                'error-callback': () => {
                    alert("Xatolik!!! Iltimos birozdan keyin urunib ko'ring")
                }
            });
            this.recaptchaVerifier.render();
            this.setState({reCaptcha: this.recaptchaVerifier})
        }

    }

    login = (v) => {
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
    routeToRegister = () => {
        this.props.history.push('/register')
    }

    checkPasswordAndLogin = (e, v) => {
        request({
            url: api.checkPasswordAndLoginUrl,
            data: v,
            method: 'POST'
        }).then(res => {
            this.sendFirebase(v.phoneNumber);
            this.setState({data: v})
        }).catch(err => {
            alert(err.response.data.message)
        })
    }

    sendFirebase = (phoneNumber) => {
        firebaseAuth.signInWithPhoneNumber(phoneNumber, this.state.reCaptcha)
            .then(res => {
                this.setState({
                    smsSent: true,
                    confirmationResult: res
                })
            })
    }

    sendSmsToFirebase = (e, v) => {
        this.state.confirmationResult.confirm(v.code)
            .then(res => {
                this.login(this.state.data)
            }).catch(err => {
            alert("Kod xato")
        })

    }


    render() {
        console.log(this.state.reCaptcha)
        return (
            <div>
                <h1 className="text-center">Login</h1>

                {!this.state.smsSent ?
                    <AvForm onValidSubmit={this.checkPasswordAndLogin}>
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
                        <button
                            onClick={this.routeToRegister}
                            type="button"
                            className={"btn btn-primary"}>Ro'yxatdan o'tish
                        </button>
                    </AvForm> :
                    <AvForm onValidSubmit={this.sendSmsToFirebase}>
                        <AvField
                            label="Telefon raqam"
                            validate={{
                                required: {value: true, errorMessage: 'Iltimos telefon raqamini kiriting'}
                            }}
                            name="code"/>
                        <button className={"btn btn-success"}>Yuborish
                        </button>
                    </AvForm>
                }

                <div id="reCaptcha" render="explicit" style={{display: 'none'}}/>
            </div>
        );
    }
}

export default Login;