import {Button, ButtonGroup, Grid, Paper} from "@mui/material";
import {useEffect, useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import DialogTemplate from "../components/dialog/DialogTemplate";

import AlertDialog from "../components/dialog/AlertDialog";
import LocationApi from "../service/location/LocationApi";
import LocationForm from "../components/form/location/LocationForm";
import LocationTable from "../components/table/location/LocationTable";

const LocationContainer = () => {

    const [locationList, setLocationList] = useState([]);
    const [selectedLocations, setSelectedLocations] = useState([]);
    const [locationToEdit, setLocationToEdit] = useState(null);
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    function fetchLocations() {
        LocationApi.readAll()
            .then(
                response => {
                    setLocationList(response.data)
                }
            );
    }

    useEffect(() => {
        fetchLocations();
    }, []);

    const onSelectionChangeHandler = (ids) => {
        setSelectedLocations(ids);
    }

    const handleAddButtonClicked = () => {
        setAddDialogOpen(true);
    }

    const handleAddDialogClose = () => {
        setAddDialogOpen(false);
        fetchLocations();
    }

    const handleEditButtonClicked = () => {
        setLocationToEdit(selectedLocations[0]);
        setEditDialogOpen(true);
    }

    const handleEditDialogClose = () => {
        setEditDialogOpen(false);
        fetchLocations();
    }

    const handleDeleteButtonClicked = () => {
        setDeleteDialogOpen(true);
    }

    const handleDeleteDialogClose = (result) => {
        setDeleteDialogOpen(false);
        if (result) {
            selectedLocations.forEach(location => LocationApi.delete(location.locationId));
            setLocationList(locationList.filter(location => !selectedLocations.includes(location)));
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
                        <LocationTable
                            fields={locationList ? locationList : []}
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
                title={"Add Location"}
            >
                <LocationForm op={"CREATE"} />
            </DialogTemplate>

            <DialogTemplate
                open={editDialogOpen}
                onClose={handleEditDialogClose}
                title={"Edit Location"}
            >
                <LocationForm op={"UPDATE"} fields={locationToEdit} />
            </DialogTemplate>

            <AlertDialog
                title={"Location Deletion"}
                message={"Are you sure you want to delete selected locations?"}

                isOpen={deleteDialogOpen}
                onClose={handleDeleteDialogClose}
            />


        </div>
    )

}

export default LocationContainer;