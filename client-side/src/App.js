import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Country from "./pages/Country";
import Region from "./pages/Region";
import Login from "./pages/Login";
import Cabinet from "./pages/Cabinet";
import Home from "./pages/Home";
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Register from "./pages/Register";
import Verification from "./pages/Verification";

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
                        <Route exact path="/verification/:email/:code" component={Verification}/>
                    </Switch>
                </Router>
            </div>
        );
    }
}

App.propTypes = {};

export default App;
