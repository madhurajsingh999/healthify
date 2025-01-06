export type LoginRequest = {
  username: string;
  password: string;
};

export type LoginResponse = {
  id: number;
  token: string;
  firstName: string;
  lastName: string;
  email: string;
  roles: string[];
  status: number;
};

export type RegisterRequest = {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  gender: string;
  dateOfBirth: string;
  phoneNumber: string;
};