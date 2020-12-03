import React, {Component} from 'react';
import {Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {TOKEN} from "../utils/constants";
import api from '../utils/api'
import {toast} from "react-toastify";
import request from "../utils/request";

class Country extends Component {
    componentDidMount() {
        if (!localStorage.getItem(TOKEN)) {
            this.props.history.push('/login')
        } else {
            this.getCountries();
        }
    }

    getCountries = () => {
        request({
            url: api.getCountriesUrl,
            method: 'GET',
        })
            .then(jovob => {
                this.setState({countries: jovob.data._embedded.countries})
            }).catch(err => {
            console.log(err.response.data)
        })
    }

    saveCountry = (e, v) => {
        let current = this.state.currentCountry;
        request({
            url: api.addCountryUrl + (current ? ('/' + current.id) : ''),
            method: current ? 'PATCH' : 'POST',
            data: v
        }).then(res => {
            this.getCountries();
            this.closeModal();
            toast.success('Saqlandi')
        }).catch(err => {
            console.log(err)
        })
    }

    editCountry = (davlat) => {
        this.openModal();
        this.setState({currentCountry: davlat})
    }

    deleteModal = (davlat) => {
        this.setState({
            showModalDelete: true,
            currentCountry: davlat
        })
    }

    hideDeleteModal = () => {
        this.setState({
            showModalDelete: false,
            currentCountry: ''
        })
    }

    deleteCountry = () => {
        request({
            url: api.deleteCountryUrl + '/' + this.state.currentCountry.id,
            method: 'DELETE',
        }).then(jovob => {
            this.hideDeleteModal();
            this.getCountries();
            toast.success("Dleetd")
        }).catch(err => {
            console.log(err.response.data)
        })
    }


    state = {
        countries: [],
        currentCountry: '',
        showModalDelete: false
    }

    openModal = () => {
        this.setState({showModal: true})
    }

    closeModal = () => {
        this.setState({
            showModal: false,
            currentCountry: ''
        })
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Country List</h1>
                <button className="btn btn-primary"
                        onClick={this.openModal}>+Davlat qo'shish
                </button>
                <table className="table table-bordered">
                    <thead>
                    <tr>
                        <th>Tr</th>
                        <th>NomiUz</th>
                        <th>NomiRu</th>
                        <th>NomiEn</th>
                        <th colSpan={2}>Amallar</th>
                    </tr>
                    </thead>

                    <tbody>
                    {this.state.countries.map((bolta, i) =>
                        <tr key={i}>
                            <td>{i + 1}</td>
                            <td>{bolta.nameUz}</td>
                            <td>{bolta.nameRu}</td>
                            <td>{bolta.nameEn}</td>
                            <td>
                                <button
                                    onClick={() => this.editCountry(bolta)}
                                    className="btn btn-warning">Tahrirlash
                                </button>
                            </td>
                            <td>
                                <button
                                    onClick={() => this.deleteModal(bolta)}
                                    className="btn btn-danger">O'chirish
                                </button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>

                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>
                        {this.state.currentCountry ? 'Davlat tahrirlash' : 'Davlat qo\'shish'}
                    </ModalHeader>

                    <ModalBody>
                        <AvForm onValidSubmit={this.saveCountry}>
                            <AvField
                                defaultValue={this.state.currentCountry.nameUz}
                                name="nameUz"/>
                            <AvField
                                defaultValue={this.state.currentCountry.nameRu}
                                name="nameRu"/>
                            <AvField
                                defaultValue={this.state.currentCountry.nameEn}
                                name="nameEn"/>
                            <button className="btn btn-danger"
                                    onClick={this.closeModal}
                                    type="button">Cancel
                            </button>
                            <button className="btn btn-success">Save</button>
                        </AvForm>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.showModalDelete}>
                    <ModalHeader>
                        {this.state.currentCountry.nameUz + " davlatni haqiqatan o'chirasizmi?"}
                    </ModalHeader>

                    <ModalFooter>
                        <button className="btn btn-success"
                                onClick={this.hideDeleteModal}
                                type="button">Bekor qilish
                        </button>
                        <button
                            onClick={this.deleteCountry}
                            className="btn btn-danger">O'chirish
                        </button>
                    </ModalFooter>

                </Modal>

            </div>
        );
    }
}

Country.propTypes = {};

export default Country;
