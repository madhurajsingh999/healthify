import React from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { BsBoxArrowInDownRight, BsBoxArrowInRight, BsFillPersonFill, BsPersonAdd } from "react-icons/bs";
import NavDropdown from 'react-bootstrap/NavDropdown';
import { useSelector } from "react-redux";
import { logOut, selectUser } from "../../services/login/LoginSlice";
import { useAppDispatch } from "../../store";

interface Props {}

const AppNavbar = (props: Props) => {
  const dispach=useAppDispatch();
  const userObj = useSelector(selectUser);
  return (
    <>
    <Navbar collapseOnSelect expand="lg" bg="primary" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/home">Healthify</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#features">Features</Nav.Link>
            <Nav.Link href="#pricing">Pricing</Nav.Link>
            <NavDropdown title="Dropdown" id="collapsible-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Another action
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">
                Separated link
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
          {userObj?.isAuthenticated ? 
          (
            <>
            <NavDropdown title="Dropdown" id="collapsible-nav-dropdown">
              <NavDropdown.Item href="/profile">
              <BsFillPersonFill /> {userObj?.firstName} {userObj?.lastName}
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item onClick={()=>dispach(logOut())}>
               <BsBoxArrowInDownRight/> Logout
              </NavDropdown.Item>
            </NavDropdown>
            </>
        )
          :(
            <>
          <Nav.Link href="/login"><BsBoxArrowInRight/> Login</Nav.Link>
          <Nav.Link href="/register"><BsPersonAdd/> Signup</Nav.Link>
          </>
        )
          }
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
    </>
  );
};

export default AppNavbar;
