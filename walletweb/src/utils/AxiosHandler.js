import axios from "axios";

const HttpsServer = "http://localhost:8090";

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
  // TODO: Issue Token Or Redirect Login Page
}

export default AxiosHandler;
