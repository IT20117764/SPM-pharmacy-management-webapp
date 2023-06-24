import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import { useParams } from 'react-router-dom';

function RequestQuotation() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const [fileBase64String, setFileBase64String] = useState("");
    // const [pid, setPid] = useState(1);
    const { pid } = useParams();

    //error message
    const[message, setMessage] = useState("");
    const[errClass, setErrClass] = useState("");

    //Encode file
    function encodeFileBase64 (file) {
        var reader = new FileReader();
        if (file) {
          reader.readAsDataURL(file);
          reader.onload = () => {
            var Base64 = reader.result;
            console.log(Base64);
            setFileBase64String(Base64);
          };
          reader.onerror = (error) => {
            console.log("error: ", error);
          };
        }
    };

    function sendQuotation(e){
        e.preventDefault();
        if(fileBase64String === ""){
            alert("Please choose a file");
            return;
        }
        const quotationDto = {
            userId:userId,
            pharmacyId:pid, 
            prescription:fileBase64String,
        }

        const requestDto = {
            userId:userId,
            quotationDto:quotationDto
        } 

        RestService.saveQuotation(token,requestDto).then((res)=>{
            if(res.data.success){
                setErrClass("alert alert-success");
                setMessage("Quotation sent successfully!");
            } else {
                setErrClass("alert alert-danger");
                setMessage("Failed to send the quotation. Please try again.")
            }
        }).catch((err)=>{
            console.log(err);
        });
    }

    return (  
        <div>
             <h1>- REQUEST QUOTATION -</h1>
             <div className="p-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="form-group">
                        <label>Prescription</label>
                        <input type="file" className="form-control" name="file" onChange={(e)=>encodeFileBase64(e.target.files[0])}/>
                    </div>
                    <div className="text-center mb-2">
                        <img src={fileBase64String} style={{width:'50%'}} />
                    </div>

                    <div className={errClass} role="alert">
                         {message}
                    </div>
                    
                    <div className="text-center mb-3 mt-2">
                        <button className="btn btn-primary" style={{borderRadius:'20px'}} onClick={sendQuotation} >Submit</button>
                    </div>
                </form>
            </div>       
        </div>
    );
}

export default RequestQuotation;