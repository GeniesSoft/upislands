import {Button, ButtonGroup, Grid, Paper} from "@mui/material";
import {useEffect, useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import DialogTemplate from "../components/dialog/DialogTemplate";

import AlertDialog from "../components/dialog/AlertDialog";
import LocationApi from "../service/location/LocationApi";
import LocationForm from "../components/form/location/LocationForm";
import LocationTable from "../components/table/location/LocationTable";
import LocalGuideApi from "../service/localguide/LocalGuideApi";
import LocalGuideForm from "../components/form/localguide/LocalGuideForm";
import LocalGuideTable from "../components/table/localguide/LocalGuideTable";

const LocalGuideContainer = () => {

    const [localGuideList, setLocalGuideList] = useState([]);
    const [selectedLocalGuide, setSelectedLocalGuide] = useState([]);
    const [localGuideToEdit, setLocalGuideToEdit] = useState(null);
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    function fetchLocalGuides() {
        LocalGuideApi.readAll()
            .then(
                response => {
                    setLocalGuideList(response.data)
                }
            );
    }

    useEffect(() => {
        fetchLocalGuides();
    }, []);

    const onSelectionChangeHandler = (ids) => {
        setSelectedLocalGuide(ids);
    }

    const handleAddButtonClicked = () => {
        setAddDialogOpen(true);
    }

    const handleAddDialogClose = () => {
        setAddDialogOpen(false);
        fetchLocalGuides();
    }

    const handleEditButtonClicked = () => {
        setLocalGuideToEdit(selectedLocalGuide[0]);
        setEditDialogOpen(true);
    }

    const handleEditDialogClose = () => {
        setEditDialogOpen(false);
        fetchLocalGuides();
    }

    const handleDeleteButtonClicked = () => {
        setDeleteDialogOpen(true);
    }

    const handleDeleteDialogClose = (result) => {
        setDeleteDialogOpen(false);
        if (result) {
            selectedLocalGuide.forEach(localGuide => LocalGuideApi.delete(localGuide.localGuideId));
            setLocalGuideList(localGuideList.filter(localGuide => !selectedLocalGuide.includes(localGuide)));
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
                        <LocalGuideTable
                            fields={localGuideList ? localGuideList : []}
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
                <LocalGuideForm op={"CREATE"} />
            </DialogTemplate>

            <DialogTemplate
                open={editDialogOpen}
                onClose={handleEditDialogClose}
                title={"Edit Location"}
            >
                <LocalGuideForm op={"UPDATE"} fields={localGuideToEdit} />
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

export default LocalGuideContainer;