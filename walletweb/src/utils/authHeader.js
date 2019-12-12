export function authHeader() {
    // return authorization header with jwt token
    if (localStorage.getItem("accessToken")) {
        return { "Authorization" : "Bearer " + localStorage.getItem("accessToken"),
                 "Content-Type" : "application/json" };
    } else {
        return {};
    }
}