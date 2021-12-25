import {Button, ButtonGroup, Grid, Paper} from "@mui/material";
import {useEffect, useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import DialogTemplate from "../components/dialog/DialogTemplate";

import AlertDialog from "../components/dialog/AlertDialog";
import BookingApi from "../service/booking/BookingApi";
import BookingTable from "../components/table/booking/BookingTable";
import BookingForm from "../components/form/booking/BookingForm";

const BookingContainer = () => {

    const [bookingList, setBookingList] = useState([]);
    const [selectedBookings, setSelectedBookings] = useState([]);
    const [bookingToEdit, setBookingToEdit] = useState(null);
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    function fetchBookings() {
        BookingApi.readAll()
            .then(
                response => {
                    setBookingList(response.data)
                }
            );
    }

    useEffect(() => {
        fetchBookings();
    }, []);

    const onSelectionChangeHandler = (ids) => {
        setSelectedBookings(ids);
    }

    const handleAddButtonClicked = () => {
        setAddDialogOpen(true);
    }

    const handleAddDialogClose = () => {
        setAddDialogOpen(false);
        fetchBookings();
    }

    const handleEditButtonClicked = () => {
        setBookingToEdit(selectedBookings[0]);
        setEditDialogOpen(true);
    }

    const handleEditDialogClose = () => {
        setEditDialogOpen(false);
        fetchBookings();
    }

    const handleDeleteButtonClicked = () => {
        setDeleteDialogOpen(true);
    }

    const handleDeleteDialogClose = (result) => {
        setDeleteDialogOpen(false);
        if (result) {
            selectedBookings.forEach(booking => BookingApi.delete(booking.bookingId));
            setBookingList(bookingList.filter(booking => !selectedBookings.includes(booking)));
        }
    }

    return (
        <div>
            <Grid
                container
                spacing={3}
                direction="column"
                justifyContent="center"
            >

                <Grid item container justifyContent="flex-end">

                    <ButtonGroup size={"small"} variant="outlined">
                        <Button
                            color="primary"
                            startIcon={<AddIcon/>}
                            onClick={handleAddButtonClicked}
                        >
                            Add
                        </Button>
                        <Button
                            color="secondary"
                            endIcon={<ExpandMoreIcon/>}
                        >
                            More
                        </Button>
                    </ButtonGroup>

                </Grid>


                <Grid item>
                    <Paper elevation={1}>
                        <BookingTable
                            fields={bookingList ? bookingList : []}
                            onSelectionChangeHandler={onSelectionChangeHandler}
                            onEdit={handleEditButtonClicked}
                            onDelete={handleDeleteButtonClicked}
                        />
                    </Paper>
                </Grid>

            </Grid>

            <DialogTemplate
                open={addDialogOpen}
                onClose={handleAddDialogClose}
                title={"Add Booking"}
            >
                <BookingForm op={"CREATE"} />
            </DialogTemplate>

            <DialogTemplate
                open={editDialogOpen}
                onClose={handleEditDialogClose}
                title={"Edit Booking"}
            >
                <BookingForm op={"UPDATE"} fields={bookingToEdit} />
            </DialogTemplate>

            <AlertDialog
                title={"Booking Deletion"}
                message={"Are you sure you want to delete selected bookings?"}

                isOpen={deleteDialogOpen}
                onClose={handleDeleteDialogClose}
            />


        </div>
    )

}

export default BookingContainer;