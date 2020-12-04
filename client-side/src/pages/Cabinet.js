import React, {Component} from 'react';
import {Button, Modal, ModalBody, ModalHeader} from "reactstrap";
import {BASE_URL, TOKEN} from "../utils/constants";
import request from "../utils/request";
import api from "../utils/api";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {toast} from "react-toastify";

class Cabinet extends Component {
    componentDidMount() {
        if (!localStorage.getItem(TOKEN)) {
            this.props.history.push('/login')
        } else {
            this.userMe();
        }
    }

    state = {
        currentUser: '',
        photo: ''
    }

    editProfile = (e, v) => {
        v.photo = this.state.photo;
        request({
            url: api.editProfile,
            method: 'PUT',
            data: v
        }).then(res => {
            toast.success(res.data.message);
            this.userMe();
        }).catch(err => {
            toast.error(err.response.data.message)
        })
    }

    userMe = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(jovob => {
            this.setState({currentUser: jovob.data.object})
        }).catch(err => {
            localStorage.removeItem(TOKEN);
            this.props.history.push('/login')
        })
    }

    openProfileModal = () => {
        this.setState({showProfileModal: true, photo: this.state.currentUser.photo.id})
    }

    logOut = () => {
        localStorage.removeItem(TOKEN);
        this.props.history.push('/')
    }

    closeProfileModal = () => {
        this.setState({showProfileModal: false})
    }

    uploadFile = (e) => {
        let file = e.target.files[0];
        console.log(file);
        if (file && (file.type === 'image/jpeg'
            || file.type === 'image/jpg'
            || file.type === 'image/png')) {
            if (file.size > 1024000 * 2) {
                toast.error("Fay; hajmi 2MB dan oshmasin")
                return;
            }
            let data = new FormData();
            data.append("file", file);
            request({
                url: api.uploadFile,
                method: 'POST',
                data
            }).then(res => {
                this.setState({photo: res.data.object});
                toast.success('Fayl yuklandi')
            })

        }
    }

    render() {
        const routeToOwnPage = (url) => {
            this.props.history.push(url);
        }
        return (
            <div>
                <h2>Cabinetga xush
                    kelibsiz {this.state.currentUser.firstName + ' ' + this.state.currentUser.lastName}</h2>
                <Button
                    onClick={() => routeToOwnPage('/country')}>
                    Country
                </Button>

                <Button
                    onClick={() => routeToOwnPage('/region')}>
                    Region
                </Button>


                <Button
                    onClick={this.openProfileModal}>
                    Profilni tahrirlash
                </Button>

                <Button
                    onClick={this.logOut}>
                    Chiqish
                </Button>

                <div style={{width: '100px'}}
                     className="m-2">
                    {this.state.currentUser && this.state.currentUser.photo &&
                    <img className="img-fluid" src={BASE_URL + 'attachment/' + this.state.currentUser.photo.id}
                         alt=""/>}
                </div>


                <Modal isOpen={this.state.showProfileModal}>
                    <ModalHeader>
                        Profilni tahrirlash
                    </ModalHeader>

                    <ModalBody>
                        <AvForm
                            model={this.state.currentUser}
                            onValidSubmit={this.editProfile}>
                            <AvField name="firstName"/>
                            <AvField name="lastName"/>
                            <AvField name="phoneNumber"/>
                            <AvField
                                onChange={this.uploadFile}
                                name="photoId"
                                type="file"/>
                            <div style={{width: '100px'}} className="m-2">
                                <img className="img-fluid"
                                     src={BASE_URL + 'attachment/' + this.state.photo} alt=""/>
                            </div>
                            <AvField type="password" name="password"/>
                            <button className="btn btn-danger"
                                    onClick={this.closeProfileModal}
                                    type="button">Cancel
                            </button>
                            <button disabled={!this.state.photo} className="btn btn-success">Saqlash</button>
                        </AvForm>
                    </ModalBody>
                </Modal>

            </div>
        );
    }
}

export default Cabinet;