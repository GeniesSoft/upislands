import CompanyApi from "../company/CompanyApi";
import TestConstraints from "./TestConstraints";
import {Button} from "@mui/material";

const CompanyTest = () => {

    const createCompany = () => {
        CompanyApi.create(TestConstraints.companyCreateRequest());
    }
    const getCompany = () => {
        CompanyApi
            .read(1)
            .then(response => console.log(response.data))
            .catch(error => console.log(error));
    }
    const updateCompany = () => {
        CompanyApi.update(TestConstraints.companyUpdateRequest());
    }
    const deleteCompany = () => {
        CompanyApi.delete(1);
    }

    return (
        <div>
            <Button onClick={createCompany}>Create Company</Button>
            <Button onClick={getCompany}>Get Company</Button>
            <Button onClick={updateCompany}>Update Company</Button>
            <Button onClick={deleteCompany}>Delete Company</Button>
        </div>
    );

}

export default CompanyTest;