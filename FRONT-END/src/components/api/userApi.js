import ApiManager from './apiManager';

export const user_login = async data => {
  try {
    const result = await ApiManager ("/candidature", {
      method: "POST",
      headers: {
        'content-type': "application/json",
        "Access-Control-Allow-Origin": "*",      
        "Access-Control-Allow-Headers": "Content-Type, Authorization",      
      },
  data: data,
    });
    return result;
  } catch (error) {
    return error.response.data;
  }
};
