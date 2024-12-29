import { createContext, useEffect, useState } from "react";
import { UserToken } from "../models/user";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import React from "react";
import axios from "axios";

type AuthContextType = {
  user: UserToken | null;
  token: string | null;
  logout: () => void;
  isLoggedIn: () => boolean;
};

type Props = { children: React.ReactNode };

const AuthContext = createContext<AuthContextType>({} as AuthContextType);

export const UserProvider = ({ children }: Props) => {
  const navigate = useNavigate();
  //const [login, { data, isLoading, isError }] =useLoginMutation();
  const [token, setToken] = useState<string | null>(null);
  const [user, setUser] = useState<UserToken | null>(null);
  const [isReady, setIsReady] = useState(false);

  useEffect(() => {
    const user = localStorage.getItem("user");
    const token = localStorage.getItem("token");
    setIsReady(true);
  }, []);

  const isLoggedIn = () => {
    return !!user;
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    setUser(null);
    setToken("");
    navigate("/");
  };

  return (
    <AuthContext.Provider value={{ user, token, logout, isLoggedIn }}>
      {isReady ? children : null}
    </AuthContext.Provider>
  );
};
export const useAuth = () => React.useContext(AuthContext);