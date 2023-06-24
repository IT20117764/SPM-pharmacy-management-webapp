import { useState } from "react";
import {Link} from 'react-router-dom';

function PharmacyCard(props) {

    const[pharmacy, setPharmacy] = useState(props.pharmacy);

    return ( 
        <div class="card text-dark bg-light mb-3 "  style={{borderRadius:'25px'}} >
            <div class="card-body" style={{paddingLeft:'10%', paddingRight:'10%'}}>
                <h5 class="card-title">{pharmacy.pharmacyName}</h5>
                <h6 class="card-title">( {pharmacy.city} )</h6>
                <p class="card-text">{pharmacy.about}</p>


                <Link className="btn btn-secondary btn-sm" style={{float:'right', borderRadius:'25px', width:'5vw'}} to={{
                    pathname: '/pharmacy_profile/'+ pharmacy.pid
                    }} >View</Link>

                {/* <button className="btn btn-secondary" style={{float:'right'}}>View</button> */}
                
            </div>
        </div>
     );
}

export default PharmacyCard;