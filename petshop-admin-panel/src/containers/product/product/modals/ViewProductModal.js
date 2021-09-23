import React, {useEffect, useState} from "react";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import {DialogContentText} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import {generateCategoryStr} from "../../ProductUtil";


function ViewProductModal(props) {

    const [categoryStr, setCategoryStr] = useState("");
    const {productData, categoryData, open, handleClose} = props;

    useEffect(() => {
        if (open) {

        }
    }, [open]);

    useEffect(() => {
        if (categoryData && categoryData.name) {
            setCategoryStr(generateCategoryStr(categoryData));
        }
    }, [categoryData]);

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
                <DialogTitle id="view-category">Ürün Detay</DialogTitle>
                <DialogContent>
                    <div>
                        <h4>Kategori</h4>
                        <DialogContentText>{categoryStr}</DialogContentText>
                        <h4>Ürün İsmi</h4>
                        <DialogContentText>{productData.name}</DialogContentText>
                        <h4>Ürün Açıklama</h4>
                        <DialogContentText>{productData.about}</DialogContentText>
                        <h4>Ürün Eski Fiyatı</h4>
                        <DialogContentText>Ürünün eski fiyatı sıfır ise indirim söz konusu değildir.</DialogContentText>
                        <DialogContentText>{productData.oldPrice}</DialogContentText>
                        <h4>Ürün Fiyatı</h4>
                        <DialogContentText>{productData.price}</DialogContentText>
                    </div>
                    <div>
                        <h4>Ürün Resmileri</h4>

                        {productData.images && productData.images.map((image, index) =>
                            <div key={index} className="productImageItem" style={{float: "left"}}><img src={image.url}/></div>)}
                    </div>
                    <div style={{clear: "both"}}>
                        <h4>Oluşturulma Tarihi</h4>
                        <DialogContentText>{productData.createdAt}</DialogContentText>
                        <h4>Oluşturan Kullanıcı</h4>
                        <DialogContentText>{productData.createdBy}</DialogContentText>
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

export default ViewProductModal;