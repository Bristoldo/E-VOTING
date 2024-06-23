import { router } from 'expo-router';
import { voter } from '../styles/bg-fonts';

const url = 'http://127.0.0.1:8080/api/auth/candidature/voter';

const headers = {
  'Content-Type': 'application/json',
};

const voterUser = async (idUser, idCandidature) => {
  const data = {
    idUser: idUser,
    idCandidature: idCandidature,
  };
  try {
    const response = await fetch (url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify (data),
    });

    if (!response.ok) {
      // Gérer les erreurs HTTP
      if (response.status === 403) {
        console.log ('Accès refusé');
        return 403;
      } else {
        console.log ('Erreur interne du serveur');
        return 500;
      }
    }

    const responseData = await response.json ();

    // Vérifier la réponse de l'API
    if (responseData.status === 200) {
      // Succès
      return 200;
    } else {
      console.log ("Erreur dans les données reçues de l'API");
      return 400; // Ou une autre valeur d'erreur appropriée
    }
  } catch (error) {
    // Erreur lors de la requête fetch
    console.error (error);
    return 500; // Ou une autre valeur d'erreur appropriée
  }
};

//   const response =  fetch (url, {
//     method: 'POST',
//     headers: headers,
//     body: JSON.stringify (data),
//   })
//     .then (function (res) {
//       if (res.status == 200) {
//         return res.json ();
//       }
//     })
//     .then (function (res) {
//       if (JSON.parse (res) == 200) {
//           return 200;
//         } else {
//           console.log("aaa");
//         return 403;
//       }
//     })
//     .catch (error => {
//       console.log (error);
//       return 500;
//     });
//   return respone;
// }

export default function voterCandidat (idCandidature) {
  global.voter = false;
  //   const user = getToken ();
  //   console.log (user);
  const user = {id: 99};
  if (user != null) {
    const response = voterUser (user.id, idCandidature);
    // if (response == 200) {
        // console.log("asasasas");
    //   router.navigate ('/user/confirme');
    // } else {
        router.push("/user/candidature/candidatures");
        
        router.navigate("user/candidature/confirmer");
// }

  } else {
    
    router.navigate ('/connexion');
  }
}
