import { Outlet } from "react-router";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import { UserProvider } from "./context";
import AppNavbar from "./components/navbar/Navbar";
import Container from "react-bootstrap/esm/Container";
import { useLayoutEffect } from "react";
import { LocalStorageKeys } from "./config";
import { setCredentials } from "./services/login/LoginSlice";
import { useAppDispatch } from "./store";

const App=()=> {
const dispach=useAppDispatch();
  useLayoutEffect(() => {
    const userData=localStorage.getItem(LocalStorageKeys.USER_DATE);
    if(userData){
      const user=JSON.parse(userData);
      dispach(setCredentials(user));
    }
  }, [dispach]);

  return (
    <Container fluid>
      <UserProvider>
        <AppNavbar />
        <Container className="pt-2">
        <Outlet />
        </Container>
        <ToastContainer />
      </UserProvider>
    </Container>
  );
}

export default App;
