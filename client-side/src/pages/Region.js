import React, {Component} from 'react';
import {Modal, ModalBody, ModalHeader} from "reactstrap";
import {AvField, AvForm} from 'availity-reactstrap-validation'
import {TOKEN} from "../utils/constants";
import api from "../utils/api";
import {toast} from "react-toastify";
import request from "../utils/request";

class Region extends Component {
    getCountries = () => {
        request({
            url: api.getCountriesUrl,
            method: 'GET'
        }).then(jovob => {
            this.setState({countries: jovob.data._embedded.countries})
        }).catch(err => {
            console.log(err.response.data)
        })
    }

    getRegions = () => {
        request({
            url: api.getRegionsUrl,
            method: 'GET',
        })
            .then(jovob => {
                this.setState({regions: jovob.data._embedded.regions})
            }).catch(err => {
            console.log(err.response.data)
        })
    }

    saveRegion = (e, v) => {
        let current = this.state.currentRegion;
        request({
            url: api.loginUrl,
            method: current ? 'PATCH' : 'POST',
            data: v
        }).then(res => {
            this.getRegions();
            this.closeModal();
            toast.success('Saqlandi')
        }).catch(err => {
            console.log(err)
        })
    }

    componentDidMount() {
        if (!localStorage.getItem(TOKEN)) {
            this.props.history.push('/login')
        } else {
            this.getCountries()
            this.getRegions()
        }
    }

    state = {
        regions: [],
        countries: [],
        showModal: false,
        currentRegion: ''
    }

    openModal = () => {
        this.setState({showModal: true})
    }

    closeModal = () => {
        this.setState({showModal: false})
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Region List</h1>
                <button className="btn btn-primary"
                        onClick={this.openModal}
                >+Viloyat qo'shish
                </button>
                <table className="table table-bordered mt-2">
                    <thead>
                    <tr>
                        <th>Tr</th>
                        <th>Nomi</th>
                        <th colSpan={2}>Amallar</th>
                    </tr>
                    </thead>

                    <tbody>
                    {this.state.regions.map((bolta, i) =>
                        <tr key={i}>
                            <td>{i + 1}</td>
                            <td>{bolta.nameEn}</td>
                            <td>
                                <button className="btn btn-warning">Tahrirlash</button>
                            </td>
                            <td>
                                <button className="btn btn-danger">O'chirish</button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>

                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>
                        <h3>Viloyat qo'shish</h3>
                    </ModalHeader>

                    <ModalBody>
                        <AvForm onValidSubmit={this.saveRegion}>
                            <AvField name="nameUz"/>
                            <AvField name="nameRu"/>
                            <AvField name="nameEn"/>
                            <AvField
                                type="select"
                                name="country">
                                <option value="" disabled>Davlatni tanlang</option>
                                {this.state.countries.map(bolta =>
                                    <option value={"/" + bolta.id}>{bolta.nameEn}</option>
                                )}
                            </AvField>
                            <button className="btn btn-danger"
                                    onClick={this.closeModal}
                                    type="button">Cancel
                            </button>
                            <button className="btn btn-success">Save</button>
                        </AvForm>
                    </ModalBody>
                </Modal>
            </div>
        );
    }
}

Region.propTypes = {};

export default Region;
