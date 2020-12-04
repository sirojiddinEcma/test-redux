import React, {Component} from 'react';
import {LANGUAGE} from "../utils/constants";

class Home extends Component {

    getLang(e) {
        localStorage.setItem(LANGUAGE, e.target.value)
    }

    routeToNext = () => {
        this.props.history.push('/verification/qalay/yomon/nimedi')
        // this.props.history.goForward()
    }

    render() {
        return (
            <div>
                <h1>Home page</h1>

                <select name="lang" onChange={this.getLang}>
                    <option value="" disabled>Tilni tanlang</option>
                    <option value="uz">O'zbek tili</option>
                    <option value="ru">Russkiy</option>
                    <option value="en">English</option>
                </select>
                <button onClick={this.routeToNext}>Next</button>
            </div>
        );
    }
}

export default Home;