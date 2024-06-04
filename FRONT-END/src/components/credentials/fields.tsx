import axios from "axios";
import { Link } from "expo-router";
import React, { useState } from "react";
import { Button, StyleSheet, Text, TextInput, View } from "react-native";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    axios
      .get("http://127.0.0.1:8080/api/auth/candidature", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json", 
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((result) => {
        return console.log(result.data);
      });
    // user_login({
    //   email: email,
    //   password: password,
    // })
    //   .then((result) => {
    //     if (result.status == 200) {
    //       console.log(result);
    //       console.log("AccessToken", result);
    //     } else {
    //       console.log("error");
    //     }
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  };

  function changeHandlerEmail(val) {
    setEmail(val);
  }

  function changeHandlerPassword(val) {
    setPassword(val);
  }

  return (
    <View>
      <TextInput
        style={styles.input1}
        placeholder="Login ..."
        onChangeText={(val) => changeHandlerEmail(val)}
        // value={email}
      />
      <View style={styles.mdp}>
        <TextInput
          style={styles.input2}
          secureTextEntry={true}
          placeholder="Mot de passe ..."
          onChangeText={(val) => changeHandlerPassword(val)}
          // value={password}
        />
        <Link href={"/help"} style={styles.help}>
          <Text style={styles.probText}>Probleme de connexion ? </Text>
        </Link>
      </View>
      <Link href={"/user/candidature/candidatures"} style={styles.help}>
      <Button onPress={() => handleLogin()} title="Connexion" color="coral" />
      </Link>
  
    </View>
  );
}

const styles = StyleSheet.create({
  input1: {
    marginBottom: 10,
    paddingHorizontal: 8,
    paddingVertical: 6,
    fontFamily: "Poppins_100Thin",
    borderBottomEndRadius: 5,
    borderBottomStartRadius: 5,
    borderTopStartRadius: 5,
    borderTopEndRadius: 5,
    borderColor: "gray",
    borderWidth: 1,
  },
  input2: {
    paddingHorizontal: 8,
    paddingVertical: 6,
    fontFamily: "Poppins_100Thin",
    borderBottomEndRadius: 5,
    borderBottomStartRadius: 5,
    borderTopStartRadius: 5,
    borderTopEndRadius: 5,
    borderColor: "gray",
    borderWidth: 1,
  },
  mdp: {
    marginBottom: 10,
  },
  probText: {
    color: "gray",
    fontFamily: "Poppins_300Light",
  },
  help: {
    marginBottom: 10,
  },
});
