import React, { useState } from 'react';
import { Button, StyleSheet, TextInput, View } from 'react-native';
import { user_login } from '../api/userApi';

export default function Login () {
  const [email, setEmail] = useState ('');
  const [password, setPassword] = useState ('');


  const handleLogin = () => {
    user_login ({
      email: email,
      password: password,
    })
      .then (result => {
        if (result.status == 200) {
          console.log ('AccessToken', result.data.token);
        } else {
          console.log ('error');
        }
      })
      .catch (err => {
        console.log(err);
      });
  };

  changeHandlerEmail = val => {
    setEmail (val);
  };

  changeHandlerPassword = val => {
    setPassword (val);
  };

  return (
    <View>
      <TextInput
        style={styles.input}
        placeholder="email ..."
        onChangeText={val => changeHandlerEmail (val)}
        // value={email}
      />
      <TextInput
        style={styles.input}
        secureTextEntry={true}
        placeholder="password ..."
        onChangeText={val => changeHandlerPassword (val)}
        // value={password}
      />
      <Button
        onPress={() => handleLogin()}
        title="Connexion"
        color="coral"
      />
    </View>
  );
}

const styles = StyleSheet.create ({
  input: {
    marginBottom: 10,
    paddingHorizontal: 8,
    paddingVertical: 6,
    borderBottomWidth: 1,
    borderBottomColor: '#ddd',
  },
});
