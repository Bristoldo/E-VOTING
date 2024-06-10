'use strict';
import { StyleSheet } from 'react-native';

module.exports = StyleSheet.create ({
  container: {
    marginHorizontal: 20,
    marginVertical: 30,
    fontFamily: 'Poppins_400Regular',
    backgroundColor: 'white',
    flex:1,
    padding: 20,
    borderRadius: 5,
  },
  loading: {
    alignItems: 'center',
    backgroundColor: 'gainsboro',
    paddingVertical: 50
  },
  p_regular:{
    fontFamily: 'Poppins_400Regular',
    color: 'rgb(68, 68, 68)',
    fontSize: 16
  }
});
