import {Button, Container, CssBaseline, Grid, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import LocationApi from "../../../service/location/LocationApi";
import LocalGuideApi from "../../../service/localguide/LocalGuideApi";
import Dropzone from "../../../service/content/Dropzone";

const LocalGuideForm = (props) => {

    const {
        localGuideId,
        localGuideName,
        companyId
    } = props.fields ||
    {
        localGuideId: "",
        localGuideName: "",
        companyId: ""
    }

    const initCreate = {
        localGuideId: localGuideId,
        localGuideName: localGuideName,
        companyId: companyId
    }

    const initUpdate = {
        localGuideId: localGuideId,
        localGuideName: localGuideName,
        companyId: companyId
    }

    const getInitialValues = () => {
        return props.op === "CREATE" ? initCreate : initUpdate;
    }

    const getValidations = () => {
        return props.op === "CREATE" ? null : null;
    }


    const formik = useFormik({
        initialValues: getInitialValues(),

        validationSchema: yup.object().shape(getValidations()),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(() => {

                values.address = {};

                switch (props.op) {
                    case "CREATE":
                        LocalGuideApi.create(values);
                        break;
                    case "UPDATE":
                        LocalGuideApi.update(values);
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
                                id="localGuideId"
                                label="Local GuideId"
                                value={formik.values.localGuideId}
                                onBlur={formik.handleBlur}
                                helperText={formik.touched.localGuideId ? formik.errors.localGuideId : " "}
                                error={formik.touched.localGuideId && Boolean(formik.errors.localGuideId)}
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
                            id="localGuideName"
                            label="Local Guide Name"
                            value={formik.values.localGuideName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.localGuideName ? formik.errors.localGuideName : " "}
                            error={formik.touched.localGuideName && Boolean(formik.errors.localGuideName)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="companyId"
                            label="Company Id"
                            value={formik.values.companyId}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.companyId ? formik.errors.companyId : " "}
                            error={formik.touched.companyId && Boolean(formik.errors.companyId)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    {props.op === "UPDATE" ?
                        <Grid item xs={12}>
                            <p>Add Content</p>
                            <Dropzone id={localGuideId} path={"local-guide"}/>
                        </Grid>
                        :
                        null
                    }

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

export default LocalGuideForm;