import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import { useLocation } from "react-router-dom";

function UpdateMedicine (props) {
    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const location = useLocation();
    const medicine = location.state?.medicine;

    const[medicineName, setMedicineName] = useState(medicine.name);
    const[commonBrands, setCommonBrands] = useState(medicine.brand);
    const[description, setDescription] = useState(medicine.description);
    const [prescription, setPrescription] = useState(medicine.image);

    
   

    useEffect(()=> {
        if(isLogged){
            console.log(medicine)
        } else {
            window.location.href = '/login'; 
        }
    },[])

    function updateMedicine(e){
        e.preventDefault();
        
        if(medicineName === "" || commonBrands === "" || description === "" || prescription === ""){
            alert("Input fields can't be empty.");
            return;
        }

        const medicineDto = {
           id:medicine.id,  
           name:medicineName,
           brand:commonBrands,
           description:description,
           image:prescription
        }

        const requestDto = {
            userId:userId,
            medicineDto:medicineDto
        } 

        RestService.updateMedicine(token,requestDto).then((res)=>{
            if(res.data.success){
                
                alert('Medicine Update Successfully')
                window.location.href = '/view_medicine_list';
            } else {
                alert('Failed to Update Medicine')
            }
        }).catch((err)=>{
            console.log(err);
        });

    }

    //Encode file
    function encodeFileBase64 (file) {
        var reader = new FileReader();
        if (file) {
          reader.readAsDataURL(file);
          reader.onload = () => {
            var Base64 = reader.result;
            setPrescription(Base64);
          };
          reader.onerror = (error) => {
            console.log("error: ", error);
          };
        }
    };


    return ( 
        <div>
            <h1>- UPDATE MEDICINE -</h1>
            <div className="p-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="form-group">
                        <label>Medicine Name</label>
                        <input type="text" className="form-control" value={medicineName} placeholder="Enter medicine name" onChange={(e)=>setMedicineName(e.target.value)}  />
                    </div>
                    
                    <div className="form-group">
                        <label>Common Brand Name</label>
                        <input type="text" className="form-control" value={commonBrands} placeholder="Enter common brand" onChange={(e)=>setCommonBrands(e.target.value)}  />
                    </div>
                    

                    <div className="form-group">
                        <label>Description</label>
                        <textarea class="form-control" placeholder="Enter description" value={description} rows="5" onChange={(e)=>setDescription(e.target.value)}></textarea>
                    </div>

                    <div className="form-group">
                        <label>Image</label>
                        <input type="file" className="form-control" name="file" onChange={(e)=>encodeFileBase64(e.target.files[0])}/>
                    </div>
                    <div className="text-center mb-2">
                        <img src={prescription} style={{width:'50%'}} />
                    </div>


                    <div className="text-center mb-3">
                        <button type="button" class="btn btn-primary" onClick={updateMedicine} >Update</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default UpdateMedicine ;