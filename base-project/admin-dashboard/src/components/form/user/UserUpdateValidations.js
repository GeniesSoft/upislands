import * as yup from "yup";

const validationsForm = {
    firstName: yup
        .string()
        .required("First Name is Required"),

    lastName: yup
        .string()
        .required("Last Name is Required"),

    emailAddress: yup
        .string()
        .email("Enter a valid email")
        .required("Email is required"),

    birthDate: yup
        .date("Birth date must be a valid Date")
        .required("Birth Date is required"),

    phoneNumber: yup
        .string()
        .matches(
            /^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/,
            "Enter a valid phone"
        )
        .required("Phone Number is required"),

};

export default validationsForm;