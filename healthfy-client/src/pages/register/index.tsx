import React, { use, useEffect } from 'react';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { useSignupUserInfoMutation } from '../../services/register';
import { RegisterRequest } from '../../models/login';

// Define the validation schema using yup
const schema = yup.object().shape({
  firstName: yup.string().required('Name is required'),
  lastName: yup.string().required('Username is required'),
  email: yup.string().email('Invalid email format').required('Email is required'),
  password: yup.string().min(6, 'Password must be at least 6 characters').required('Password is required'),
  confirmPassword: yup.string()
    .oneOf([yup.ref('password')], 'Passwords must match')
    .required('Confirm Password is required'),
  gender: yup.string().required('Name is required'),
  dateOfBirth: yup.string().required('Name is required'),
  phoneNumber: yup.string(),
},);

const Register = () => {
  const [signupUserInfo, { data, isLoading, isError, isSuccess, error }] = useSignupUserInfoMutation();
  const { control, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  useEffect(() => { // Handle the signup response here

    if (!isLoading) {
      console.log('Signing up...');
      if (data && isSuccess) {
        console.log('Signup successful:', data);
      }
      if (isError) {
        console.error('Signup failed:', error);
      }
    }

  }, [data, isError, error]);

  const onSubmit = (data: any) => {

    const payload: RegisterRequest = { firstName: data.firstName, lastName: data.lastName, email: data.email, password: data.password, gender: data.gender, dateOfBirth: data.dateOfBirth, phoneNumber: data.phoneNumber };
    signupUserInfo({ payload });
    console.log(data);
    // Handle signup logic here
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="p-4 bg-white rounded shadow"
        style={{ width: '50%' }} // Set the form width to 50% of the container
      >
        <h3 className="mb-3">Signup</h3>
        <div className="mb-3">
          <Controller
            name="firstName"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="text"
                className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                placeholder="Name"
              />
            )}
          />
          {errors.firstName && <div className="invalid-feedback">{errors.firstName.message}</div>}
        </div>
        <div className="mb-3">
          <Controller
            name="lastName"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="text"
                className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                placeholder="Last Name"
              />
            )}
          />
          {errors.lastName && <div className="invalid-feedback">{errors.lastName.message}</div>}
        </div>
        <div className="mb-3">
          <Controller
            name="email"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="email"
                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                placeholder="Email"
              />
            )}
          />
          {errors.email && <div className="invalid-feedback">{errors.email.message}</div>}
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
        <div className="mb-3">
          <Controller
            name="confirmPassword"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="password"
                className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`}
                placeholder="Confirm Password"
              />
            )}
          />
          {errors.confirmPassword && <div className="invalid-feedback">{errors.confirmPassword.message}</div>}
        </div>
        <div className="mb-3">
          <Controller
            name="gender"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="select"
                className={`form-control ${errors.gender ? 'is-invalid' : ''}`}
                placeholder="Gender"
              />
            )}
          />
          {errors.gender && <div className="invalid-feedback">{errors.gender.message}</div>}
        </div>
        <div className="mb-3">
          <Controller
            name="dateOfBirth"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="text"
                className={`form-control ${errors.dateOfBirth ? 'is-invalid' : ''}`}
                placeholder="Date Of Birth"
              />
            )}
          />
          {errors.dateOfBirth && <div className="invalid-feedback">{errors.dateOfBirth.message}</div>}
        </div>
        <div className="mb-3">
          <Controller
            name="phoneNumber"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="text"
                className={`form-control ${errors.phoneNumber ? 'is-invalid' : ''}`}
                placeholder="Phone NUmber"
              />
            )}
          />
          {errors.phoneNumber && <div className="invalid-feedback">{errors.phoneNumber.message}</div>}
        </div>
        <button type="submit" className="btn btn-primary w-100">Signup</button>
      </form>
    </div>
  );
};

export default Register;