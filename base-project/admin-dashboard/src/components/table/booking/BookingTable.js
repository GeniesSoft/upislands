import DataTable from 'react-data-table-component';
import {useCallback, useMemo, useState} from "react";
import ClearIcon from '@mui/icons-material/Clear';
import {Button} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import Box from "@mui/material/Box";

const columns = [
    {selector: row => row["bookingId"], name: "Booking Id"},
    {selector: row => row["companyId"], name: "Company Id"},
    {selector: row => row["localGuideId"], name: "Local Guide Id"},
    {selector: row => row["locationId"], name: "Location Id"},
    {selector: row => row["userId"], name: "User Id"},
    {selector: row => row["date"], name: "Date"},
    {selector: row => row["startTime"], name: "Start Time"},
    {selector: row => row["endTime"], name: "End Time"},
    {selector: row => row["jetSkiCount"], name: "Jet Ski Count"},
    {selector: row => row["active"].toString(), name: "Is Active"},
    {selector: row => row["paid"].toString(), name: "Is Paid"},
];

const BookingTable = (props) => {

    const [selectedRows, setSelectedRows] = useState([]);
    const [toggleCleared, setToggleCleared] = useState(false);
    const [data] = props.fields;

    const [isEditButtonsDisabled, setEditButtonsDisabled] = useState(true);
    const [isDeleteButtonsDisabled, setDeleteButtonsDisabled] = useState(true);

    const handleRowSelected = useCallback(state => {
        setSelectedRows(state.selectedRows);
        setEditButtonsDisabled(state.selectedCount !== 1);
        setDeleteButtonsDisabled(state.selectedCount < 1);
        const selectedIds = state.selectedRows;
        props.onSelectionChangeHandler(selectedIds);
    }, [props]);

    const contextActions = useMemo(() => {
        const handleEdit = () => {
            props.onEdit();
            setToggleCleared(!toggleCleared);
        };

        const handleDelete = () => {
            props.onDelete();
            setToggleCleared(!toggleCleared);
        };

        const handleClear = () => {
            setToggleCleared(!toggleCleared);
        };

        return (
            <Box>
                <Button
                    key="edit"
                    color="warning"
                    children={<EditIcon/>}
                    disabled={isEditButtonsDisabled}
                    onClick={handleEdit}
                />
                <Button
                    key="delete"
                    color="error"
                    children={<DeleteIcon/>}
                    disabled={isDeleteButtonsDisabled}
                    onClick={handleDelete}
                />
                <Button
                    key="clear"
                    color="primary"
                    children={<ClearIcon/>}
                    onClick={handleClear}
                />
            </Box>
        );
    }, [data, selectedRows, toggleCleared]);

    return (
        <div>
            <DataTable
                title={"Bookings"}
                columns={columns}
                data={props.fields}
                selectableRows
                onSelectedRowsChange={handleRowSelected}
                contextActions={contextActions}
                clearSelectedRows={toggleCleared}
                highlightOnHover
                pointerOnHover
                pagination={true}
                paginationRowsPerPageOptions={[5, 10, 25, 100]}
                paginationPerPage={5}
            />
        </div>
    )

}

export default BookingTable;