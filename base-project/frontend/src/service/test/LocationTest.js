import {Button} from "antd";
import LocationApi from "../location/LocationApi";
import TestConstraints from "./TestConstraints";

const LocationTest = () => {

    const createLocation = () => {
        LocationApi
            .create(TestConstraints.locationCreateRequest())
            .then(response => console.log(response.data))
            .catch(error => console.log(error));
    }
    const getLocation = () => {
        LocationApi
            .read(1)
            .then(response => console.log(response.data))
            .catch(error => console.log(error));
    }
    const updateLocation = () => {
        LocationApi
            .update(TestConstraints.locationUpdateRequest())
            .then(response => console.log(response.data))
            .catch(error => console.log(error));
    }
    const deleteLocation = () => {
        LocationApi
            .delete(1)
            .then(response => console.log(response.data))
            .catch(error => console.log(error));
    }

    return (
        <div>
            <Button onClick={createLocation}>Create Location</Button>
            <Button onClick={getLocation}>Get Location</Button>
            <Button onClick={updateLocation}>Update Location</Button>
            <Button onClick={deleteLocation}>Delete Location</Button>
        </div>
    );

}

export default LocationTest;