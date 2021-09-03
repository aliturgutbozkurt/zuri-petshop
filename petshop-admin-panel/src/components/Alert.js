import Alert from '@material-ui/lab/Alert';
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContentText from "@material-ui/core/DialogContentText";


export const createSuccessAlert = (message) => {
    return (
        <Alert variant="outlined" severity="success">
            {message}
        </Alert>
    );
}

export const createErrorAlert = (message) => {
    return (
        <Alert variant="outlined" severity="error">
            {message}
        </Alert>
    );
}

export const createConfirmAlert = (open, dialogContentText, handleClose, handleConfirm) => {
    return (<Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
    >
        <DialogTitle id="alert-dialog-title">Uyarı</DialogTitle>
        <DialogContent>
            <DialogContentText id="alert-dialog-description">
                {dialogContentText}
            </DialogContentText>
        </DialogContent>
        <DialogActions>
            <Button onClick={handleClose} color="primary">
                İptal
            </Button>
            <Button onClick={handleConfirm} color="primary" autoFocus>
                Onayla
            </Button>
        </DialogActions>
    </Dialog>)
}