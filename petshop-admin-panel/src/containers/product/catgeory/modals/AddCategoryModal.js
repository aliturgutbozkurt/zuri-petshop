import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import React, {useEffect, useRef, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import {createCategoryImage, createProductCategory} from "../ProductCategoryService";
import CategoryList from "../CategoryList";
import "./CategoryModal.css";
import {
    emptyValidationErrors,
    isValid,
    nameError,
    depthError,
    photoUrlError,
    validateDepth,
    validateName, validatePhotoUrl
} from "../ProductCategoryValidation";
import {createProductImage} from "../../product/ProductImageService";
import {createErrorAlert, createSuccessAlert} from "../../../../components/Alert";

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
    const [photoUrl, setPhotoUrl] = useState("");
    const photoUrlRef = useRef(null);
    const [nameErrorArray, setNameErrorArray] = useState(nameError);
    const [depthErrorArray, setDepthErrorArray] = useState(depthError);
    const [photoUrlErrorArray, setPhotoUrlErrorArray] = useState(photoUrlError);
    const [activeCategory, setActiveCategory] = useState("");
    const [activeDepth, setActiveDepth] = useState(0);
    const [lastDepth, setLastDepth] = useState(false);
    const {open, handleClose, handleUpdateUpsertStatus} = props;
    const [photoSuccess, setPhotoSuccess] = useState(false);
    const [photoError, setPhotoError] = useState(false);

    useEffect(() => {
        if (open) {
            setName("");
            setPhotoUrl("");
            emptyValidationErrors();
            setNameErrorArray([]);
            setDepthErrorArray([]);
            setPhotoUrlErrorArray([]);
        }
    }, [open]);

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
        setNameErrorArray(validateName(name));
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


    const handleCreate = () => {
        const {handleCreateResult} = props;
        const request = {
            name: name,
            photoUrl: photoUrl,
            depth: activeDepth,
            parentId: activeCategory
        }

        // if (!isValid(request)) {
        //     setNameErrorArray(nameError);
        //     setDepthErrorArray(depthError);
        //     setPhotoUrlErrorArray(photoUrlError);
        //     return;
        // }
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
            {photoSuccess &&
            createSuccessAlert("Resim Yükleme Başarılı")
            }

            {photoError &&
            createErrorAlert("Resim Yükleme Başarısız")
            }
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
                < Button onClick={handleCreate} color="primary">
                    Ekle
                </Button>
                }
            </DialogActions>
        </Dialog>
    );

}

export default AddCategoryModal;