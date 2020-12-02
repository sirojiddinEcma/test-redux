import React, {Component} from 'react';
import {Button} from "reactstrap";
import {TOKEN} from "../utils/constants";

class Cabinet extends Component {
    componentDidMount() {
        if (!localStorage.getItem(TOKEN)){
            this.props.history.push('/login')
        }
    }

    render() {
        const routeToOwnPage = (url) => {
            this.props.history.push(url);
        }
        return (
            <div>
                <h2>Cabinetga xush kelibsiz</h2>
                <Button
                    onClick={() => routeToOwnPage('/country')}>
                    Country
                </Button>

                <Button
                    onClick={() => routeToOwnPage('/region')}>
                    Region
                </Button>
            </div>
        );
    }
}

export default Cabinet;