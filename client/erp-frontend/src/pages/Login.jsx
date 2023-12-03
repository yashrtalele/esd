import { useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';

import axios from '../api/axios.js';

const Login = () => {
    const notify = () => toast.success("Log in Success");
    const [form, setForm] = useState({})
    const navigate = new useNavigate();
    const [errors, setErrors] = useState({})
    // const [success, setSuccess] = useState(false);
    const LOGIN_URL = "/api/home/authenticate"
    // eslint-disable-next-line no-unused-vars
    const [authenticated, setauthenticated] = useState(localStorage.getItem(localStorage.getItem("authenticated")|| false));
    const setField = (field, value) => {
        setForm({
            ...form,
            [field]: value
        })

        if(!errors[field])
            setErrors({
                ...errors,
                [field]: null
            })
    }
    const validateForm = () => {
        const { email, password } = form
        const newErrors = {}
        if(!email || email === '') newErrors.email = "Please enter your email"
        else if(!password || password === '') newErrors.password = "Please enter your password"
        return newErrors
    }

    const handleSubmit = async e => {
        e.preventDefault()
        const formErrors = validateForm()
        if(Object.keys(formErrors).length > 0) {
            setErrors(formErrors)
        } else {
            const email = form.email
            const password = form.password
            try {
                const response = await axios.post(
                    LOGIN_URL,
                    JSON.stringify({"email": email, "password": password}),
                    {
                        headers: { "Content-Type": "application/json" },
                    }
                );
                setauthenticated(true)
                localStorage.setItem("authenticated", true);
                notify();
                navigate("/home", {state: response["data"]["data"]});
            } catch (err) {
                if (!err?.response) {
                    console.log("No Server Response");
                } else if (err.response?.status === 409) {
                    console.log("Username Taken");
                } else {
                    console.log("Registration Failed");
                }
            }
        }
    }

    return(
        <>
        <Row>
            <Col >
            <div className="login-form-container">
        <div className="login-form-card ">
            <div className="login-form-content">
            <h3 className="login-form-title">Sign In</h3>
            <Form onSubmit={handleSubmit}>
                <div className="form-div">
                <Form.Group className="form-group mt-3 mb-3" controlId="formGroupEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" value={form.email} onChange={(e)=>setField('email', e.target.value)} isInvalid={!!errors.email}/>
                    <Form.Control.Feedback type='invalid'>{errors.email}</Form.Control.Feedback>
                </Form.Group>
                </div>
                <div className="form-div">
                <Form.Group className="mb-3" controlId="formGroupPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" value={form.password} onChange={(e)=>setField('password', e.target.value)} isInvalid={!!errors.password} />
                    <Form.Control.Feedback type='invalid'>{errors.password}</Form.Control.Feedback>
                </Form.Group>
                </div>
                
                {/* <Link to="/home", state={id}> */}
                <div className="d-grid gap-2 mt-3">
                    <Button variant="primary" type="submit" className="btn btn-primary">
                        Submit
                    </Button>
                </div>
                {/* </Link> */}
            </Form>
            </div>
            </div>
            </div>
            </Col>
            </Row>
        </>
    );
}

export default Login;