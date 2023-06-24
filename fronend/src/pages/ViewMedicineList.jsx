import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import {Link} from 'react-router-dom';
import autoTable from 'jspdf-autotable';
import jsPDF from "jspdf";

function ViewMedicineList() {
    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));


    const[itemList, setItemList] = useState([]);
    const[type, setType] = useState(0);
    const[tabletList, setTabletList] = useState([]);
    const[syrupsList, setSyrupsList] = useState([]);
    const[otherItemList, setOtherItemList] = useState([]);

    const[selectedItem, setSelectedItem] = useState([]);


    const[medicines, setMedicines] = useState([]);
    const[filteredList, setFilteredList] = useState([])

    useEffect(()=> {

        if(isLogged){

            const requestDto = {
                userId:userId
            } 

            RestService.getMedicineList(token,requestDto).then((res)=>{
                setMedicines(res.data.medicineDtoList);
                setFilteredList(res.data.medicineDtoList);
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])

    

    function deleteMedicineById(id){
        const requestDto = {
            medicineId:id
        }
        RestService.deleteMedicine(token,requestDto).then((res)=>{
            if(res.data.success == true){
                alert('Medicine deleted successfully')
                window.location.reload(false);
            } else {
                alert('failed to delete the data')
            }
        }).catch((err)=>{
            console.log(err);
        })
    }

    function search(searchVal) {

        let temp = [];

        if(searchVal != ""){
            for(const m of medicines) {
                if(m.name.toLowerCase().startsWith(searchVal.toLowerCase())){
                    temp.push(m);
                }
            }
    
            setFilteredList(temp);
        } else {
            setFilteredList(medicines);
        }
       
    }

    function generateReport() {
        const doc = new jsPDF()

        doc.text("System Medicines", 15, 10);
        
        autoTable(doc, { html: '#medicine-tbl' })

        doc.save('Medicines.pdf')
    }




    return ( 
        <div>
            <h1>- MEDICINES -</h1>
            <div className="p-5">
                <div className="row">
                    <div className="col">
                        <input type="text" class="form-control mt-3"  style={{borderRadius:'25px',width:'50%', margin:'auto',float:'left'}} id="search"  placeholder="Search" onChange={(e)=> search(e.target.value)} />          
                        
                    </div>
                    <div className="col">
                        <div style={{float:'right'}}>
                        <button className="btn btn-primary mt-3 mr-2" style={{borderRadius:'25px', width:'5vw'}} onClick={(e)=>{window.location.href = '/add_medicine'}} >Add</button>
                        </div>                  
                    </div>
                </div>
                <button className="btn btn-secondary mt-3" style={{borderRadius:'25px', width:'5vw'}} onClick={generateReport} >Report</button> 
                <table className="table table-hover table-light mb-5 mt-3" id='medicine-tbl'>
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            filteredList.map(
                                medicine =>
                                <tr key={medicine.itemId}>
                                    <td>{medicine.id}</td> 
                                    <td>{medicine.name}</td>        
                                    <td className="text-center">
                                        <Link className="btn btn-secondary btn-sm mr-2" to={{
                                        pathname: '/view_medicine/'+ medicine.id
                                        }} >View</Link>
                                        <Link className="btn btn-primary btn-sm mr-2" state={{medicine:medicine}} to={{
                                            pathname: '/update_medicine/'+ medicine.id
                                            
                                            }} >Update</Link>

                                        <button type="button" className="btn btn-danger btn-sm"  onClick={(e)=>{deleteMedicineById(medicine.id)}} >Delete</button>  
                                        
                                    </td> 
                                </tr>
                            )                                             
                        }
                    </tbody>
                </table>





            </div>

        </div>
     );
}

export default ViewMedicineList;