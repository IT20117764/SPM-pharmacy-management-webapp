import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import {Link} from 'react-router-dom';
import autoTable from 'jspdf-autotable';
import jsPDF from "jspdf";

function Inventory() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));


    const[btnType, setBtnType] = useState("Tablets");

    const[itemList, setItemList] = useState([]);
    const[type, setType] = useState(0);
    const[tabletList, setTabletList] = useState([]);
    const[syrupsList, setSyrupsList] = useState([]);
    const[otherItemList, setOtherItemList] = useState([]);

    const[selectedItem, setSelectedItem] = useState([]);


    

    const[searchItems, setSearchItems] = useState([]);

    useEffect(()=> {

        if(isLogged){

            const requestDto = {
                userId:userId
            } 

            RestService.findPharmacyInventoryById(token,requestDto).then((res)=>{
                // setItemList(res.data.itemDtoList);
                filterLists(res.data.itemDtoList);
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])

    function filterLists(inventory){

        let tempTablets = [];
        let tempSyrups = [];
        let tempOthers = [];

        for(const i of inventory){
            if(i.type == 0){
                tempTablets.push(i);
            } else if(i.type == 1){
                tempSyrups.push(i);
            } else if(i.type == 2){
                tempOthers.push(i);
            }
        }

        setTabletList(tempTablets);
        setSyrupsList(tempSyrups);
        setOtherItemList(tempOthers);
        setItemList(tempTablets);

    }


    function deleteItemById(id){
        const requestDto = {
            itemId:id
        }
        RestService.deleteItemById(token,requestDto).then((res)=>{
            if(res.data.success == true){
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
            for(const i of itemList) {
                if(i.itemName.toLowerCase().startsWith(searchVal.toLowerCase())){
                    temp.push(i);
                }
            }
    
            setItemList(temp);
        } else {
            if(type == 0){
                setItemList(tabletList);
            } else if(type == 1){
                setItemList(syrupsList);
            } else if(type == 3){
                setItemList(otherItemList);
            }
        }
       
    }

    function generateReport() {
        const doc = new jsPDF()

        doc.text("Inventory Report", 15, 10);
        
        autoTable(doc, { html: '#item-tbl' })

        doc.save('inventory.pdf')
    }



    return ( 
        <div>
            <h1>- INVENTORY -</h1>
            <div className="p-5">
                <div className="row">
                    <div className="col">
                        <input type="text" class="form-control mt-3"  style={{borderRadius:'25px',width:'50%', margin:'auto',float:'left'}} id="search"  placeholder="Search" onChange={(e)=> search(e.target.value)} />
                        <button type="button" className="ml-3 btn-light mt-3 dropdown-toggle p-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style={{borderStyle:'none', width:'10vw',borderRadius:'25px'}}>
                            {btnType}
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item " href="#" style={{width:'100%'}} onClick={(e)=>{setItemList(tabletList); setBtnType("Tablets"); setType(0)}} >Tablets</a>
                            <a class="dropdown-item " href="#" onClick={(e)=>{setItemList(syrupsList); setBtnType("Syrups"); setType(1)}}>Syrups</a>
                            <a class="dropdown-item" href="#" onClick={(e)=>{setItemList(otherItemList); setBtnType("Other"); setType(2)}}>Other</a>
                        </div>
                    </div>
                    <div className="col">
                        <div style={{float:'right'}}>
                        <button className="btn btn-primary mt-3 mr-2" style={{borderRadius:'25px', width:'5vw'}} onClick={(e)=>{window.location.href = '/add_inventory_item'}} >Add</button>
                        <button className="btn btn-secondary mt-3" style={{borderRadius:'25px', width:'5vw'}} onClick={generateReport} >Report</button>
                        </div>
                        
                    </div>
                </div>

                <table className="table table-hover table-light mb-5 mt-3" id='item-tbl'>
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Item Name</th>
                            <th scope="col">Qty</th>
                            <th scope="col">Price Per Item</th>
                            <th scope="col">Description</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            itemList.map(
                                item =>
                                <tr key={item.itemId}>
                                    <td>{item.itemId}</td> 
                                    <td>{item.itemName}</td>        
                                    <td>{item.qty}</td> 
                                    <td>{item.pricePerItem}</td>
                                    <td>{item.description}</td> 
                                    <td className="text-center">
                                        <Link className="btn btn-primary btn-sm mr-2" state={{item:item}} to={{
                                            pathname: '/update_inventory_item/'+ item.itemId
                                            
                                            }} >Update</Link>

                                        <button type="button" className="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteItem" onClick={(e)=>{setSelectedItem(item)}} >Delete</button>  
                                        
                                    </td> 
                                </tr>
                            )                                             
                        }
                    </tbody>
                </table>





            </div>


            {/* Delete modal */}                  
            <div class="modal fade" id="deleteItem" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content ">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Delete User</h5>
                    </div>
                    <div class="modal-body">
                        <h6>Do you want to delete ' <span style={{color: "red"}}>{selectedItem.itemName}</span> ' ?</h6>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger btn-sm" onClick={(e)=>{deleteItemById(selectedItem.itemId)}}>Delete</button>
                    </div>
                    </div>
                </div>
            </div>

        </div>
     );
}

export default Inventory;