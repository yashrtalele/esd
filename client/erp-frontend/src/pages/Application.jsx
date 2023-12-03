import { useState } from "react";
import { Container, Row } from 'react-bootstrap';
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "../api/axios";

// eslint-disable-next-line no-unused-vars
const Application = (props) => {
    const [myData, setMyData] = useState([]);
    const navigate = new useNavigate();
    const location = useLocation();
    const propsData = location.state;
    console.log(propsData);
    const imagePath='../src/assets'+propsData["personalData"]["photographPath"];

    const [file, setFile] = useState(null);

    const handleFileChange = (e) => {
        if (e.target.files) {
            console.log(e.target.files[0])
            setFile(e.target.files[0]);
        }
    };

    const handleUpload = async (e) => {
        e.preventDefault();
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = yyyy + '-' + mm + '-' + dd;
        let value = URL.createObjectURL(file);
        const link = document.createElement('a');
        link.href = value;
        let r = (Math.random() + 1).toString(36).substring(7);
        let fileName = r+'.pdf';
        link.setAttribute(
            'download',
            fileName,
        );
        document.body.appendChild(link);
        link.click();
        link.parentNode.removeChild(link);
        const formData = new FormData();
        formData.append('cv_application', 'file:///home/yash/Downloads/'+fileName);
        formData.append('about', propsData["placementData"]["placement"]["organization"]["name"]);
        formData.append('comments', 'None');
        formData.append('date', today);
        formData.append('placement', propsData["placementData"]["id"]);
        formData.append('student', propsData["personalData"]["studentId"]);
        let file_name= new String('file:///home/yash/Downloads/'+fileName);
        
        console.log(formData);
        const userData = {
            cvApplication: file_name,
            about: propsData["placementData"]["placement"]["organization"]["name"],
            comments: 'None',
            date: today.toString(),
            placement: propsData["placementData"]["id"],
            student: propsData["personalData"]["studentId"],
        };
        axios.post("aux/add/application", userData).then((response) => {
            if(response.status===408) {
                toast.warning("Failed to apply! Please try again");
            } else {
                toast.success("Applied Successfully!");
                axios.get("aux/get/"+propsData["personalData"]["studentId"]+"/placements")
                .then((res) => setMyData(res.data))
                .catch((error) => console.log(error));
                console.log(myData);
                navigate(-1);
            }
        });
    };

    return(
        <>
        <h3 className='mt-5'>Application for {propsData["placementData"]["placement"]["organization"]["name"]}</h3>
        <Container>
        <Row>
            <div className="card">
                <div className="card-body card-body-custom">
                <div className="mb-4 mb-xl-0 col-xl-4">
                    <div className="card-body text-center">
                        <img className="img-account-profile rounded-circle mb-2 w-50" src={imagePath} alt="Img Not available" />
                    </div>
                </div>
                <div className="card-details">
                <h5 className="card-title">{propsData["personalData"]["firstName"]} {propsData["personalData"]["lastName"]}</h5>
                    <p className="card-text card-text-custom">Email: {propsData["personalData"]["email"]}</p>
                    <p className="card-text card-text-custom">Specialization: {propsData["personalData"]["specialization"]["name"]}</p>
                    <p className="card-text card-text-custom">Domain: {propsData["personalData"]["domain"]["program"]}</p>
                    <p className="card-text card-text-custom">Current GPA: {propsData["personalData"]["cgpa"]}</p>
                    <div className="m-3">
                    <label className="mx-3">Upload CV: </label>
                    <input className="" type="file" onChange={handleFileChange}/>
                    </div>
                </div>
                </div>
            </div>
        </Row>
        <Row className='mt-5'>
        <div className="col-md-14">
            <button className="m-5 btn btn-primary" type="submit" onClick={handleUpload}>Upload & Apply</button>
        </div>
        </Row>
        </Container>
        </>
    );
}

export default Application;