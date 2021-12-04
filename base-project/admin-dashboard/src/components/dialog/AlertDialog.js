import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

const AlertDialog = (props) => {

    return (
        <div>
            <Dialog
                open={props.isOpen}
                onClose={(e) => props.onClose(false)}
            >
                <DialogTitle>
                    {props.title}
                </DialogTitle>

                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        {props.message}
                    </DialogContentText>
                </DialogContent>

                <DialogActions>
                    <Button onClick={(e) => props.onClose(false)} color="primary" autoFocus>
                        No
                    </Button>
                    <Button onClick={(e) => props.onClose(true)} color="primary">
                        Yes
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default AlertDialog;