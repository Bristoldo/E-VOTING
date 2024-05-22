import axios from "axios";

const ApiManager = axios.create({
    baseURL: "http://localhost:8081/api/auth",
    responseType: "json",
    withCredentials:true
});
 
export default ApiManager;