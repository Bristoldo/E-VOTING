import {
  Poppins_100Thin,
  Poppins_400Regular,
  Poppins_500Medium,
  Poppins_600SemiBold_Italic,
  Poppins_800ExtraBold,
  useFonts
} from "@expo-google-fonts/poppins";
import { Link, Stack } from "expo-router";
import { StatusBar } from "expo-status-bar";
import React from "react";
import { StyleSheet, Text, View } from "react-native";
import Login from "../components/credentials/fields";
// import Button from '../components/bouton';

export default function App() {
  const global_style = require("./../components/styles/bg-fonts");

  let [fontsLoaded, fontError] = useFonts({
    Poppins_100Thin,
    Poppins_500Medium,
    Poppins_400Regular,
    Poppins_800ExtraBold,
    Poppins_600SemiBold_Italic,
  });

  if (!fontsLoaded && !fontError) {
    return null;
  }

  return (
    <>
      <Stack.Screen
        options={{
          title: "Connexion",
          headerStyle: {
            backgroundColor: "#f4511e",
          },
          headerTintColor: "#fff",
          headerTitleAlign: "center",
        }}
      />
      <View style={global_style.container}>
        <Text style={global_style.title}>Bienvenue </Text>
        <Login />
        <StatusBar style="auto" />
        {/* <Link href={"/candidature/candidats"}> candidatures </Link> */}
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  title: {
    fontSize: 20,
  },
});
