import AsyncStorage from "@react-native-async-storage/async-storage";

export default function getToken () {
  const token = AsyncStorage.getItem ('token');
  if (token == null) {
    return null;
  } else {
    return token;
  }
}
