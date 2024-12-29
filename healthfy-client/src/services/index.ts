import {
    BaseQueryFn,
    createApi,
    FetchArgs,
    fetchBaseQuery,
    FetchBaseQueryError,
    retry,
  } from '@reduxjs/toolkit/query/react';
import { logOut } from './login/LoginSlice';
import { API_BASE_USER, LocalStorageKeys } from '../config';
  
  const baseQuery =  async (args: string | FetchArgs, api:any, extraOptions:any) => {
      const result: any = await fetchBaseQuery({
        baseUrl: API_BASE_USER,
        prepareHeaders: headers => {
          const token = getToken();
          console.log("TOKEN FROM LOCA: ", token);
          if (token) {
            headers.set('authorization', `Bearer ${token}`);
          }
          return headers;
        },
      })(args, api, extraOptions);
      if (result.error?.status === 401 || result.error?.status === 400) {
        retry.fail(result.error);
      }
      return result;
    };

    const getToken=()=>{
        try{
          const userData = localStorage.getItem(LocalStorageKeys.USER_DATE);
          if (userData) {
            const user = JSON.parse(userData);
            return user.token;
          }
        }catch(e){
          console.log('Error in getting token: ', e);
        }
        return '';
    }
  
  const baseQueryWithReAuth: BaseQueryFn<string | FetchArgs, unknown, FetchBaseQueryError> = async (
    args: any,
    api: any,
    extraOptions: any
  ) => {
    let result = await baseQuery(args, api, extraOptions);
    const resultData: any = result?.error;
    if (result?.error?.status === 401 || resultData?.data?.message?.includes('Unauthorized')) {
      try {
        api.dispatch(logOut());
        window.location.href = '/login';
      } catch (error) {
        console.log('Unexpected Error occurred: ', error);
      }
    } else if (result?.error?.status === 403) {
      console.log('error message', result?.error);
    }
    return result;
  };
  
  export const healthfyApi = createApi({
    reducerPath: 'healthfyApi',
    tagTypes: ['healthfyApi'],
    refetchOnMountOrArgChange: 30,
    baseQuery: baseQueryWithReAuth,
    endpoints: () => ({}),
  });