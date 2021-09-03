import React, {useEffect, useState} from "react";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import {DialogContentText} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import TextField from "@material-ui/core/TextField";
import {updateProductCategory} from "./ProductCategoryService";
import UpdateCategoryList from "./UpdateCategoryList";
import {
    depthError,
    emptyValidationErrors,
    isValid,
    nameError,
    validateDepth,
    validateName
} from "./ProductCategoryValidation";

function UpdateCategoryModal(props) {
    const {open, handleClose, handleUpdateUpsertStatus, categoryData} = props;
    const [name, setName] = useState("");
    const [nameErrorArray, setNameErrorArray] = useState(nameError);
    const [depthErrorArray, setDepthErrorArray] = useState(depthError);
    const [activeCategory, setActiveCategory] = useState("");
    const [activeDepth, setActiveDepth] = useState(0);
    const [lastDepth, setLastDepth] = useState(false);


    useEffect(() => {
        if (open) {
            emptyValidationErrors();
            setNameErrorArray([]);
            setDepthErrorArray([]);
        }
    }, [open]);

    useEffect(() => {
        if (open) {
            setName(categoryData.name);
            setActiveCategory(categoryData.parent ? categoryData.parent.id : null);
        }
    }, [open,categoryData]);


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

    const handleActiveNameChange = name => {
        setName(name);
    }

    const handleUpdate = () => {
        const {handleUpdateResult} = props;
        const request = {
            id: categoryData.id,
            name: name,
            depth: activeDepth,
            parentId: activeCategory
        }
        if (!isValid(request)) {
            setNameErrorArray(nameError);
            setDepthErrorArray(depthError);
            return;
        }
        updateProductCategory(request).then(response => {
            setName("");
            handleUpdateUpsertStatus();
            handleUpdateResult(true);
            handleClose();
        }).catch(e => {
            console.error(e);
            handleUpdateResult(false);
        })
    }


    return (
        <React.Fragment>
            <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="add-category">Kategori Düzenle</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Eğer Ana Kategori düzenliyorsanız herhangi bir kategori seçmeyiniz. Eğer alt
                        kategori düzenliyorsanız ilgili üst kategorileri seçerek ilerleyiniz.
                    </DialogContentText>

                    <UpdateCategoryList
                        handleActiveCategoryChange={handleActiveCategoryChange}
                        handleLastDepthChange={handleLastDepthChange}
                        handleActiveNameChange={handleActiveNameChange}
                        handleActiveDepthChange={handleActiveDepthChange}
                        active={open}
                        categoryData={categoryData}
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
                    />}
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
                    < Button onClick={handleUpdate} color="primary">
                        Güncelle
                    </Button>
                    }
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );

}

export default UpdateCategoryModal;