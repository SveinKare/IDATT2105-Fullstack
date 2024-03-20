import axios from "axios";

export const getToken = (username, password) => {
  const config = {
          headers: {
              "Content-type": "application/json",
          },
      };
  return axios.post("http://localhost:8080/token", {username: username, password: password}, config);
}