import {Button, Dialog, DialogActions, DialogContent, DialogTitle, useMediaQuery} from "@mui/material";
import * as React from 'react';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import {useTheme} from "@mui/material/styles";

const DialogTemplate = (props) => {

    const {
        open,
        onClose,
        title,
        primaryActionName,
        onPrimaryAction,
        secondaryActionName,
        onSecondaryAction,
        children,
        ...other
    } = props;
    const theme = useTheme();
    const fullScreen = useMediaQuery(theme.breakpoints.down("sm"));

    return (
        <Dialog
            fullWidth={true}
            fullScreen={fullScreen}
            onClose={onClose}
            open={open}
        >
            <DialogTitle sx={{m: 0, p: 2}} {...other}>

                {title ? title : "Title"}

                {onClose ? (
                    <IconButton
                        aria-label="close"
                        onClick={onClose}
                        sx={{
                            position: "absolute",
                            right: 8,
                            top: 8,
                            color: (theme) => theme.palette.grey[500],
                        }}
                    >
                        <CloseIcon/>
                    </IconButton>
                ) : null}

            </DialogTitle>

            <DialogContent dividers>
                {children}
            </DialogContent>

            <DialogActions>

                {
                    primaryActionName && onPrimaryAction ?
                        <Button color={"secondary"} onClick={onPrimaryAction}>
                            {primaryActionName}
                        </Button>
                        :
                        null
                }
                {
                    secondaryActionName && onSecondaryAction ?
                        <Button color={"secondary"} onClick={onSecondaryAction}>
                            {secondaryActionName}
                        </Button>
                        :
                        null
                }

            </DialogActions>
        </Dialog>
    );

}

export default DialogTemplate;