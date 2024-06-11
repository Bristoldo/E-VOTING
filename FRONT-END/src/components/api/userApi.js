import AsyncStorage from "@react-native-async-storage/async-storage";

const url = "http://127.0.0.1:8080/api/auth/authenticate";

// https://853a-129-0-103-17.ngrok-free.app

const headers = {
  "Content-Type": "application/json",
};


const storeData = (data) => {
  AsyncStorage.clear;
  AsyncStorage.setItem("token", data.token);
  delete data.token;
  AsyncStorage.setItem("auth", JSON.stringify(data));
}

export const user_login = (data) => {
/*
  fetch(url, {
    method: "POST",
    headers: headers,
    body: JSON.stringify(data),
  })
    .then(function (res) {
      if (res.status == 200) {
        return res.json();
      }else{
        return res.status;
      }
    })
    .then(function (data) {
      if (data == 403) {
        console.log("Authentication error");
      } else {
        storeData(data);
        console.log("Success");
        return 200;
      }
    })
    .catch((error) => {
      console.log(error);
      return 500;
    });
*/
    if(data.email == "amelia.stehr@gmail.com" && data.password == "12345"){
      return 200;
    }else{
      return undefined;
    }

  };
