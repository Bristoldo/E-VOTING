import axios from "axios";

const ApiManager = axios.create({
    baseURL: "http://localhost:8080/api/auth",
    responseType: 'json',
    withCredentials:true,
    timeout: 10000
});

export default ApiManager;