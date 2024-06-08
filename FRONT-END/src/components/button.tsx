import React from "react";
import { StyleSheet, Text, View } from "react-native";

export default function Button1({ title, disabled }) {
  // const

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  title: {
    textTransform: "uppercase",
    textAlign: "center",
    fontWeight: "500",
    color: "#fff",
  },
  container: {
    backgroundColor: "#d9d",
    justifyContent: "center",
    alignContent: "center",
    borderRadius: 10,
    padding: 8,
  },
});
