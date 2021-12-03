import {Button, ButtonGroup, Grid, Paper} from "@mui/material";
import UserTable from "../components/table/user/UserTable";
import {useEffect, useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import DialogTemplate from "../components/dialog/DialogTemplate";
import UserForm from "../components/form/user/UserForm";
import UserApi from "../service/user/UserApi";
import AlertDialog from "../components/dialog/AlertDialog";

const UserContainer = () => {

    const [userList, setUserList] = useState([]);
    const [selectedUsers, setSelectedUsers] = useState([]);
    const [userToEdit, setUserToEdit] = useState(null);
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    function fetchUsers() {
        UserApi.readAll()
            .then(
                response => {
                    setUserList(response.data)
                }
            );
    }

    useEffect(() => {
        fetchUsers();
    }, []);

    const onSelectionChangeHandler = (ids) => {
        setSelectedUsers(ids);
    }

    const handleAddButtonClicked = () => {
        setAddDialogOpen(true);
    }

    const handleAddDialogClose = () => {
        setAddDialogOpen(false);
        fetchUsers();
    }

    const handleEditButtonClicked = () => {
        setUserToEdit(selectedUsers[0]);
        setEditDialogOpen(true);
    }

    const handleEditDialogClose = () => {
        setEditDialogOpen(false);
        fetchUsers();
    }

    const handleDeleteButtonClicked = () => {
        setDeleteDialogOpen(true);
    }

    const handleDeleteDialogClose = (result) => {
        setDeleteDialogOpen(false);
        if (result) {
            selectedUsers.forEach(user => UserApi.delete(user.userId));
            setUserList(userList.filter(u => !selectedUsers.includes(u)));
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
                        <UserTable
                            fields={userList ? userList : []}
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
                title={"Add User"}
            >
                <UserForm op={"CREATE"} />
            </DialogTemplate>

            <DialogTemplate
                open={editDialogOpen}
                onClose={handleEditDialogClose}
                title={"Edit User"}
            >
                <UserForm op={"UPDATE"} fields={userToEdit} />
            </DialogTemplate>

            <AlertDialog
                title={"User Deletion"}
                message={"Are you sure you want to delete selected users?"}

                isOpen={deleteDialogOpen}
                onClose={handleDeleteDialogClose}
            />


        </div>
    )

}

export default UserContainer;