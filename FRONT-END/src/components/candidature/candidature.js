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
import { Link } from 'expo-router';
import React from 'react';
import { Button, Image, StyleSheet, Text, View } from 'react-native';
import voterCandidat from '../api/voter';

export default function Candidature({item, view_vote_btn}) {
  const image = require ('./../../../assets/images.jpg');
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
    <View style={styles.content}>
      <View>
        <Image source={image} style={styles.image} />
      </View>
      <View style={styles.subContent}>
        <Link href={`/user/candidature/${item.id}`}>
          <View
            style={{
              flexDirection: 'row',
              alignItems: 'center',
              gap: 5,
              flex: 1,
            }}
          >
            <MaterialIcons name="account-box" size={30} color="rgb(213, 61, 78)" />
            <Text style={[styles.title, styles.font]}>
              Voir Profile{' '} <MaterialIcons name='arrow-forward'/>
            </Text>
          </View>
        </Link>
        <View style={styles.mainContent}>
          <View
            style={[
              styles.contenu_voix_vote,
              !view_vote_btn && {flexDirection: 'column'},
            ]}
          >
            <Text style={styles.voix}>
              <Text style={{ justifyContent: 'center', alignContent: 'center', alignItems: 'center', justifyContent:'center' }}>{item.nombre_voix} Voix
              </Text>
            </Text>
            {view_vote_btn &&
              <Button
                title="voter"
                color="rgb(213, 61, 78)"
                onPress={() => voterCandidat (item.id)}
              />}
          </View>
        </View>
      </View>
    </View>
  );
}
const styles = StyleSheet.create ({
  title: {
    fontSize: 15,
    color: 'rgb(68, 68, 68)',
  },
  content: {
    flexDirection: 'row',
    marginBottom: 10,
    alignContent: 'center',
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 6,
  },
  subContent: {
    flex: 1,
    borderTopEndRadius: 5,
    borderBottomEndRadius: 5,
    gap: 5,
    padding: 5,
    paddingHorizontal: 11,
    justifyContent: 'space-around',
  },
  mainContent: {},
  image: {
    height: 100,
    width: 100,
    borderRadius: 5,
    borderTopRightRadius: 0,
    borderBottomRightRadius: 0,
  },
  btn_link: {
    justifyContent: 'space-between',
    flex: 1,
    alignItems: 'center',
  },
  voix: {
    textAlign: 'center',
    fontFamily: 'Poppins_300Light',
    gap: 5,
    backgroundColor: 'rgb(213, 61, 78)',
    padding: 5,
    color: 'white',
    alignContent: 'center',
    borderRadius: 3,
  },
  contenu_voix_vote: {
    justifyContent: 'space-between',
    flexDirection: 'row',
    gap: 50,
    right: 0,
  },
  font: {
    fontFamily: 'Poppins_400Regular',
  },
});
