import * as Font from 'expo-font';
import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Login from './src/components/login';

const getFonts = () =>
  Font.loadAsync ({
    'poppins-regular': require ('./assets/fonts/Poppins-Regular.ttf'),
    'poppins-semi-bold': require ('./assets/fonts/Poppins-SemiBold.ttf'),
  });

export default function App () {
  const [fontsLoaded, setFontsLoaded] = useState (false);

  // if (fontsLoaded) {
    return (
      <View style={styles.container}>
        <Text style={styles.title}> Bienvenue </Text>
        <Login />
        <StatusBar style="auto" />
      </View>
    );
  // } else {
    // return (
      // <AppLoading
        // startAsync={getFonts}
        // onFinish={() => setFontsLoaded (true)}
      // />
    // );
  // }
}


const styles = StyleSheet.create ({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontFamily: 'poppins-regular',
    fontSize: 20,
  }
});
