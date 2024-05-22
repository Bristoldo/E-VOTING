import ApiManager from './apiManager';

export const user_login = async data => {
  try {
    console.log(data);
    const result = await ApiManager ("/authenticate", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      data: data,
    });
    return result;
  } catch (error) {
    console.log(data);

    console.log(error);
  }
};
