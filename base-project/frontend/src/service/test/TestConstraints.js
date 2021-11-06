import user from "../user/user.json";
import location from "../location/location.json";

class TestConstraints {

    userCreateRequest() {
        let userCreateRequest = {...user.create};
        userCreateRequest.firstName = "testFirstName";
        userCreateRequest.lastName = "testLastName";
        userCreateRequest.emailAddress = "test@mail.com";
        userCreateRequest.password = "testPassword";
        userCreateRequest.phoneNumber = "05554441122";
        userCreateRequest.birthDate = "01/01/2001";
        return userCreateRequest;
    }

    userUpdateRequest() {
        let userUpdateRequest = {...user.update};
        userUpdateRequest.userId = 1;
        userUpdateRequest.firstName = "updatedTestFirstName";
        userUpdateRequest.lastName = "updatedTestLastName";
        userUpdateRequest.emailAddress = "updatedTest@mail.com";
        userUpdateRequest.phoneNumber = "05553337799";
        userUpdateRequest.birthDate = "02/02/2002";
        return userUpdateRequest;
    }

    locationCreateRequest() {
        let locationCreateRequest = {...location.create};
        locationCreateRequest.locationName = "testLocationName";
        locationCreateRequest.description = "testDescription";
        locationCreateRequest.city = "testCity";
        locationCreateRequest.state = "testState";
        locationCreateRequest.country = "testCountry";
        locationCreateRequest.county = "testCounty";
        locationCreateRequest.street = "testStreet";
        locationCreateRequest.streetNumber = "1";
        locationCreateRequest.zip = "1";
        return locationCreateRequest;
    }

    locationUpdateRequest() {
        let locationUpdateRequest = {...location.update};
        locationUpdateRequest.locationId = 1;
        locationUpdateRequest.locationName = "updatedTestLocationName";
        locationUpdateRequest.description = "updatedTestDescription";
        locationUpdateRequest.city = "updatedTestCity";
        locationUpdateRequest.state = "updatedTestState";
        locationUpdateRequest.country = "updatedTestCountry";
        locationUpdateRequest.county = "updatedTestCounty";
        locationUpdateRequest.street = "updatedTestStreet";
        locationUpdateRequest.streetNumber = "2";
        locationUpdateRequest.zip = "2";
        return locationUpdateRequest;
    }

}

export default new TestConstraints();