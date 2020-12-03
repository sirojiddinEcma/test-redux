import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Country from "./pages/Country";
import Region from "./pages/Region";
import Login from "./pages/Login";
import Cabinet from "./pages/Cabinet";
import Home from "./pages/Home";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Register from "./pages/Register";

class App extends Component {
    render() {
        return (
            <div className="container pt-3">
                <ToastContainer/>
                <Router>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/country" component={Country}/>
                        <Route exact path="/region" component={Region}/>
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/register" component={Register}/>
                        <Route exact path="/cabinet" component={Cabinet}/>
                    </Switch>
                </Router>
            </div>
        );
    }
}

App.propTypes = {};

export default App;
