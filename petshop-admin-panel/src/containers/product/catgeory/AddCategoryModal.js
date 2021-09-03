import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import {createProductCategory} from "./ProductCategoryService";
import CategoryList from "./CategoryList";
import {
    emptyValidationErrors,
    isValid,
    nameError,
    depthError,
    validateDepth,
    validateName
} from "./ProductCategoryValidation";

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
    const [nameErrorArray, setNameErrorArray] = useState(nameError);
    const [depthErrorArray, setDepthErrorArray] = useState(depthError);
    const [activeCategory, setActiveCategory] = useState("");
    const [activeDepth, setActiveDepth] = useState(0);
    const [lastDepth, setLastDepth] = useState(false);
    const {open, handleClose, handleUpdateUpsertStatus} = props;

    useEffect(() => {
        if (open) {
            setName("");
            emptyValidationErrors();
            setNameErrorArray([]);
            setDepthErrorArray([]);
        }
    }, [open]);


    const handleNameChange = (e) => {
        const name = e.target.value;
        setNameErrorArray(validateName(name))
        setName(name);
    }


    const handleActiveCategoryChange = parentId => {
        setActiveCategory(parentId);
    }

    const handleActiveDepthChange = activeDepth => {
        setDepthErrorArray(validateDepth(activeDepth));
        setActiveDepth(activeDepth);
    }

    const handleLastDepthChange = status => {
        setLastDepth(status);
    }


    const handleCreate = () => {
        const {handleCreateResult} = props;
        const request = {
            name: name,
            depth: activeDepth,
            parentId: activeCategory
        }

        if (!isValid(request)) {
            setNameErrorArray(nameError);
            setDepthErrorArray(depthError);
            return;
        }
        createProductCategory(request).then(response => {
            console.log("created");
            setName("");
            handleUpdateUpsertStatus();
            handleCreateResult(true);
            handleClose();
        }).catch(e => {
            console.error(e);
            handleCreateResult(false);
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
                    active={open}
                />
                {!lastDepth &&
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
                }
                <br/>
                <br/>
                <div className="error">{nameErrorArray.map(error => error)}</div>
                <div className="eroor">{depthErrorArray.map(error => error)}</div>
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