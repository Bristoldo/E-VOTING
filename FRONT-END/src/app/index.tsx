import { Link, Stack } from "expo-router";
import React, { useState } from "react";

import { MaterialIcons } from "@expo/vector-icons";
import { StatusBar } from "expo-status-bar";
import {
  Poppins_100Thin,
  Poppins_300Light,
  Poppins_400Regular,
  Poppins_500Medium,
  Poppins_600SemiBold_Italic,
  Poppins_800ExtraBold,
  useFonts,
} from "@expo-google-fonts/poppins";
import {
  Button,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from "react-native";
import Button1 from "../components/button";

export default function Condidtions() {
  const global_style = require("./../components/styles/bg-fonts");

  const [checked, setChecked] = useState(false);

  const [passeroute, setPasseroute] = useState("/");

  const [iconName, setIconName] = useState("check-box-outline-blank");

  const ChangeMode = () => {

    if (!checked) {
      setChecked(true);
      setIconName("check-box");
      setPasseroute("/index1");
    } else {
      setChecked(false);
      setIconName("check-box-outline-blank");
      setPasseroute("/");
    }

  };

  let [fontsLoaded, fontError] = useFonts({
    Poppins_100Thin,
    Poppins_500Medium,
    Poppins_400Regular,
    Poppins_800ExtraBold,
    Poppins_600SemiBold_Italic,
    Poppins_300Light,
  });

  if (!fontsLoaded && !fontError) {
    return null;
  }

  return (
    <>
      <Stack.Screen
        options={{
          title: "Accueil",
          headerStyle: {
            backgroundColor: "#f4511e",
          },
          headerTintColor: "#fff",
          headerTitleAlign: "center",
        }}
      />
      <ScrollView>
        <View style={styles.content}>
          <Text style={styles.entete}>
            {""}
            Condidtions Génénrales d'utilisation
          </Text>
          <Text style={styles.text}>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Corrupti
            quaerat, asperiores aut reprehenderit recusandae sint voluptatibus
            modi error, dolorem, aliquid veniam nostrum earum quam incidunt
            suscipit minima adipisci perspiciatis odit! Lorem ipsum dolor, sit
            amet consectetur adipisicing elit. Corrupti quaerat, asperiores aut
            reprehenderit recusandae sint voluptatibus modi error, dolorem,
            aliquid veniam nostrum earum quam incidunt suscipit minima adipisci
            perspiciatis odit! Lorem ipsum dolor, sit amet consectetur
            adipisicing elit.
          </Text>
          <View>
            <Pressable onPress={() => ChangeMode()} style={styles.ckeckSection}>
              <MaterialIcons name={`${iconName}`} size={18} color="red" />
              <Text
                style={{
                  fontWeight: "600",
                  alignContent: "center",
                  fontSize: 15,
                }}
              >J'accepte ces condidtions
              </Text>
            </Pressable>
            <Link href="/connexion" disabled={!checked} style={styles.link}>
              <Button
                title="Continuer"
                color="coral"
                disabled={!checked}
                onPress={() => console.log("")}
              />
            </Link>
          </View>

          <Link href={passeroute} style={{ display: "none" }}>
            <Button1 title="Continuer" disabled="false" />
          </Link>
          <StatusBar style="auto" />
        </View>
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create({
  entete: {
    fontSize: 20,
    fontFamily: "Poppins_400Regular",
    paddingBottom: 10,
    alignContent: "center",
  },
  content: {
    paddingHorizontal: 20,
    paddingVertical: 20,
  },


  text: {
    fontFamily: "Poppins_300Light",
    textAlign: "justify",
  },

  ckeckSection: {
    flexDirection: "row",
    gap: 10,
    marginVertical: 15,
  },
  link: {
    justifyContent: "flex-end",
    alignContent: "center",
  },
});
