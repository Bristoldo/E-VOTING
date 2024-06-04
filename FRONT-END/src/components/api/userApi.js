import ApiManager from './apiManager';

export const user_login = async data => {
  try {
    console.log(data);
    const result = await ApiManager ("/authenticate", {
      method: "POST",
        headers: {
          "Context-Type": "application/json",
          "Accept": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
  data: data,
    });
    return result;
  } catch (error) {
    return error;
  }
};
