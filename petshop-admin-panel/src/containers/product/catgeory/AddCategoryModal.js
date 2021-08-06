import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import Select from "@material-ui/core/Select";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import React, {useEffect, useState} from "react";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import {makeStyles} from '@material-ui/core/styles';
import {createProductCategory, listCategoriesByParentId, listCategoriesByParentIdNull} from "./ProductCategoryService";
import CategoryList from "./CategoryList";

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
    },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
}));


function AddCategoryModal(props) {
    const classes = useStyles();
    const [name, setName] = useState("");
    const [activeCategory, setActiveCategory] = useState("");
    const {open, handleClose} = props;

    useEffect(() => {
        if (open) {
           setName("");
        }
    }, [open]);


    const handleNameChange = (e) => {
        setName(e.target.value);
    }



    const handleActiveCategoryChange = parentId =>{
        setActiveCategory(parentId);
    }


    const handleCreate = () => {
        const request = {
            name: name,
            parentId: activeCategory
        }
        createProductCategory(request).then(response => {
            console.log("created");
            setName("");
            handleClose();
        }).catch(e => {
            console.error(e);
        })
    }


    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
            <DialogTitle id="add-category">Kategori Ekle</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Eğer Ana Kategori ekliyorsanız herhangi bir kategori seçmeyiniz. Eğer alt
                    kategori ekliyorsanız ilgili üst kategorileri seçerek ilerleyiniz.
                </DialogContentText>

                <CategoryList
                    handleActiveCategoryChange={handleActiveCategoryChange}
                    handlePageChange={()=>{}}
                    active={open}/>

                <TextField
                    autoFocus
                    margin="dense"
                    id="name"
                    value={name}
                    onChange={handleNameChange}
                    label="Kategori Adı"
                    type="name"
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    İptal
                </Button>
                <Button onClick={handleCreate} color="primary">
                    Ekle
                </Button>
            </DialogActions>
        </Dialog>
    );

}

export default AddCategoryModal;