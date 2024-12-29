import { createSlice, Draft, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../store';

export interface UserState {
  id:number|null;
  token: string;
  firstName: string;
  lastName: string;
  email: string | null;
  roles: String[];
  isAuthenticated?: boolean|false;
}

const initialState: UserState = {
  id:null,
  token: '',
  firstName: '',
  lastName: '',
  email: '',
  roles: [''],
  isAuthenticated: false,
} as const;

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setCredentials: (state: Draft<typeof initialState>, action: PayloadAction<any>) => {
      state.id = action.payload.id;
      state.firstName = action.payload.firstName;
      state.lastName = action.payload.lastName;
      state.token = action.payload.token;
      state.email = action.payload.email;
      state.roles = action.payload.roles;
      state.isAuthenticated = true;
    },
    logOut: (state: Draft<typeof initialState>) => {
      state.id = null;
      state.token = '';
      state.email = '';
      state.roles = [''];
      state.firstName = '';
      state.lastName = '';
      state.isAuthenticated = false;
    }
  },
});

// A small helper of user state for `useSelector` function.
export const selectUserState = (state: { userData: UserState }) => state.userData;
export const selectUser = (state: RootState) => (state?.userData ? state.userData : null);

export const {
  setCredentials,
  logOut,
} = userSlice.actions;

export default userSlice.reducer;