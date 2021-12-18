import * as yup from "yup";

const validationsForm = {

    userId: yup
        .number()
        .required("User Id is Required"),

    companyName: yup
        .string()
        .required("Company Name is Required"),

    companyDescription: yup
        .string()
        .required("Company is Required"),

};

export default validationsForm;