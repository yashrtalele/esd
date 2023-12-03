import { useEffect, useState } from "react";
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table';
import { Navigate, useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "../api/axios";

// eslint-disable-next-line no-unused-vars
const Home = (props) => {
    const [authenticated] = useState(localStorage.getItem("authenticated"));
    const [myData, setMyData] = useState([]);
    const [placement, setMyPlacement] = useState([]);
    const [grades, setGrades] = useState([]);
    const navigate = new useNavigate();
    const location = useLocation();
    const propsData = location.state;
    useEffect(() => {
        window.scrollTo(0, 0);
    }, [location]);
    useEffect(() => {
        const OFFERS_URL = "/aux/get/"+propsData["studentId"]+"/offers";
        axios
        .get(OFFERS_URL)
        .then((res) => {setMyData(res.data)})
        .catch((error) => console.log(error));
        // console.log("👽" + myData.length);
        axios.get("aux/get/"+propsData["studentId"]+"/placements")
                .then((res) => setMyPlacement(res.data))
                .catch((error) => console.log(error));

        axios.get("aux/get/"+propsData["studentId"]+"/grades")
        .then((res) => setGrades(res.data))
        .catch((error) => console.log(error));
                // console.log("🚀 🚀 🚀" + placement.length);
    }, []);
    const handleOnClick = (data) => {
        console.log(data);
        let flag=0;
        let found=false;
        placement.forEach((e) => {
            if(e["placement"]["id"] === data["id"]) {
                found = true;
                console.log(e["acceptance"]);
                if(e["acceptance"]==='PENDING') {
                    toast.warning("Already Applied! Please wait for company's response");
                }
                else if(e["acceptance"]==='ACCEPTED') {
                    toast.success("Already Accepted!");
                }
                else if(e["acceptance"]==='REJECTED') {
                    toast.error("Company rejected you!")
                }
            } else{
                found=false
                flag=flag+1;
            }
        })
        // console.log(found + " " + flag + " " + placement.length + " " + data["id"] + " ")
        if(found===false && flag === placement.length) {
            navigate("/application", {state: {'placementData': data,
            'personalData': propsData}})
        }
    }

    if(!authenticated) {
        return <Navigate replace to="/" />;
    } else {
    return(
        <>
        <main>
        <div className="container containerb">
            <h1 className="pt-5">Welcome, {propsData["firstName"]} {propsData["lastName"]}</h1>
        </div>
        {
            myData.length ? (
                <div>
                <Table striped hover responsive className="table-bootstrap">
                    <thead>
                        <tr>
                        <th>#</th>
                        <th>Company Name</th>
                        <th>Domain</th>
                        <th>Specialization</th>
                        <th>Minimum Grade</th>
                        <th>Your Grade</th>
                        <th>Apply</th>
                        </tr>
                    </thead>
                    <tbody>
                {
                    myData.map((data) => {
                        console.log(data);
                            return (<tr key={data["id"]}>
                                <td>{data["id"]}</td>
                                <td>{data["placement"]["organization"]["name"]}</td>
                                <td>{data["domain"]["program"]}</td>
                                <td>{data["specialization"]["name"]}</td>
                                <td>{data["placement"]["minimumGrade"]}</td>
                                <td>{grades.toFixed(1)}</td>
                                <td>
                                    {/* <Link to="/application" state={{'placementData': data,
                                        'personalData': propsData,
                                    }}> */}
                                        <Button onClick={() => handleOnClick(data)}>Apply</Button>
                                    {/* </Link> */}
                                </td>
                            </tr>);
                        })
                            
                }
            </tbody>
        </Table>
        </div>
            ) : (
                <div>
                    <h3>No data found!</h3>
                </div>
            )
        }
        </main>
        </>
    );
            }
}

export default Home;