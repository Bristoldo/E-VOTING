import axios from "axios";
import { Link } from "expo-router";
import { useEffect, useState } from "react";
import { FlatList, View } from "react-native";

export default function Details() {
  const global_style = require("./../../../components/styles/bg-fonts");

  const filiere = "PHYSIQUE";

  const niveau = 1;

  const data = {
    filiere: filiere,
    niveau: niveau,
  };

  const [candidatures, setCandidatures] = useState([]);

  //   const config = {
  //     method: "post",
  //     url: "http://127.0.0.1:8080/api/auth/scrutin/filiere-niveau",
  //     data:data,
  //   };

  useEffect(() => {
    const getCandidature = (data) => {
      // fetch("http://127.0.0.1:8080/api/auth/scrutin/filiere-niveau", {
      //   method: "POST",
      //   headers:{
      //     "Content-Type": "application/json",
      //     // "Accept": "application/json",
      //     "Access-Control-Allow-Origin": "*",
      //   },
      //   body: JSON.stringify(data),
      // }).then((result) => {
      //   console.log(result);
      // }).then((res) =>{
        // console.log(res);
      // });
    };
    getCandidature(data);
    axios
      .get("http://127.0.0.1:8080/api/auth/scrutin/filiere-niveau", {
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json", 
        },
        data:JSON.stringify(data),
      })
      .then((result) => {
         console.log(result);
      });
    getCandidature(data);
  });

  return (
    <View style={global_style.container}>
      <FlatList
        data={candidatures}
        renderItem={({ item }) => (
          <Link href={`/candidature/${item.id}`}>candidatures</Link>
        )}
      />
    </View>
  );
}
