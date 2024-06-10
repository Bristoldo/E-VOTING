import { Stack, useLocalSearchParams } from 'expo-router';
import React, { useEffect, useState } from 'react';
import { Button, Image, ScrollView, StyleSheet, Text, View } from 'react-native';
import voterCandidat from '../../../components/api/voter';

export default function Details () {
  
  // let [fontsLoaded, fontError] = useFonts({
  //     Poppins_100Thin,
  //     Poppins_500Medium,
  //     Poppins_400Regular,
  //     Poppins_800ExtraBold,
  //     Poppins_600SemiBold_Italic,
  //     Poppins_300Light,
  // });

  // if (!fontsLoaded && !fontError) {
  //     return null;
  // }
  
  const [candidature, setCandidature] = useState();
  
  const [etudiant, setEtuiant] = useState();
  
  const global_style = require ('../../../components/styles/bg-fonts');
  
  const image = require ('./../../../../assets/images.jpg');
  
  const {id} = useLocalSearchParams ();
  
  const url = `http://localhost:8080/api/auth/candidature/${id}`;
  
  
  const headers = {
    'Content-Type': 'application/json',
    // "Authentication": "Bearer " + getToken
  };
  const getCandidatureByIdApi = async () => {
    return await  fetch (url, {
      method: 'GET',
      headers: headers,
    })
    .then (function (res) {
      return res.json ();
    })
    .then (function (result) {
      return JSON.stringify (result);
    })
    .catch (error => {
      console.log (error);
    });
  };
  
  const getCandidatureById = () => {
    const res = getCandidatureByIdApi ();
    res.then((data) => { 
      data = JSON.parse(data);
      setCandidature(data);
      setEtuiant(data.etudiant);
    })
  };

  useEffect (() => {
    getCandidatureById();
  }, [candidature]);

  return (
    <>
    <Stack.Screen
    options={{
      title: "Informations sur candidature",
      headerStyle: {
        backgroundColor: "rgb(213, 61, 78)",
      },
      headerTintColor: "#fff",
      headerTitleAlign: "center",
    }}
    />
    <ScrollView showsVerticalScrollIndicator={false}>
      <View style={global_style.container}>
          <View style={styles.main}>
            <Image source={image} style={styles.image} />
            <View style={[styles.names, {padding: 10}]}>
            {  candidature ? (  
              <><Text style={styles.font_text}>
                  {`${candidature.etudiant.firstname}`}
                </Text><Text style={styles.font_text}>
                    {`${candidature.etudiant.lastname}`}
                  </Text></>
            ):( <Text> Chargement... </Text>) }
            </View>
          </View>
          <View style={{backgroundColor: 'white', marginBottom: 10}}>
            <View
              style={{
                borderBottomWidth: 1,
                borderBottomColor: 'rgb(213, 61, 78)',
                marginBottom: 20,
              }}
            >
              <Text style={[global_style.p_regular, {color: "rgb(213, 61, 78)"}]}>
                Informations
              </Text>
            </View>
            <View style={{marginBottom: 10}}>
            {  candidature ? (  
              <Text style={styles.font_text}>
                <Text>
                Filiere:{'  '} { candidature.etudiant.filiere } 
                </Text>
                {'\n'}
                <Text>
                Niveau:{'  '} { candidature.etudiant.niveau }
                </Text>
                {'\n'}
                <Text>
                A postulé le:{'  '} { (""+candidature.date_postuler).substring(0,10) }
                </Text>
                {'\n'}
                <Text>
                Nombre de voix:{'  '} { candidature.nombre_voix }
                </Text>                
                {'\n'}
              </Text>
            ):( <Text> Chargement... </Text>) }
            </View>
          </View>
          <View style={{backgroundColor: 'white'}}>
            <Text
              style={[
                global_style.p_regular,
                {
                  color: 'rgb(213, 61, 78)',
                  borderBottomWidth: 1,
                  borderBottomColor: 'rgb(213, 61, 78)',
                  marginBottom: 20,
                },
              ]}
            >
              Speech
            </Text>
            <View style={{marginBottom: 20}}>
              <ScrollView style={{ height: 250 }}>
                    <Text style={styles.font_text}>
                        Bonjour a tous {'\n'}
                        je desire etre le prechain delegue de d'Amphi pour cette annee.{'\n'} 
                        Mon role est de vous presenter et de faire entendre vos preocupations et suggestions aupres de l'administration et des professeurs.{'\n'}   
                        Coordialement.
                    </Text>
              </ScrollView>
            </View> 
            { global.voter && <Button title="voter" color="rgb(213, 61, 78)" onPress={() => voterCandidat (candidature.id)}/>}
          </View> 
        </View>
    </ScrollView> 
    </>
  );
} 

const styles = StyleSheet.create ({
  main: {
    backgroundColor: 'white',
    marginBottom: 10,
    alignItems: 'center',
  },
  image: {
    height: 120,
    width: 120,
  },
  names: {
    flexDirection: 'row',
    gap: 8,
  },
  font_text: {
    fontFamily: 'Poppins_400Regular',
    color: 'rgb(68, 68, 68)',
    gap: 3,
  },
});
