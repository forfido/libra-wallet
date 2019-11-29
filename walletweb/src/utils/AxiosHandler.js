import axios from "axios";
import Constants from "@/constants";

const HttpsServer = Constants.ENDPOINT;

const AxiosHandler = axios.create({
  baseURL: HttpsServer,
  timeout: 10000
});

if (localStorage.getItem(Constants.ACCESS_TOKEN)) {
  AxiosHandler.defaults.headers.common[Constants.AUTHORIZTION] =  "Bearer " + localStorage.getItem(Constants.ACCESS_TOKEN);
} else {
  AxiosHandler.defaults.headers.common[Constants.AUTHORIZTION] = "";
}

export default AxiosHandler;
