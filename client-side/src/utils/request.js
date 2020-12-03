import {BASE_URL, LANGUAGE, TOKEN} from "./constants";
import axios from 'axios'

export default (params) => {
    let url = params.url;
    let method = params.method;
    let data = params.data;
    url = BASE_URL + url;

    let headers = {
        'Authorization': localStorage.getItem(TOKEN),
        'Access-Control-Allow-Origin': '*',
        'Accept-Language': localStorage.getItem(LANGUAGE)
    };

    return axios({
        url,
        method,
        data,
        headers
    })

}