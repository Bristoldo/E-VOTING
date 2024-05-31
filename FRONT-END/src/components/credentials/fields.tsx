import React, { useState } from "react";
import { Button, StyleSheet, Text, TextInput, View } from "react-native";
import { user_login } from "../api/userApi";
import {
  Poppins_900Black_Italic,
  Poppins_500Medium,
  Poppins_800ExtraBold,
  Poppins_100Thin,
  Poppins_600SemiBold_Italic,
  Poppins_300Light,
  Poppins_700Bold,
  Poppins_400Regular,
  useFonts,
} from "@expo-google-fonts/poppins";
import { Link } from "expo-router";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    user_login({
      email: email,
      password: password,
    })
      .then((result) => {
        if (result.status == 200) {
          console.log(result);
          console.log("AccessToken", result.data.token);
        } else {
          console.log("error");
        }
      })
      .catch((err) => {
        console.log(err);
      });
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
        <Link href={"/help"}>
          <Text style={styles.probText}>Probleme de connexion? </Text>
        </Link>
      </View>
      <Button onPress={() => handleLogin()} title="Connexion" color="coral" />
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
    color: "dark",
  },
});
