import {Button, ButtonGroup, Grid, Paper} from "@mui/material";
import {useEffect, useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import DialogTemplate from "../components/dialog/DialogTemplate";

import AlertDialog from "../components/dialog/AlertDialog";
import LocationForm from "../components/form/location/LocationForm";
import CompanyApi from "../service/company/CompanyApi";
import CompanyTable from "../components/table/company/CompanyTable";
import CompanyForm from "../components/form/company/CompanyForm";
import LocationTable from "../components/table/location/LocationTable";
import AddLocationForm from "../components/form/company/AddLocationForm";

const CompanyContainer = () => {

    const [companyList, setCompanyList] = useState([]);
    const [selectedCompanies, setSelectedCompanies] = useState([]);
    const [companyToEdit, setCompanyToEdit] = useState(null);
    const [selectedCompanyLocaitonList, setSelectedCompanyLocaitonList] = useState([]);
    const [selectedCompanyLocationsToDelete, setSelectedCompanyLocationsToDelete] = useState([]);
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [addLocationDisabled, setAddLocationDisabled] = useState(true);
    const [addLocationDialogOpen, setAddLocationDialogOpen] = useState(false);
    const [deleteLocationDialogOpen, setDeleteLocationDialogOpen] = useState(false);

    function fetchCompanies() {
        CompanyApi.readAll()
            .then(
                response => {
                    setCompanyList(response.data)
                }
            );
    }

    useEffect(() => {
        fetchCompanies();
    }, []);

    const onSelectionChangeHandler = (ids) => {
        setSelectedCompanies(ids);

        if (ids.length === 1) {
            setSelectedCompanyLocaitonList(ids[0].locationList);
            setAddLocationDisabled(false);
        } else {
            setSelectedCompanyLocaitonList([]);
            setAddLocationDisabled(true);
        }

    }

    const onLocationSelectionChangeHandler = (ids) => {
        setSelectedCompanyLocationsToDelete(ids);
    }

    const handleAddButtonClicked = () => {
        setAddDialogOpen(true);
    }

    const handleEditButtonClicked = () => {
        setCompanyToEdit(selectedCompanies[0]);
        setEditDialogOpen(true);
    }

    const handleDeleteButtonClicked = () => {
        setDeleteDialogOpen(true);
    }

    const handleAddLocationButtonClicked = () => {
        setAddLocationDialogOpen(true);
    }

    const handleAddDialogClose = () => {
        setAddDialogOpen(false);
        fetchCompanies();
    }

    const handleEditDialogClose = () => {
        setEditDialogOpen(false);
        fetchCompanies();
    }

    const handleDeleteDialogClose = (result) => {
        setDeleteDialogOpen(false);
        if (result) {
            selectedCompanies.forEach(company => CompanyApi.delete(company.companyId));
            setCompanyList(companyList.filter(company => !selectedCompanies.includes(company)));
        }
    }

    const handleAddLocationDialogClose = () => {
        setAddLocationDialogOpen(false);
        fetchCompanies();
    }

    const handleLocationDeleteDialogClose = (result) => {
        setDeleteLocationDialogOpen(false);
        if (result) {
            console.log(selectedCompanyLocationsToDelete)
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
                        <CompanyTable
                            fields={companyList ? companyList : []}
                            onSelectionChangeHandler={onSelectionChangeHandler}
                            onEdit={handleEditButtonClicked}
                            onDelete={handleDeleteButtonClicked}
                        />
                    </Paper>
                </Grid>

                <Grid item container justifyContent="flex-end">

                    <ButtonGroup size={"small"} variant="outlined">
                        <Button
                            color="primary"
                            startIcon={<AddIcon/>}
                            onClick={handleAddLocationButtonClicked}
                            disabled={addLocationDisabled}
                        >
                            Add Location
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
                            fields={selectedCompanyLocaitonList ? selectedCompanyLocaitonList : []}
                            onSelectionChangeHandler={onLocationSelectionChangeHandler}
                            onEdit={handleEditButtonClicked}
                            onDelete={handleDeleteButtonClicked}
                        />
                    </Paper>
                </Grid>

            </Grid>

            <DialogTemplate
                open={addDialogOpen}
                onClose={handleAddDialogClose}
                title={"Add Company"}
            >
                <CompanyForm op={"CREATE"} />
            </DialogTemplate>

            <DialogTemplate
                open={editDialogOpen}
                onClose={handleEditDialogClose}
                title={"Edit Company"}
            >
                <CompanyForm op={"UPDATE"} fields={companyToEdit} />
            </DialogTemplate>

            <AlertDialog
                title={"Company Deletion"}
                message={"Are you sure you want to delete selected companies?"}

                isOpen={deleteDialogOpen}
                onClose={handleDeleteDialogClose}
            />

            <DialogTemplate
                open={addLocationDialogOpen}
                onClose={handleAddLocationDialogClose}
                title={"Add Location to Company"}
            >
                <AddLocationForm companyId={selectedCompanies[0] ? selectedCompanies[0].companyId : 0}/>
            </DialogTemplate>

            <AlertDialog
                title={"Location Deletion"}
                message={"Are you sure you want to delete selected locations?"}

                isOpen={deleteLocationDialogOpen}
                onClose={handleLocationDeleteDialogClose}
            />

        </div>
    )

}

export default CompanyContainer;