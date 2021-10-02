import React, {useEffect} from "react";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import {DialogContentText} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";


function ViewUserModal(props) {

    const {userData, open, handleClose} = props;

    useEffect(() => {
        if (open) {
        }
    }, [open]);

    return (
        <React.Fragment>
            <Dialog open={open} onClose={handleClose}
                    aria-labelledby="form-dialog-title"
                    aria-describedby="alert-dialog-description"
                    PaperProps={{
                        style: {
                            minHeight: '40vh',
                            minWidth: '40vw',
                        },
                    }}>
                <DialogTitle id="view-user">Kullanıcı Detay</DialogTitle>
                <DialogContent>
                    <div>
                        <h4>Ad</h4>
                        <DialogContentText>{userData.name}</DialogContentText>
                        <h4>Soyad</h4>
                        <DialogContentText>{userData.lastName}</DialogContentText>
                        <h4>İlçe</h4>
                        <DialogContentText>{userData.town}</DialogContentText>
                        <h4>Şehir</h4>
                        <DialogContentText>{userData.city}</DialogContentText>
                        <h4>Cinsiyet</h4>
                        <DialogContentText>{userData.gender}</DialogContentText>
                    </div>
                    <div style={{clear: "both"}}>
                        <h4>Son giriş</h4>
                        <DialogContentText>{userData.lastLoggedIn}</DialogContentText>
                        <h4>Kayıt Tarihi</h4>
                        <DialogContentText>{userData.createdAt}</DialogContentText>
                    </div>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        İptal
                    </Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );
}

export default ViewUserModal;