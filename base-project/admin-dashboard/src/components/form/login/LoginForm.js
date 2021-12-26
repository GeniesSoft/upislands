import {Button, Container, CssBaseline, Grid, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import LoginValidations from "./LoginValidations";
import AuthApi from "../../../service/auth/AuthApi";
import { useNavigate } from 'react-router-dom';

const LoginForm = (props) => {

    const {
        username,
        password
    } = props.fields ||
    {
        username: "",
        password: ""
    }

    const navigate = useNavigate();

    const formik = useFormik({
        initialValues: {username, password},

        validationSchema: yup.object().shape(LoginValidations),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(async () => {

                const logedIn = await AuthApi.login(values.username, values.password);

                if (logedIn) {
                    console.log("hello");
                    navigate('/');
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
                    <Grid item xs={12}>
                        <TextField
                            id="username"
                            label="Username"
                            value={formik.values.username}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.username ? formik.errors.username : " "}
                            error={formik.touched.username && Boolean(formik.errors.username)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
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
                </Grid>

                <Button
                    type="submit"
                    color="primary"
                    disabled={formik.isSubmitting}
                    variant={"outlined"}
                >
                    Login
                </Button>

                <Button
                    color="secondary"
                    onClick={formik.handleReset}
                    variant={"outlined"}
                >
                    CLEAR
                </Button>

            </form>

        </Container>
    );

}

export default LoginForm;