import * as yup from "yup";

const validationsForm = {
    locationName: yup
        .string()
        .required("Location Name is Required"),

    description: yup
        .string()
        .required("Description is Required"),

    tripTime: yup
        .string()
        .required("Trip Time is required"),

};

export default validationsForm;