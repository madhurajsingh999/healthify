import { healthfyApi } from '..';
import { logOut, setCredentials } from './LoginSlice';
import { LocalStorageKeys } from '../../config';
import { LoginRequest, LoginResponse } from '../../models/login';
import { ENDPOINTS } from '../../config/ApiEndPoints';


const apiWithTags = healthfyApi.enhanceEndpoints({ addTagTypes: ['UserLogin'] });

const userLoginApi = apiWithTags.injectEndpoints({
  endpoints: builder => ({
    login: builder.mutation<LoginResponse, { payload: { loginPayload: LoginRequest; currentDate: Date } }>({
      query: ({ payload }) => ({
        url: ENDPOINTS.LOGIN_ENDPOINT,
        method: 'POST',
        body: payload?.loginPayload,
      }),
      async onQueryStarted(arg, { dispatch, queryFulfilled }) {
        try {
          const { data } = await queryFulfilled;
          const payload = {
            id: data?.id,
            firstName: data?.firstName,
            lastName: data?.lastName,
            token: data.token,
            roles: data?.roles,
            email: data?.email,
            isAuthenticated: true,
          };
          dispatch(setCredentials(payload));
          localStorage.setItem(LocalStorageKeys.USER_DATE, JSON.stringify(payload));
        } catch (err) {
          console.log(err);
        }
      },
      invalidatesTags: ['UserLogin'],
    }),
    logout: builder.mutation<LoginResponse, { payload: { tokenId: string; token: string } }>({
      query: ({ payload }) => ({
        url: ENDPOINTS.LOGOUT_ENDPOINT,
        method: 'POST',
        headers: {
          Authorization: `Bearer ${payload?.token}`,
        },
        body: payload?.tokenId,
      }),
      async onQueryStarted(arg, { dispatch, queryFulfilled }) {
        try {
          const { data } = await queryFulfilled;
          if (data) {
            localStorage.clear();
            localStorage.removeItem(LocalStorageKeys.USER_DATE);
            dispatch(logOut());
          }
        } catch (err) {
          console.log(err);
        }
      },
    })
  }),
});

export const {
  useLogoutMutation, useLoginMutation
} = userLoginApi;

export default userLoginApi;