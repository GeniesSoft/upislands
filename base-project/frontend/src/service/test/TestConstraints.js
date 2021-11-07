import user from "../user/user.json";
import location from "../location/location.json";
import company from "../company/company.json";

class TestConstraints {

    userCreateRequest() {
        let userCreateRequest = user.create;
        userCreateRequest.firstName = "testFirstName";
        userCreateRequest.lastName = "testLastName";
        userCreateRequest.emailAddress = "test@mail.com";
        userCreateRequest.password = "testPassword";
        userCreateRequest.phoneNumber = "05554441122";
        userCreateRequest.birthDate = "01/01/2001";
        return userCreateRequest;
    }

    userUpdateRequest() {
        let userUpdateRequest = user.update;
        userUpdateRequest.userId = 1;
        userUpdateRequest.firstName = "updatedTestFirstName";
        userUpdateRequest.lastName = "updatedTestLastName";
        userUpdateRequest.emailAddress = "updatedTest@mail.com";
        userUpdateRequest.phoneNumber = "05553337799";
        userUpdateRequest.birthDate = "02/02/2002";
        return userUpdateRequest;
    }

    locationCreateRequest() {
        let locationCreateRequest = location.create;
        locationCreateRequest.locationName = "testLocationName";
        locationCreateRequest.description = "testDescription";
        locationCreateRequest.city = "testCity";
        locationCreateRequest.state = "testState";
        locationCreateRequest.country = "testCountry";
        locationCreateRequest.county = "testCounty";
        locationCreateRequest.street = "testStreet";
        locationCreateRequest.streetNumber = 1;
        locationCreateRequest.zip = 1;
        return locationCreateRequest;
    }

    locationUpdateRequest() {
        let locationUpdateRequest = location.update;
        locationUpdateRequest.locationId = 1;
        locationUpdateRequest.locationName = "updatedTestLocationName";
        locationUpdateRequest.description = "updatedTestDescription";
        locationUpdateRequest.city = "updatedTestCity";
        locationUpdateRequest.state = "updatedTestState";
        locationUpdateRequest.country = "updatedTestCountry";
        locationUpdateRequest.county = "updatedTestCounty";
        locationUpdateRequest.street = "updatedTestStreet";
        locationUpdateRequest.streetNumber = 2;
        locationUpdateRequest.zip = 2;
        return locationUpdateRequest;
    }

    companyCreateRequest() {
        let companyCreateRequest = company.create;
        companyCreateRequest.userId = 1;
        companyCreateRequest.locationIdList = [1];
        companyCreateRequest.companyName = "testName";
        companyCreateRequest.companyDescription = "testDescription";
        companyCreateRequest.city = "testCity";
        companyCreateRequest.state = "testState";
        companyCreateRequest.country = "testCountry";
        companyCreateRequest.county = "testCounty";
        companyCreateRequest.street = "testStreet";
        companyCreateRequest.streetNumber = 1;
        companyCreateRequest.zip = 1;
        return companyCreateRequest;
    }

    companyUpdateRequest() {
        let companyUpdateRequest = company.update;
        companyUpdateRequest.companyId = 1;
        companyUpdateRequest.locationIdList = [1];
        companyUpdateRequest.companyName = "updatedTestyName";
        companyUpdateRequest.companyDescription = "updatedTestDescription";
        companyUpdateRequest.city = "updatedTestCity";
        companyUpdateRequest.state = "updatedTestState";
        companyUpdateRequest.country = "updatedTestCountry";
        companyUpdateRequest.county = "updatedTestCounty";
        companyUpdateRequest.street = "updatedTestStreet";
        companyUpdateRequest.streetNumber = 2;
        companyUpdateRequest.zip = 2;
        return companyUpdateRequest;
    }

}

export default new TestConstraints();