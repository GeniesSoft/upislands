import {Button, Container, CssBaseline, Grid, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import UserCreateValidations from "./UserCreateValidations";
import UserUpdateValidations from "./UserUpdateValidations";
import UserApi from "../../../service/user/UserApi";

const UserForm = (props) => {

    const {
        userId,
        firstName,
        lastName,
        emailAddress,
        password,
        phoneNumber,
        birthDate,
    } = props.fields ||
    {
        userId: "",
        firstName: "",
        lastName: "",
        emailAddress: "",
        password: "",
        phoneNumber: "",
        birthDate: "",
    }

    const initCreate = {
        userId: userId,
        firstName: firstName,
        lastName: lastName,
        emailAddress: emailAddress,
        password: password,
        phoneNumber: phoneNumber,
        birthDate: birthDate,
    }

    const initUpdate = {
        userId: userId,
        firstName: firstName,
        lastName: lastName,
        emailAddress: emailAddress,
        phoneNumber: phoneNumber,
        birthDate: birthDate,
    }

    const getInitialValues = () => {
        return props.op === "CREATE" ? initCreate : initUpdate;
    }

    const getUserValidations = () => {
        return props.op === "CREATE" ? UserCreateValidations : UserUpdateValidations;
    }


    const formik = useFormik({
        initialValues: getInitialValues(),

        validationSchema: yup.object().shape(getUserValidations()),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(() => {

                switch (props.op) {
                    case "CREATE":
                        UserApi.create(values);
                        break;
                    case "UPDATE":
                        UserApi.update(values);
                        break;
                    default:
                        break;
                }

                setSubmitting(false);
            }, 1000);

        },
    });

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <form onSubmit={formik.handleSubmit}>

                <Grid container>

                    {props.op === "UPDATE" ?
                        <Grid item xs={12}>
                            <TextField
                                id="userId"
                                label="User Id"
                                type={"text"}
                                value={formik.values.userId}
                                onBlur={formik.handleBlur}
                                helperText={formik.touched.userId ? formik.errors.userId : " "}
                                error={formik.touched.userId && Boolean(formik.errors.userId)}
                                margin="dense"
                                variant="outlined"
                                fullWidth
                                onClick={(e) => e.preventDefault() }
                            />
                        </Grid>
                        :
                        null
                    }

                    <Grid item xs={12} md={6}>
                        <TextField
                            id="firstName"
                            label="First Name"
                            type={"text"}
                            value={formik.values.firstName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.firstName ? formik.errors.firstName : " "}
                            error={formik.touched.firstName && Boolean(formik.errors.firstName)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12} md={6}>
                        <TextField
                            id="lastName"
                            label="Last Name"
                            value={formik.values.lastName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.lastName ? formik.errors.lastName : " "}
                            error={formik.touched.lastName && Boolean(formik.errors.lastName)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12} md={6}>
                        <TextField
                            id="emailAddress"
                            label="Email Address"
                            value={formik.values.emailAddress}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.emailAddress ? formik.errors.emailAddress : " "}
                            error={formik.touched.emailAddress && Boolean(formik.errors.emailAddress)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    {props.op === "CREATE" ?
                        <Grid item xs={12} md={6}>
                            <TextField
                                id="password"
                                label="Password"
                                value={formik.values.password}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                helperText={formik.touched.password ? formik.errors.password : " "}
                                error={formik.touched.password && Boolean(formik.errors.password)}
                                margin="dense"
                                variant="outlined"
                                fullWidth
                            />
                        </Grid>
                        :
                        null
                    }


                    <Grid item xs={12}>
                        <TextField
                            id="phoneNumber"
                            label="Phone Number"
                            type={"number"}
                            value={formik.values.phoneNumber}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.phoneNumber ? formik.errors.phoneNumber : " "}
                            error={formik.touched.phoneNumber && Boolean(formik.errors.phoneNumber)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="birthDate"
                            label="Birth Date"
                            type={"date"}
                            value={formik.values.birthDate}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.birthDate ? formik.errors.birthDate : " "}
                            error={formik.touched.birthDate && Boolean(formik.errors.birthDate)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </Grid>

                </Grid>

                <Button
                    type="submit"
                    color="primary"
                    disabled={formik.isSubmitting}
                >
                    SUBMIT
                </Button>

                <Button
                    color="secondary"
                    onClick={formik.handleReset}
                >
                    CLEAR
                </Button>

            </form>

        </Container>
    );

}

export default UserForm;