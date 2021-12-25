import {Button, Container, CssBaseline, Grid, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import LocationApi from "../../../service/location/LocationApi";
import LocationValidations from "./LocationValidations";
import Dropzone from "../../../service/content/Dropzone";

const LocationForm = (props) => {

    const {
        locationId,
        locationName,
        description,
        tripTime,
        needExperience,
        address,
    } = props.fields ||
    {
        locationId: "",
        locationName: "",
        description: "",
        tripTime: "",
        needExperience: "",
        address: "",
    }

    const initCreate = {
        locationId: locationId,
        locationName: locationName,
        description: description,
        tripTime: tripTime,
        needExperience: needExperience,
        address: address,
    }

    const initUpdate = {
        locationId: locationId,
        locationName: locationName,
        description: description,
        tripTime: tripTime,
        needExperience: needExperience,
        address: address,
    }

    const getInitialValues = () => {
        return props.op === "CREATE" ? initCreate : initUpdate;
    }

    const getValidations = () => {
        return props.op === "CREATE" ? LocationValidations : LocationValidations;
    }


    const formik = useFormik({
        initialValues: getInitialValues(),

        validationSchema: yup.object().shape(getValidations()),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(() => {

                values.address = {};

                switch (props.op) {
                    case "CREATE":
                        LocationApi.create(values);
                        break;
                    case "UPDATE":
                        LocationApi.update(values);
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
                                id="locationId"
                                label="Location Id"
                                value={formik.values.locationId}
                                onBlur={formik.handleBlur}
                                helperText={formik.touched.locationId ? formik.errors.locationId : " "}
                                error={formik.touched.locationId && Boolean(formik.errors.locationId)}
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
                            id="locationName"
                            label="Location Name"
                            value={formik.values.locationName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.locationName ? formik.errors.locationName : " "}
                            error={formik.touched.locationName && Boolean(formik.errors.locationName)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="description"
                            label="Description"
                            value={formik.values.description}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.description ? formik.errors.description : " "}
                            error={formik.touched.description && Boolean(formik.errors.description)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12} md={6}>
                        <TextField
                            id="tripTime"
                            label="Trip Time"
                            value={formik.values.tripTime}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.tripTime ? formik.errors.tripTime : " "}
                            error={formik.touched.tripTime && Boolean(formik.errors.tripTime)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>

                    <Grid item xs={12} md={6}>
                        <TextField
                            id="needExperience"
                            label="Need Experience"
                            value={formik.values.needExperience}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.needExperience ? formik.errors.needExperience : " "}
                            error={formik.touched.needExperience && Boolean(formik.errors.needExperience)}
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

                    {props.op === "UPDATE" ?
                        <Grid item xs={12}>
                            <p>Add Content</p>
                            <Dropzone locationId={locationId} />
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

export default LocationForm;