import {Button, Container, CssBaseline, Grid, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import CompanyApi from "../../../service/company/CompanyApi";
import CompanyValidations from "./CompanyValidations";

const CompanyForm = (props) => {

    const {
        companyId,
        userId,
        companyName,
        companyDescription,
        address,
    } = props.fields ||
    {
        companyId: "",
        userId: "",
        companyName: "",
        companyDescription: "",
        address: "",
    }

    const initCreate = {
        userId: userId,
        companyName: companyName,
        companyDescription: companyDescription,
        address: address,
    }

    const initUpdate = {
        companyId: companyId,
        userId: userId,
        companyName: companyName,
        companyDescription: companyDescription,
        address: address,
    }

    const getInitialValues = () => {
        return props.op === "CREATE" ? initCreate : initUpdate;
    }

    const getValidations = () => {
        return props.op === "CREATE" ? CompanyValidations : CompanyValidations;
    }


    const formik = useFormik({
        initialValues: getInitialValues(),

        validationSchema: yup.object().shape(getValidations()),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(() => {

                values.address = {};

                switch (props.op) {
                    case "CREATE":
                        CompanyApi.create(values);
                        break;
                    case "UPDATE":
                        CompanyApi.update(values);
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
                        :
                        null
                    }

                    <Grid item xs={12}>
                        <TextField
                            id="userId"
                            label="User Id"
                            type={"number"}
                            value={formik.values.userId}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.userId ? formik.errors.userId : " "}
                            error={formik.touched.userId && Boolean(formik.errors.userId)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="companyName"
                            label="Company Name"
                            value={formik.values.companyName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.companyName ? formik.errors.companyName : " "}
                            error={formik.touched.companyName && Boolean(formik.errors.companyName)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="companyDescription"
                            label="Description"
                            value={formik.values.companyDescription}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.companyDescription ? formik.errors.companyDescription : " "}
                            error={formik.touched.companyDescription && Boolean(formik.errors.companyDescription)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="address"
                            label="Address"
                            value={formik.values.address}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.address ? formik.errors.address : " "}
                            error={formik.touched.address && Boolean(formik.errors.address)}
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

export default CompanyForm;