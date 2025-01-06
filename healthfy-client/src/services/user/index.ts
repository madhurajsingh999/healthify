import { healthfyApi } from '..';
import { ENDPOINTS } from '../../config/ApiEndPoints';

const apiWithTags = healthfyApi.enhanceEndpoints({ addTagTypes: ['UserInfo'] });

const userInfoApi = apiWithTags.injectEndpoints({
  endpoints: builder => ({
    fetchUserInfo: builder.mutation<any,void>({
      query: () => ({
        url: ENDPOINTS.ME_ENDPOINT,
        method: 'GET',
      }),
      invalidatesTags: ['UserInfo'],
    })
  }),
});

export const {
    useFetchUserInfoMutation,
} = userInfoApi;

export default userInfoApi;