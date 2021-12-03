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

    password: yup
        .string()
        .required("Password is required")
        .min(8, "Password must contain at least 8 characters")
        .max(30, "Maximum password size is 30 characters")
        .matches(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@.,$!%*?&]{8,30}$/,
            "Must Contain at least one uppercase letter, one lowercase letter, one number and one special character"
        ),

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