import {Button, Container, CssBaseline, Grid, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import CompanyApi from "../../../service/company/CompanyApi";

const AddLocationForm = (props) => {

    const companyId = props.companyId

    const initCreate = {
        companyId: parseInt(companyId),
        locationId: "",
    }

    const getInitialValues = () => {
        return initCreate;
    }

    const getValidations = () => {
        return null;
    }

    const formik = useFormik({
        initialValues: getInitialValues(),

        validationSchema: yup.object().shape(getValidations()),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(() => {

                CompanyApi.addLocation(values.companyId, values.locationId);

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
                            id="companyId"
                            label="Company Id"
                            type={"number"}
                            value={formik.values.companyId}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.companyId ? formik.errors.companyId : " "}
                            error={formik.touched.companyId && Boolean(formik.errors.companyId)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                            onClick={(e) => e.preventDefault() }
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="locationId"
                            label="Location Id"
                            type={"number"}
                            value={formik.values.userId}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.locationId ? formik.errors.locationId : " "}
                            error={formik.touched.locationId && Boolean(formik.errors.locationId)}
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

export default AddLocationForm;