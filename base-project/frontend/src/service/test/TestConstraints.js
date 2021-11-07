import user from "../data/user.json";
import location from "../data/location.json";
import company from "../data/company.json";
import address from "../data/address.json";

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

    addressCreate() {
        let addressCreate = address;
        addressCreate.city = "testCity";
        addressCreate.state = "testState";
        addressCreate.country = "testCountry";
        addressCreate.county = "testCounty";
        addressCreate.street = "testStreet";
        addressCreate.streetNumber = 1;
        addressCreate.zip = 1;
        return addressCreate;
    }

    addressUpdate() {
        let addressUpdate = address;
        addressUpdate.city = "updatedTestCity";
        addressUpdate.state = "updatedTestState";
        addressUpdate.country = "updatedTestCountry";
        addressUpdate.county = "updatedTestCounty";
        addressUpdate.street = "updatedTestStreet";
        addressUpdate.streetNumber = 2;
        addressUpdate.zip = 2;
        return addressUpdate;
    }

    locationCreateRequest() {
        let locationCreateRequest = location.create;
        locationCreateRequest.locationName = "testLocationName";
        locationCreateRequest.description = "testDescription";
        locationCreateRequest.tripTime = "testTripTime";
        locationCreateRequest.needExperience = false;
        locationCreateRequest.address = this.addressCreate();

        return locationCreateRequest;
    }

    locationUpdateRequest() {
        let locationUpdateRequest = location.update;
        locationUpdateRequest.locationId = 1;
        locationUpdateRequest.tripTime = "updatedTestTripTime";
        locationUpdateRequest.needExperience = false;
        locationUpdateRequest.locationName = "updatedTestLocationName";
        locationUpdateRequest.description = "updatedTestDescription";
        locationUpdateRequest.address = this.addressUpdate();

        return locationUpdateRequest;
    }

    companyCreateRequest() {
        let companyCreateRequest = company.create;
        companyCreateRequest.userId = 1;
        companyCreateRequest.locationIdList = [1];
        companyCreateRequest.companyName = "testName";
        companyCreateRequest.companyDescription = "testDescription";
        companyCreateRequest.address = this.addressCreate()
        return companyCreateRequest;
    }

    companyUpdateRequest() {
        let companyUpdateRequest = company.update;
        companyUpdateRequest.companyId = 1;
        companyUpdateRequest.locationIdList = [1];
        companyUpdateRequest.companyName = "updatedTestyName";
        companyUpdateRequest.companyDescription = "updatedTestDescription";
        companyUpdateRequest.address = this.addressUpdate();
        return companyUpdateRequest;
    }

}

export default new TestConstraints();