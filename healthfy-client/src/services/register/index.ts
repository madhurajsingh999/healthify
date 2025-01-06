import { healthfyApi } from '..';
import { ENDPOINTS } from '../../config/ApiEndPoints';
import { RegisterRequest } from '../../models/login';

const apiWithTags = healthfyApi.enhanceEndpoints({ addTagTypes: ['SignupInfo'] });

const signupUserInfoApi = apiWithTags.injectEndpoints({
    endpoints: builder => ({
        signupUserInfo: builder.mutation<any, { payload: RegisterRequest }>({
            query: (payload) => ({
                url: ENDPOINTS.SIGNUP_ENDPOINT,
                method: 'POST',
                body: payload.payload
            }),
            invalidatesTags: ['SignupInfo'],
        })
    }),
});

export const {
    useSignupUserInfoMutation,
} = signupUserInfoApi;

export default signupUserInfoApi;