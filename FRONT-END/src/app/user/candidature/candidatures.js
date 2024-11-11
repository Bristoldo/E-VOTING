import { Stack } from 'expo-router';
import { useEffect, useState } from 'react';
import { FlatList, StyleSheet, Text, View } from 'react-native';
import Candidature from '../../../components/candidature/candidature';


export default function Candidatures () {
  const global_style = require ('../../../components/styles/bg-fonts');

  const filiere = 'PHILOSOPHIE';

  const niveau = 1;

  const data = {
    filiere: filiere,
    niveau: niveau,
  };


  const [candidatures, setCandidatures] = useState ();

  const url = "http://127.0.0.1:8080/api/auth/scrutin/filiere-niveau";

  const headers = {
    'Content-Type': 'application/json',
    // "Authorization": "Bearer " + AsyncStorage.getItem("token")
  };
  const getScrutinApi = data => {
    fetch (url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify (data),
    })
      .then (function (res) {
        return res.json ();
      })
      .then (function (scrutin) {
        setCandidatures (scrutin[4].candidatures);
        return;
      })
      .catch (error => {
        console.log (error);
      });
  };

  const getScrutins = data => {
    getScrutinApi (data);
  };
  useEffect (() => {
    getScrutins (data);
  }, []);

global.voter = true;

  return (
    <>
      <Stack.Screen
        options={{
          title: "List des candidatures",
          headerStyle: {
            backgroundColor: "rgb(213, 61, 78)",
          },
          headerTintColor: "#fff",
          headerTitleAlign: "center",
        }}
      />
        <View style={global_style.container}>
          <View style={styles.title}>
              <Text style={global_style.p_regular}>
                Scrutin:
              </Text>
              <Text style={[global_style.p_regular, { color: 'rgb(213, 61, 78)', textTransform: 'uppercase' }]}>
                vote des delegues 
            </Text>
          </View>
          <FlatList
            data={candidatures}
            renderItem={({item}) => <Candidature item={item}  view_vote_btn={true}/>}
            showsVerticalScrollIndicator={false}
          
          />
        </View>
    </>
  );
}

const styles = StyleSheet.create({
  title:{
    flexDirection: 'row',
    gap: 10,
    marginBottom: 20
  }
})