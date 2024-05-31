'use strict';
import { StyleSheet } from "react-native";
import { Poppins_900Black_Italic ,Poppins_500Medium ,Poppins_800ExtraBold , Poppins_100Thin , Poppins_600SemiBold_Italic ,Poppins_300Light, Poppins_700Bold ,Poppins_400Regular, useFonts } from '@expo-google-fonts/poppins';

module.exports = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'gainsboro',
    paddingHorizontal: 20,
    paddingVertical: 100,
    fontFamily: 'Poppins_100Thin',
  },

  title: {
    fontSize: 21,
    fontFamily: 'Poppins_400Regular',
    paddingBottom: 30,
    alignContent: 'center',
  }
});
