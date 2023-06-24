import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";

function AddMedicine() {


    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    //error message
    const[message, setMessage] = useState("");
    const[errClass, setErrClass] = useState("");

    const[medicineName, setMedicineName] = useState("");
    const[commonBrands, setCommonBrands] = useState("");
    const[description, setDescription] = useState("");
    const [fileBase64String, setFileBase64String] = useState("");
    


    useEffect(()=> {
        if(isLogged){

        } else {
            window.location.href = '/login'; 
        }
    },[])

    function addMedicine(e){
        e.preventDefault();

        if(medicineName === "" || commonBrands === "" || description === "" || fileBase64String === ""){
            alert("Input fields can't be empty.");
            return;
        }
        

        const medicineDto = { 
           name:medicineName,
           brand:commonBrands,
           description:description,
           image:fileBase64String
        }

        const requestDto = {
            userId:userId,
            medicineDto:medicineDto
        } 

        RestService.addMedicine(token,requestDto).then((res)=>{
            if(res.data.success){

                setMedicineName('');
                setCommonBrands('');
                setDescription('');
                setFileBase64String('');

                setErrClass("alert alert-success");
                setMessage("Item added successfully!");
            } else {
                setErrClass("alert alert-danger");
                setMessage("Failed to add the item. Please try again.")
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
            console.log(Base64);
            setFileBase64String(Base64);
          };
          reader.onerror = (error) => {
            console.log("error: ", error);
          };
        }
    };


    return ( 
        <div>
            <h1>- ADD MEDICINE -</h1>
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
                        <img src={fileBase64String} style={{width:'50%'}} />
                    </div>

                    <div className={errClass} role="alert">
                         {message}
                    </div>

                    <div className="text-center mb-3">
                        <button type="button" class="btn btn-primary" onClick={addMedicine} >Add Medicine</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default AddMedicine;