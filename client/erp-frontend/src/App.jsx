/* eslint-disable react/jsx-no-undef */
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from 'react-bootstrap/Button';
import { Link, Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import './App.css';
import Application from './pages/Application.jsx';
import Home from "./pages/Home.jsx";
import Login from "./pages/Login.jsx";

function App() {

  return (
    <>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
      crossOrigin="anonymous"
    />
    <div>
      <Router>
          <Routes>
            <Route exact path="/" element={<Login />}/>
            <Route path="/home" element={<Home />}/>
            <Route path="/application" element={<Application />}/>
            <Route path="*" element={<PageNotFound />} />
          </Routes>
          <ToastContainer
            position="top-center"
            autoClose={2000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
          />
        </Router>
      </div>
    </>
  )
}

function PageNotFound() {
  return (
    <div>
      <h2>404 Page not Found</h2>
      <Link to="/">
      <Button variant="outline-primary">Back To Login Page</Button>
      </Link>
    </div>
  );
}

export default App
