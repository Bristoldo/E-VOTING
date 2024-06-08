import { Stack } from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import Login from '../components/credentials/fields';


export default function Accueil() {

    return (
        <>
        <Stack.Screen options={{
          title: 'Liste des Candidats',
          headerTintColor: '#fff',
        }}
        />
        <View style={styles.container}>
          <Text style={styles.title}> Bienvenue </Text>
          <Login />
          <StatusBar style="auto" />
        </View>
        </>
      );
}

const styles = StyleSheet.create ({
    container: {
      flex: 1,
      backgroundColor: 'gainsboro',
      // alignItems: 'center',
      // justifyContent: 'center',
    },
    title: {
      // fontFamily: 'poppins-regular',
      fontSize: 20,
    }
  });