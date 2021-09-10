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
import Divider from "@material-ui/core/Divider";
import {createProductCategory} from "../catgeory/ProductCategoryService";
import {createProductImage} from "./ProductImageService";
import {createErrorAlert, createSuccessAlert} from "../../../components/Alert";
import {createProduct} from "./ProductService";
import "./AddProductModal.css";

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
    const [photoSuccess, setPhotoSuccess] = useState(false);
    const [photoError, setPhotoError] = useState(false);
    const [activeCategory, setActiveCategory] = useState("");
    const {open, handleClose, handleUpdateUpsertStatus} = props;

    useEffect(() => {
        if (open) {
            setName("");
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


    const handleNameChange = (e) => {
        setName(e.target.value);
    }

    const handleAboutChange = (e) => {
        setAbout(e.target.value);
    }

    const handleActiveCategoryChange = categoryId => {
        setActiveCategory(categoryId);
    }

    const handlePhoto1Change = (e) => {
        e.preventDefault();
        createProductImage(e.target.files[0]).then(response => {
            setPhoto1(response.data);
            setPhotoSuccess(true);
        }).catch(e => {
            console.log(e);
            setPhotoError(true);
        })
    }

    const handlePhoto2Change = (e) => {
        e.preventDefault();
        createProductImage(e.target.files[0]).then(response => {
            setPhoto2(response.data);
            setPhotoSuccess(true);
        }).catch(e => {
            console.log(e);
            setPhotoError(true);
        })
    }

    const handlePhoto3Change = (e) => {
        e.preventDefault();
        createProductImage(e.target.files[0]).then(response => {
            setPhoto3(response.data);
            setPhotoSuccess(true);
        }).catch(e => {
            console.log(e);
            setPhotoError(true);
        })
    }

    const handlePhoto4Change = (e) => {
        e.preventDefault();
        createProductImage(e.target.files[0]).then(response => {
            setPhoto4(response.data);
            setPhotoSuccess(true);
        }).catch(e => {
            console.log(e);
            setPhotoError(true);
        })
    }


    const createImageSet = () => {
        const imageSet = [];
        if (photo1) {
            imageSet.push({url: photo1, orderNumber: 0})
        }
        if (photo2) {
            imageSet.push({url: photo2, orderNumber: 1})
        }
        if (photo3) {
            imageSet.push({url: photo3, orderNumber: 2})
        }
        if (photo4) {
            imageSet.push({url: photo4, orderNumber: 3})
        }
        return imageSet;
    }

    const resetFormValues = () => {
        setName("");
        setAbout("");
        setPhoto1("");
        setPhoto2("");
        setPhoto3("");
        setPhoto4("");
        setActiveCategory("");
    }

    const handleCreate = () => {
        const request = {
            name: name,
            about: about,
            images: createImageSet(),
            categoryId: activeCategory
        }
        createProduct(request).then(response => {
            console.log("created");
            resetFormValues();
            handleUpdateUpsertStatus();
            handleClose();
        }).catch(e => {
            console.error(e);
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
                               accept="image/png, image/jpeg"
                               onChange={handlePhoto1Change}
                        />
                        <Button onClick={() => {
                            photo1Ref.current.value = null
                            setPhoto1("");
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <div className="productImageItem"><img src={photo1}/></div>
                    <Divider/>
                    <div>
                        <p>Foto 2</p>
                        <input type="file"
                               ref={photo2Ref}
                               id="photo2" name="photo2"
                               accept="image/png, image/jpeg"
                               onChange={handlePhoto2Change}/>
                        <Button onClick={() => {
                            photo2Ref.current.value = null;
                            setPhoto2("");
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <div className="productImageItem"><img src={photo2}/></div>
                    <Divider/>
                    <div>
                        <p>Foto 3</p>
                        <input type="file"
                               ref={photo3Ref}
                               id="photo3" name="photo3"
                               accept="image/png, image/jpeg"
                               onChange={handlePhoto3Change}
                        />
                        <Button onClick={() => {
                            photo3Ref.current.value = null;
                            setPhoto3("");
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <div className="productImageItem"><img src={photo3}/></div>
                    <Divider/>
                    <div>
                        <p>Foto 4</p>
                        <input type="file"
                               ref={photo4Ref}
                               id="photo4" name="photo4"
                               accept="image/png, image/jpeg"
                               onChange={handlePhoto4Change}
                        />

                        <Button onClick={() => {
                            photo4Ref.current.value = null;
                            setPhoto4("");
                        }} color="primary">
                            Temizle
                        </Button>
                    </div>
                    <div className="productImageItem"><img src={photo4}/></div>
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