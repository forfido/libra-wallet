import axios from "axios";

const HttpsServer = "https://reqres.in";

const AxiosHandler = axios.create({
  baseURL: HttpsServer,
  timeout: 10000
});

if (localStorage.getItem("Authorization")) {
  AxiosHandler.defaults.headers.common["Authorization"] = localStorage.getItem(
    "Authorization"
  );
} else {
  AxiosHandler.defaults.headers.common["Authorization"] = "";
}

export default AxiosHandler;
