import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import { selectUser } from "../services/login/LoginSlice";
import { useSelector } from "react-redux";

type Props = { children: React.ReactNode };

const ProtectedRoute = ({ children }: Props) => {
  const location = useLocation();
  const userObj = useSelector(selectUser);
  return userObj?.isAuthenticated ? (
    <>{children}</>
  ) : (
    <Navigate to="/login" state={{ from: location }} replace />
  );
};

export default ProtectedRoute;
