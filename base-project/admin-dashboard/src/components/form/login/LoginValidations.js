import * as yup from "yup";

const validationsForm = {
    username: yup
        .string()
        .required("Username is Required"),

    password: yup
        .string()
        .required("Password is Required"),

};

export default validationsForm;