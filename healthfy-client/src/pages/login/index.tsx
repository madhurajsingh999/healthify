import React, { useLayoutEffect } from 'react';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { useLoginMutation } from '../../services/login';
import { LoginRequest } from '../../models/login';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { selectUser } from '../../services/login/LoginSlice';

// Define the validation schema using yup
const schema = yup.object().shape({
  username: yup.string().required('Username is required'),
  password: yup.string().min(6, 'Password must be at least 6 characters').required('Password is required'),
});

const Login = () => {
  const loggedInUser=useSelector(selectUser);
  const navigate=useNavigate();
  const[login,{data,isLoading,isError,isSuccess,error}]=useLoginMutation();

  const { control, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = (data: any) => {
    const payload:LoginRequest={username:data.username,password:data.password};
    login({payload:{loginPayload:payload,currentDate:new Date()}});
  };

  useLayoutEffect(() => {
    if(loggedInUser?.isAuthenticated){
      navigate('/dashboard');
    }
    if(isSuccess){
      navigate('/dashboard');
    }
  });

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <form onSubmit={handleSubmit(onSubmit)} className="p-4 bg-white rounded shadow" style={{ width: '50%' }}>
        <h3 className="mb-3">Login</h3>
        {isError && <div className="alert alert-danger">Error</div>}
        <div className="mb-3">
          <Controller
            name="username"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="text"
                className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                placeholder="Username"
              />
            )}
          />
          {errors.username && <div className="invalid-feedback">{errors.username.message}</div>}
        </div>
        <div className="mb-3">
          <Controller
            name="password"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="password"
                className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                placeholder="Password"
              />
            )}
          />
          {errors.password && <div className="invalid-feedback">{errors.password.message}</div>}
        </div>
        <button type="submit" className="btn btn-primary w-100" disabled={isLoading}>Login</button>
      </form>
    </div>
  );
};

export default Login;
