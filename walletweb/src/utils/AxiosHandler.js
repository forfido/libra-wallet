import axios from "axios";

const HttpsServer = "https://reqres.in";

const AxiosHandler = axios.create({
  baseURL: HttpsServer,
  timeout: 10000
});

if (localStorage.getItem("Authentication")) {
  AxiosHandler.defaults.headers.common["Authentication"] = localStorage.getItem(
    "Authentication"
  );
} else {
  AxiosHandler.defaults.headers.common["Authentication"] = "";
  // TODO: Issue Token Or Redirect Login Page
}

export default AxiosHandler;
