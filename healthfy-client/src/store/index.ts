import { configureStore } from '@reduxjs/toolkit';

import { useDispatch } from 'react-redux';
import { healthfyApi } from '../services';
import { userSlice } from '../services/login/LoginSlice';

export const store = configureStore({
  reducer: {
    [healthfyApi.reducerPath]: healthfyApi.reducer,
    userData: userSlice.reducer,
  },
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }).concat(healthfyApi.middleware),
  devTools: true,
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export const useAppDispatch = () => useDispatch<AppDispatch>();