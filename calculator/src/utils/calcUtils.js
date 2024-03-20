import axios from 'axios';

export const evaluate = (expression, username) => {
  const config = {
    headers: {
      "Content-type": "application/json",
      "Authorization": "Bearer " + sessionStorage.getItem("token"),
    },
  };
  return axios.post("http://localhost:8080/evaluate", { expression: expression, username: username }, config);
}

export const getHistory = (username) => {
  const config = {
    headers: {
      "Content-type": "application/json",
      "Authorization" : "Bearer " + sessionStorage.getItem("token"),
    }
  };
  return axios.post("http://localhost:8080/history", {username: username}, config);
};

