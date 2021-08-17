import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import React, {useEffect, useRef, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import CategoryList from "../catgeory/CategoryList";
import TextareaAutosize from "@material-ui/core/TextareaAutosize";
import {Label} from "@material-ui/icons";
import Divider from "@material-ui/core/Divider";

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
    },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
}));


function AddProductModal(props) {
    const classes = useStyles();
    const [name, setName] = useState("");
    const [photo1, setPhoto1] = useState("");
    const photo1Ref = useRef(null);
    const [photo2, setPhoto2] = useState("");
    const photo2Ref = useRef(null);
    const [photo3, setPhoto3] = useState("");
    const photo3Ref = useRef(null);
    const [photo4, setPhoto4] = useState("");
    const photo4Ref = useRef(null);
    const [about, setAbout] = useState("");
    const [activeCategory, setActiveCategory] = useState("");
    const {open, handleClose, handleUpdateUpsertStatus} = props;

    useEffect(() => {
        if (open) {
            setName("");
        }
    }, [open]);


    const handleNameChange = (e) => {
        setName(e.target.value);
    }

    const handleAboutChange = (e) => {
        setName(e.target.value);
    }

    const handleActiveCategoryChange = categoryId => {
        setActiveCategory(categoryId);
    }

    const handleCreate = () => {
    }


    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
            <DialogTitle id="add-category">Ürün Ekle</DialogTitle>
            <DialogContent>
                <form className={classes.root} noValidate autoComplete="off">
                    <DialogContentText>
                        İlgili üst kategorileri seçerek ilerleyiniz.
                    </DialogContentText>

                    <CategoryList
                        handleActiveCategoryChange={handleActiveCategoryChange}
                        handlePageChange={() => {
                        }}
                        handleLastDepthChange={() => {
                        }}
                        handleActiveDepthChange={() => {
                        }}
                        active={open}
                    />

                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        value={name}
                        onChange={handleNameChange}
                        label="Ürün Adı"
                        type="name"
                        fullWidth
                        variant="outlined"
                    />

                    <br/>
                    <br/>
                    <TextField
                        id="about"
                        label="Ürün Açıklması"
                        multiline
                        autoFocus
                        rows={4}
                        onChange={handleAboutChange}
                        fullWidth
                        variant="outlined"
                    />
                    -
                    <div>
                        <p>Foto 1</p>
                        <input ref={photo1Ref} type="file"
                               id="photo1" name="photo1"
                               accept="image/png, image/jpeg"/>
                        <Button onClick={() => {
                            photo1Ref.current.value = null
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <Divider/>
                    <div>
                        <p>Foto 2</p>
                        <input type="file"
                               ref={photo2Ref}
                               id="photo2" name="photo2"
                               accept="image/png, image/jpeg"/>
                        <Button onClick={() => {
                            photo2Ref.current.value = null
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <Divider/>
                    <div>
                        <p>Foto 3</p>
                        <input type="file"
                               ref={photo3Ref}
                               id="photo3" name="photo3"
                               accept="image/png, image/jpeg"/>
                        <Button onClick={() => {
                            photo3Ref.current.value = null
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <Divider/>
                    <div>
                        <p>Foto 4</p>
                        <input type="file"
                               ref={photo4Ref}
                               id="photo4" name="photo4"
                               accept="image/png, image/jpeg"/>
                        <Button onClick={() => {
                            photo4Ref.current.value = null
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                </form>

            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    İptal
                </Button>

                < Button onClick={handleCreate} color="primary">
                    Ekle
                </Button>

            </DialogActions>
        </Dialog>
    );

}

export default AddProductModal;