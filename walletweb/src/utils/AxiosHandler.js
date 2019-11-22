import axios from "axios";
import Constants from "@/constants";

const HttpsServer = Constants.ENDPOINT;

const AxiosHandler = axios.create({
  baseURL: HttpsServer,
  timeout: 10000
});

if (localStorage.getItem(Constants.AUTHORIZTION)) {
  AxiosHandler.defaults.headers.common[Constants.AUTHORIZTION] = localStorage.getItem(Constants.AUTHORIZTION);
} else {
  AxiosHandler.defaults.headers.common[Constants.AUTHORIZTION] = "";
}

export default AxiosHandler;
