import React from 'react';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';

// Define the validation schema using yup
const schema = yup.object().shape({
  name: yup.string().required('Name is required'),
  username: yup.string().required('Username is required'),
  email: yup.string().email('Invalid email format').required('Email is required'),
  password: yup.string().min(6, 'Password must be at least 6 characters').required('Password is required'),
  confirmPassword: yup.string()
    .oneOf([yup.ref('password')], 'Passwords must match')
    .required('Confirm Password is required'),
});

const Register = () => {
  const { control, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = (data: any) => {
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
            name="name"
            control={control}
            defaultValue=""
            render={({ field }) => (
              <input
                {...field}
                type="text"
                className={`form-control ${errors.name ? 'is-invalid' : ''}`}
                placeholder="Name"
              />
            )}
          />
          {errors.name && <div className="invalid-feedback">{errors.name.message}</div>}
        </div>
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
        <button type="submit" className="btn btn-primary w-100">Signup</button>
      </form>
    </div>
  );
};

export default Register;