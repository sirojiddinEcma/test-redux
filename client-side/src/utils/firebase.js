import firebase from 'firebase/app';
import 'firebase/auth';

const prodConfig = {
    apiKey: "AIzaSyDH67W9r2bX3AR8LZPS6yrutMABe2M4QM4",
    authDomain: "app-warehouseg8.firebaseapp.com",
    projectId: "app-warehouseg8",
    storageBucket: "app-warehouseg8.appspot.com",
    messagingSenderId: "834200546960",
    appId: "1:834200546960:web:e6781192a94bad12e63215",
    measurementId: "G-E6CHSHVHTW"
};

const devConfig = {
    // apiKey: "AIzaSyABl8AO9jthmlboCGz9o-MzY-QDstfXPCE",
    // authDomain: "app-e-expert.firebaseapp.com",
    // databaseURL: "https://app-e-expert.firebaseio.com",
    // projectId: "app-e-expert",
    // storageBucket: "app-e-expert.appspot.com",
    // messagingSenderId: "992748865347",
    // appId: "1:992748865347:web:e27c2bd5bf98bd41ed51f6",
    // measurementId: "G-G01E5MZLDE"

    apiKey: "AIzaSyDH67W9r2bX3AR8LZPS6yrutMABe2M4QM4",
    authDomain: "app-warehouseg8.firebaseapp.com",
    projectId: "app-warehouseg8",
    storageBucket: "app-warehouseg8.appspot.com",
    messagingSenderId: "834200546960",
    appId: "1:834200546960:web:e6781192a94bad12e63215",
    measurementId: "G-E6CHSHVHTW"
};

const config = process.env.NODE_ENV === 'production'
    ? prodConfig
    : devConfig;

if (!firebase.apps.length) {
    firebase.initializeApp(config);
}

const firebaseAuth = firebase.auth();

export {
    firebaseAuth, firebase
};

