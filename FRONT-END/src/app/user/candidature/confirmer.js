import { MaterialIcons } from '@expo/vector-icons';
import { Link, Stack } from 'expo-router';
import React from 'react';
import { Button, ScrollView, StyleSheet, Text, View } from 'react-native';

export default function Comfirmer () {
  const global_style = require ('../../../components/styles/bg-fonts');

  return (
    <>
    <Stack.Screen
    options={{
      title: "Confirmation",
      headerStyle: {
        backgroundColor: "rgb(213, 61, 78)",
      },
      headerTintColor: "#fff",
      headerTitleAlign: "center",
    }}
    />
      <ScrollView style={global_style.container}>
        <View
          style={{
            alignItems: 'center',
          }}
        >
          <MaterialIcons name="verified" size={200} color="rgb(213, 61, 78)" style={{  }} />
          <Text
            style={styles.title}
          >
            Vote Effectué !
          </Text>
          <View style={{marginTop: 50}}>
            <Link href={"/user/candidature/tendance"}>
             <Button title="Voir la tendance" color="rgb(213, 61, 78)" />
            </Link>
          </View>
        </View>
      </ScrollView>
    </>
  );
}
const styles = StyleSheet.create({
  title:{
    fontFamily: 'Poppins_800ExtraBold',
    color: 'rgb(213, 61, 78)',
    fontSize: 20,
  }
})