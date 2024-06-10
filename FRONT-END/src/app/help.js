import {
  Poppins_100Thin,
  Poppins_300Light,
  Poppins_400Regular,
  Poppins_500Medium,
  Poppins_600SemiBold_Italic,
  Poppins_800ExtraBold,
  useFonts,
} from '@expo-google-fonts/poppins';
import { Stack } from "expo-router";
import React from "react";
import { StyleSheet, Text, View } from "react-native";

export default function Help(){
    const global_style = require ('../components/styles/bg-fonts');

    
  let [fontsLoaded, fontError] = useFonts ({
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
          title: "Aide",
          headerStyle: {
              backgroundColor: "rgb(213, 61, 78)",
          },
          headerTintColor: "#fff",
          headerTitleAlign: "center",
        }}
      />
        <View style={global_style.container}>
            <Text style={styles.text}>
                Pour tout problèmes de connexion, veuillez contacter l'administeur. {'\n'}
                Rassurez-vous d'avoir un compte utilisateur valide dans le site
                <Text style={{ color: 'blue' }}> siges-online.org</Text>.
            </Text>
        </View>
        </>
    );
}

const styles = StyleSheet.create({
  text:{ 
    fontFamily: 'Poppins_400Regular', 
    color: 'rgb(68, 68, 68)',
  }
})