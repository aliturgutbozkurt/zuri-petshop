import React, {useEffect, useRef, useState} from "react";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import {DialogContentText} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import TextField from "@material-ui/core/TextField";
import {createCategoryImage, updateProductCategory} from "../ProductCategoryService";
import UpdateCategoryList from "../UpdateCategoryList";
import "./CategoryModal.css";
import {
    depthError,
    emptyValidationErrors,
    isValid,
    nameError, photoUrlError,
    validateDepth,
    validateName, validatePhotoUrl
} from "../ProductCategoryValidation";
import {createErrorAlert, createSuccessAlert} from "../../../../components/Alert";

function UpdateCategoryModal(props) {
    const {open, handleClose, handleUpdateUpsertStatus, categoryData} = props;
    const [name, setName] = useState("");
    const [photoUrl, setPhotoUrl] = useState("");
    const photoUrlRef = useRef(null);
    const [nameErrorArray, setNameErrorArray] = useState(nameError);
    const [depthErrorArray, setDepthErrorArray] = useState(depthError);
    const [photoUrlErrorArray, setPhotoUrlErrorArray] = useState(photoUrlError);
    const [activeCategory, setActiveCategory] = useState("");
    const [activeDepth, setActiveDepth] = useState(0);
    const [lastDepth, setLastDepth] = useState(false);
    const [photoSuccess, setPhotoSuccess] = useState(false);
    const [photoError, setPhotoError] = useState(false);


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
            setPhotoUrl(categoryData.photoUrl);
            setActiveCategory(categoryData.parent ? categoryData.parent.id : null);
        }
    }, [open,categoryData]);

    useEffect(() => {
        if (photoSuccess) {
            setTimeout(function () {
                setPhotoSuccess(false);
            }, 3000);
        }
    }, [photoSuccess]);

    useEffect(() => {
        if (photoError) {
            setTimeout(function () {
                setPhotoError(false);
            }, 3000);
        }
    }, [photoError]);

    useEffect(() => {
        let url = photoUrl;
        setPhotoUrlErrorArray(validatePhotoUrl(url));
    }, [photoUrl]);


    const handleNameChange = (e) => {
        const name = e.target.value;
        setNameErrorArray(validateName(name))
        setName(name);
    }

    const handlePhotoUrlChange = (e) => {
        e.preventDefault();

        createCategoryImage(e.target.files[0]).then(response => {
            setPhotoUrl(response.data);
            setPhotoSuccess(true);
        }).catch(e => {
            console.log(e);
            setPhotoError(true);
        })
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
            photoUrl: photoUrl,
            depth: activeDepth,
            parentId: activeCategory
        }
        if (!isValid(request)) {
            setNameErrorArray(nameError);
            setDepthErrorArray(depthError);
            setPhotoUrlErrorArray(photoUrlError);
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
                {photoSuccess &&
                createSuccessAlert("Resim Yükleme Başarılı")
                }

                {photoError &&
                createErrorAlert("Resim Yükleme Başarısız")
                }
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
                    <div>
                        <p>Foto</p>
                        <input ref={photoUrlRef} type="file"
                               id="photoUrl" name="photoUrl"
                               accept="image/png, image/jpeg"
                               onChange={handlePhotoUrlChange}
                        />
                        <Button onClick={() => {
                            photoUrlRef.current.value = null
                            setPhotoUrl("");
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <div className="error">{photoUrlErrorArray.map(error => error)}</div>
                    {photoUrl && <div className="productImageItem"><img src={photoUrl}/></div>}
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