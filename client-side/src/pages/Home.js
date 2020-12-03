import React, {Component} from 'react';
import {LANGUAGE} from "../utils/constants";

class Home extends Component {

    getLang(e) {
        localStorage.setItem(LANGUAGE,e.target.value)
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
            </div>
        );
    }
}

export default Home;