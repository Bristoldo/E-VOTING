import { MaterialIcons } from '@expo/vector-icons';
import { StyleSheet, Text, View } from 'react-native';
export default function Notification({message}) {
  return (
    <View style={styles.container}>
      <MaterialIcons name='info' size={18} style={{ color:"rgb(213, 61, 78)", marginBottom:3 }} />
      <Text style={styles.notification}>
        {message}
      </Text>
    </View>
  );
}
const styles = StyleSheet.create ({
  container: {
    gap: 5,
    justifyContent: 'center',
    alignItems: 'center',
    alignContent: 'center',
    flexDirection:'row',
    padding: 20,
    width: '100%',
    borderRadius: 5,
    backgroundColor: 'white',
    flex: 1,
    zIndex: 1,
    position: 'absolute',
  },
  notification: {
    fontFamily: 'Poppins_400Regular',
    fontSize: 16,
    flex:1
}
});
