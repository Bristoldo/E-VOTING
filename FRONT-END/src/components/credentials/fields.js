import { MaterialIcons } from '@expo/vector-icons';
import { Link } from 'expo-router';
import React, { useState } from 'react';
import {
  Button,
  Keyboard,
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  TouchableWithoutFeedback,
  View
} from 'react-native';

export default function Login ({ isLoading, handleLogin }) {

  const [email, setEmail] = useState ('');

  const [password, setPassword] = useState ('');

  const [visibilite, setVisibilite] = useState (true);

  function changeHandlerEmail (val) {
    setEmail (val);
  }

  function changeHandlerPassword (val) {
    setPassword (val);
  }
  function passwordVilibility () {
    if (visibilite) {
      setVisibilite (false);
    } else {
      setVisibilite (true);
    }
  }

  return (
    <TouchableWithoutFeedback onPress={() => {Keyboard.dismiss()} }>
      <View>
        <TextInput
          style={styles.input1}
          placeholder="Login ..."
          onChangeText={val => changeHandlerEmail (val)}
          // value={email}
        />
        <View style={styles.mdp}>
          <View style={styles.mdpSection}>
            <TextInput
              style={styles.input2}
              secureTextEntry={visibilite}
              placeholder="Mot de passe ..."
              onChangeText={val => changeHandlerPassword (val)}
              id="password"
              // value={password}
            />
            <Pressable onPress={() => passwordVilibility ()}>
              <MaterialIcons
                name="remove-red-eye"
                size={25}
                style={styles.eye}
              />
            </Pressable>
          </View>
          <Link href={'/help'} style={styles.help}>
            <Text style={styles.probText}>Probleme de connexion ? </Text>
          </Link>
        </View>
        <Button
          onPress={() => handleLogin (email, password)}
          title="Se Connecter"
          color="rgb(213, 61, 78)"
        />
      </View>
    </TouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create ({
  input1: {
    paddingHorizontal: 8,
    marginBottom: 10,
    paddingVertical: 6,
    fontFamily: 'Poppins_100Thin',
    borderBottomEndRadius: 5,
    borderBottomStartRadius: 5,
    borderTopStartRadius: 5,
    borderTopEndRadius: 5,
    borderColor: 'gray',
    borderWidth: 1,
  },
  input2: {
    paddingHorizontal: 8,
    paddingVertical: 6,
    fontFamily: 'Poppins_100Thin',
    borderBottomEndRadius: 5,
    borderBottomStartRadius: 5,
    borderTopStartRadius: 5,
    borderTopEndRadius: 5,
    borderColor: 'gray',
    borderWidth: 1,
    paddingRight: 35,
    flex: 1,
  },
  mdpSection: {
    flexDirection: 'row',
  },
  mdp: {
    marginBottom: 10,
  },
  probText: {
    color: 'gray',
    fontFamily: 'Poppins_300Light',
  },
  help: {
    marginBottom: 10,
  },
  eye: {
    margin: '5',
    color: 'gray',
    zIndex: 0,
    position: 'absolute',
    right: 5,
    top: 7,
    justifyContent: 'center',
    alignContent: 'center',
    alignSelf: 'center',
    alignItems: 'center',
  },
});
