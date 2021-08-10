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
    const [activeDepth, setActiveDepth] = useState(0);
    const [lastDepth, setLastDepth] = useState(false);
    const {open, handleClose, handleUpdateUpsertStatus} = props;

    useEffect(() => {
        if (open) {
            setName("");
        }
    }, [open]);


    const handleNameChange = (e) => {
        setName(e.target.value);
    }


    const handleActiveCategoryChange = parentId => {
        setActiveCategory(parentId);
    }

    const handleActiveDepthChange = activeDepth => {
        setActiveDepth(activeDepth)
    }

    const handleLastDepthChange = status => {
        setLastDepth(status);
    }


    const handleCreate = () => {
        const request = {
            name: name,
            depth: activeDepth,
            parentId: activeCategory
        }
        createProductCategory(request).then(response => {
            console.log("created");
            setName("");
            handleUpdateUpsertStatus();
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
                    handlePageChange={() => {
                    }}
                    handleLastDepthChange={handleLastDepthChange}
                    handleActiveDepthChange={handleActiveDepthChange}
                    active={open}/>
                {!lastDepth  &&
                <TextField
                    autoFocus
                    margin="dense"
                    id="name"
                    value={name}
                    onChange={handleNameChange}
                    label="Kategori Adı"
                    type="name"
                    fullWidth
                />}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    İptal
                </Button>
                {!lastDepth &&
                < Button onClick={handleCreate} color="primary">
                    Ekle
                </Button>
                }
            </DialogActions>
        </Dialog>
    );

}

export default AddCategoryModal;