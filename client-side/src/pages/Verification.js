import React, {Component} from 'react';
import request from "../utils/request";
import api from "../utils/api";
import {TOKEN} from "../utils/constants";

class Verification extends Component {
    componentDidMount() {
        let params = this.props.match.params;
        request({
            url: api.verificationUrl + '?email=' + params.email + '&code=' + params.code,
            method: 'GET'
        }).then(res => {
            localStorage.setItem(TOKEN, 'Bearer ' + res.data.object);
        })
    }

    routeToCabinet=()=>{
        this.props.history.push('/login')
    }

    render() {
        return (
            <div>
                <h1>Verification</h1>
                <button
                    onClick={this.routeToCabinet}
                    className="btn btn-success">Go to cabinetga</button>
            </div>
        );
    }
}

export default Verification;