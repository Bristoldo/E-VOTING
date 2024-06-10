import { Stack, router } from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import React, { useEffect, useState } from 'react';
import {
  ActivityIndicator,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import { user_login } from '../components/api/userApi';
import Notification from '../components/basic_cpt/notify';
import Login from '../components/credentials/fields';

export default function App () {
  const global_style = require ('../components/styles/bg-fonts');

  const [isLoading, setIsLoading] = useState (false);

  const [notificationMessage, setNotificationMessage] = useState ('Erreur de connexion');
 
  const [notify, setNotify] = useState (false);

  useEffect (
    () => {
      if (isLoading) {
        setTimeout (() => {
          setIsLoading (false);
          setNotify (true);
        }, 10000);
      }
    },
    [isLoading]
  );

  useEffect (
    () => {
      if (notify) {
        setIsLoading (false);
      } else {
        setNotify (false);
      }
    },
    [notify]
  );

  const handleLogin = (email, password) => {
    if (notify) {
      setNotify (false);
    }
    setIsLoading (true);
    const response = user_login ({
      email: email,
      password: password,
    });
    if (response != undefined) {  
      router.navigate("/user/candidature/candidatures");
    }
    // console.log (response);
  };

  return (
    <>
      <Stack.Screen
        options={{
          title: "Connexion",
          headerStyle: {
              backgroundColor: "rgb(213, 61, 78)",
          },
          headerTintColor: "#fff",
          headerTitleAlign: "center",
        }}
      />
      <ScrollView>
        <View
          style={{
            marginTop: 20,
            alignSelf: 'center',
            alignItems: 'center',
            width: '90%',
          }}
        >
          {notify && <Notification message={notificationMessage} />}
          {isLoading &&
            <ActivityIndicator
              size={'medium'}
              color="rgb(213, 61, 78)"
              style={{zIndex: 2, position: 'absolute', marginTop: 10}}
            />}
        </View>
        <View style={[global_style.container, {marginVertical: 90}]}>
          <Text style={styles.title}>Bienvenue </Text>
          <Login handleLogin={handleLogin} isLoading={isLoading} />
        </View>
        <StatusBar style="auto" />
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create ({
  title: {
    color: 'rgb(213, 61, 78)',
    fontFamily: 'Poppins_400Regular',
    fontSize: 22,
    marginBottom: 5,
  },
});
