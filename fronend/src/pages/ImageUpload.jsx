import { useState } from "react";
import Base64Downloader, { triggerBase64Download } from "react-base64-downloader";

function ImageUpload() {

    const[selectedFile, setSelectedFile] = useState();
    const [fileBase64String, setFileBase64String] = useState("");

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
            <h1>- Image Upload -</h1>
            <input type="file" className="form-control" name="file" onChange={(e)=>encodeFileBase64(e.target.files[0])}/>
            <Base64Downloader className='btn btn-primary' downloadName="my_file_name" base64={fileBase64String}>
                View Pres
            </Base64Downloader>
            <img src={fileBase64String} style={{width:'50%'}} />
        </div>
     );
}

export default ImageUpload;