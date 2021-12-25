import {Button, Container, CssBaseline, Grid, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import {useFormik} from "formik";
import * as yup from "yup";
import Dropzone from "../../../service/content/Dropzone";
import BookingApi from "../../../service/booking/BookingApi";

const BookingForm = (props) => {

    const {
        bookingId,
        companyId,
        localGuideId,
        locationId,
        userId,
        date,
        startTime,
        endTime,
        jetSkiCount,
        active,
        paid,
    } = props.fields ||
    {
        bookingId: "",
        companyId: "",
        localGuideId: "",
        locationId: "",
        userId: "",
        date: "",
        startTime: "",
        endTime: "",
        jetSkiCount: "",
        active: "",
        paid: "",
    }

    const initCreate = {
        companyId: companyId,
        localGuideId: localGuideId,
        locationId: locationId,
        userId: userId,
        date: date,
        startTime: startTime,
        endTime: endTime,
        jetSkiCount: jetSkiCount,
        active: active,
        paid: paid,
    }

    const initUpdate = {
        bookingId: bookingId,
        companyId: companyId,
        localGuideId: localGuideId,
        locationId: locationId,
        userId: userId,

        date: date,
        startTime: startTime,
        endTime: endTime,
        jetSkiCount: jetSkiCount,
        active: active,
        paid: paid,
    }

    const getInitialValues = () => {
        return props.op === "CREATE" ? initCreate : initUpdate;
    }

    const getValidations = () => {
        return props.op === "CREATE" ? null: null;
    }


    const formik = useFormik({
        initialValues: getInitialValues(),

        validationSchema: yup.object().shape(getValidations()),

        onSubmit: (values, {setSubmitting}) => {

            setTimeout(() => {

                values.address = {};

                switch (props.op) {
                    case "CREATE":
                        BookingApi.create(values);
                        break;
                    case "UPDATE":
                        BookingApi.update(values);
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
                                id="bookingId"
                                label="Booking Id"
                                value={formik.values.bookingId}
                                onBlur={formik.handleBlur}
                                helperText={formik.touched.bookingId ? formik.errors.bookingId : " "}
                                error={formik.touched.bookingId && Boolean(formik.errors.bookingId)}
                                margin="dense"
                                variant="outlined"
                                fullWidth
                                onClick={(e) => e.preventDefault() }
                            />
                        </Grid>
                        :
                        null
                    }

                    <Grid item xs={6}>
                        <TextField
                            id="companyId"
                            label="Company Id"
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
                    <Grid item xs={6}>
                        <TextField
                            id="localGuideId"
                            label="Local Guide Id"
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
                    <Grid item xs={6}>
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
                    <Grid item xs={6}>
                        <TextField
                            id="userId"
                            label="User Id"
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

                    <hr style={{width: "100%"}} />

                    <Grid item xs={12}>
                        <TextField
                            id="date"
                            label="Date"
                            value={formik.values.date}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.userId ? formik.errors.userId : " "}
                            error={formik.touched.userId && Boolean(formik.errors.userId)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                            onClick={(e) => e.preventDefault() }
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            id="startTime"
                            label="Start Time"
                            value={formik.values.startTime}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.startTime ? formik.errors.startTime : " "}
                            error={formik.touched.startTime && Boolean(formik.errors.startTime)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                            onClick={(e) => e.preventDefault() }
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            id="endTime"
                            label="End Time"
                            value={formik.values.endTime}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.endTime ? formik.errors.endTime : " "}
                            error={formik.touched.endTime && Boolean(formik.errors.endTime)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                            onClick={(e) => e.preventDefault() }
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            id="jetSkiCount"
                            label="Jet Ski Count"
                            value={formik.values.jetSkiCount}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.jetSkiCount ? formik.errors.jetSkiCount : " "}
                            error={formik.touched.jetSkiCount && Boolean(formik.errors.jetSkiCount)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                            onClick={(e) => e.preventDefault() }
                        />
                    </Grid>

                    <hr style={{width: "100%"}} />

                    <Grid item xs={12}>
                        <TextField
                            id="active"
                            label="Is Active"
                            value={formik.values.active}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.active ? formik.errors.active : " "}
                            error={formik.touched.active && Boolean(formik.errors.active)}
                            margin="dense"
                            variant="outlined"
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            id="paid"
                            label="Is Paid"
                            value={formik.values.paid}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            helperText={formik.touched.paid ? formik.errors.paid : " "}
                            error={formik.touched.paid && Boolean(formik.errors.paid)}
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

export default BookingForm;