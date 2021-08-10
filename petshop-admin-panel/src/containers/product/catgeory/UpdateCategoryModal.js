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

function UpdateCategoryModal(props) {
    const {open, handleClose, handleUpdateUpsertStatus, categoryData} = props;
    const [name, setName] = useState("");
    const [activeCategory, setActiveCategory] = useState("");
    const [activeDepth, setActiveDepth] = useState(0);
    const [lastDepth, setLastDepth] = useState(false);


    useEffect(() => {
        if (open) {
        }
    }, [open]);

    useEffect(() => {
        if (open) {
            setName(categoryData.name);
            setActiveCategory(categoryData.parent ? categoryData.parent.id : null);
        }
    }, [open,categoryData]);


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

    const handleActiveNameChange = name => {
        setName(name);
    }

    const handleUpdate = () => {
        const request = {
            id: categoryData.id,
            name: name,
            depth: activeDepth,
            parentId: activeCategory
        }
        updateProductCategory(request).then(response => {
            setName("");
            handleUpdateUpsertStatus();
            handleClose();
        }).catch(e => {
            console.error(e);
        })
    }


    return (
        <React.Fragment>
            <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="add-category">Kategori Ekle</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Eğer Ana Kategori ekliyorsanız herhangi bir kategori seçmeyiniz. Eğer alt
                        kategori ekliyorsanız ilgili üst kategorileri seçerek ilerleyiniz.
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