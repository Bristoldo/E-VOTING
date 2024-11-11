import {
  Poppins_100Thin,
  Poppins_300Light,
  Poppins_400Regular,
  Poppins_500Medium,
  Poppins_600SemiBold_Italic,
  Poppins_800ExtraBold,
  useFonts,
} from '@expo-google-fonts/poppins';
import { MaterialIcons } from '@expo/vector-icons';
import { Stack, router } from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import {
  Button,
  Image,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';

export default function Condidtions () {
  const global_style = require ('../components/styles/bg-fonts');

  const [checked, setChecked] = useState (false);

  const [passeroute, setPasseroute] = useState ('/');

  const [iconName, setIconName] = useState ('check-box-outline-blank');

  const ChangeMode = () => {
    if (!checked) {
      setChecked (true);
      setIconName ('check-box');
      setPasseroute ('/index1');
    } else {
      setChecked (false);
      setIconName ('check-box-outline-blank');
      setPasseroute ('/');
    }
  };

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
          title: "Accueil",
          headerStyle: {
            backgroundColor: "rgb(213, 61, 78)",
          },
          headerTintColor: "#fff",
          headerTitleAlign: "center",
        }}
      />
      <ScrollView>
        <View style={styles.content}>
          <View style={{marginBottom: 5}}>
            <Image
              source={require ('./../../assets/evoting-logo-3.png')}
              style={styles.image}
            />
          </View>
          <View style={styles.container}>
            <Text style={styles.entete}>
              Conditions Génénrales d'utilisation :-
            </Text>
            <Text style={styles.text}>
              Cette application de E-voting est dédiée aux étudiants de l'Université de Dschang le
              vote des delegués d'Amphi.{'\n'}
              Ci-dessous sont les conditions générales à respecter pour utiliser cette application:
              {'\n\n'}
              1.  Etre étudiant de  l'université de Dschang.{'\n'}
              2. Avoir une compte utilisateur dans le site de Siges.{'\n'}
              3. Avoir payé au moins une tranche de la pension de l'année en cours.
              {'\n'}
              4. Avoir une compte sur le site de de l'université de Dschang.
              {'\n\n'}
              <Text
                style={styles.note}
                >
                Note :
              </Text>
              {'\n'}
              <Text style={styles.star}>*</Text>
              {' '}
              Vous ne devez voter qu'une seule candidat par scrutin.
              {'\n'}
              <Text style={styles.star}>*</Text>
              {' '}
              Aprés soumission, votre choix ne peut plus étre changé !
              {'\n'}
              <Text style={styles.star}>*</Text>
              {' '}
              Le commité de controle du déroulement des votes ne seras jamais au courant de votre choix.
              {'\n'}
            </Text>
            <View>
              <Pressable
                onPress={() => ChangeMode ()}
                style={styles.ckeckSection}
                >
                <MaterialIcons name={`${iconName}`} size={20} color="rgb(213, 61, 78)" />
                <Text style={styles.ckeckWord}>
                  J'accepte ces conditions
                </Text>
              </Pressable>
              <Button
                title="Continuer"
                color="rgb(213, 61, 78)"
                disabled={!checked}
                onPress={() => {
                  if (checked) {
                    router.navigate ('/connexion');
                  }
                }}
                />
            </View>
          </View>
        </View>
        <StatusBar style="auto" />
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create ({
  container:{
    backgroundColor: 'white', 
    padding: 15, 
    paddingTop: 0, 
    borderRadius: 5  
  },
  entete: {
    color:'rgb(213, 61, 78)',
    flex: 1,
    fontSize: 17,
    fontFamily: 'Poppins_400Regular',
    paddingVertical: 10,
    alignContent: 'center',
    justifyContent: 'center',
    textTransform: 'uppercase',
  },
  content: {
    paddingHorizontal: 20,
    paddingVertical: 15,
  },
  text: {
    fontFamily: 'Poppins_300Light',
    color: 'rgb(68, 68, 68)',
  },
  note:{
      fontWeight: 'bold',
      color:'rgb(213, 61, 78)',
      textTransform: 'uppercase',
  },
  star:{
    fontWeight: 'bold', 
    color: 'rgb(213, 61, 78)'
  },
  ckeckSection: {
    flexDirection: 'row',
    gap: 10,
    marginBottom: 35,
    flex: 0,
  },
  link: {
    justifyContent: 'flex-end',
    alignContent: 'center',
  },
  image: {
    borderRadius: 5,
    alignContent: 'center',
    justifyContent: 'center',
    height: 100,
    width: '100%',
    opacity: .8,
    objectFit: 'contain'
  },
  ckeckWord: {
    fontWeight: '600',
    alignContent: 'center',
    fontSize: 15,
  },
});
