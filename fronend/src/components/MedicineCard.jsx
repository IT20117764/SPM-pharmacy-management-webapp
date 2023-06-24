import { useState } from "react";
import {Link} from 'react-router-dom';

function MedicineCard(props) {
    const[medicine, setMedicine] = useState(props.medicine);

    return ( 
        <div class="card text-dark bg-light mb-3 "  style={{borderRadius:'25px'}} >
            <div class="card-body" style={{paddingLeft:'10%', paddingRight:'10%'}}>
                <h5 class="card-title">{medicine.name}</h5>
                <p class="card-text">{medicine.description}</p>


                <Link className="btn btn-secondary btn-sm" style={{float:'right',borderRadius:'25px', width:'5vw'}} to={{
                    pathname: '/view_medicine/'+ medicine.id
                    }} >View</Link>

                {/* <button className="btn btn-secondary" style={{float:'right'}}>View</button> */}
                
            </div>
        </div>
     );
}

export default MedicineCard;