import React, {useEffect, useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import CustomTable from "../../../components/CustomTable";
import AddProductModal from "./AddProductModal";
import {deleteProductById, pageProducts} from "./ProductService";
import {deleteCategoryById} from "../catgeory/ProductCategoryService";
import {createConfirmAlert} from "../../../components/Alert";


const columns = [
    "id", "Ürün Adı", "Oluşturan Kullanıcı", "Fiyat", "Ürün Resmi", "İşlemler"
]
const deleteDialogText = "Ürünü silmek istediğinizden emin misiniz?"

function Product(props) {

    const [createModalOpen, setCreateModalOpen] = React.useState(false);
    const [viewModalOpen, setViewModalOpen] = React.useState(false);
    const [updateModalOpen, setUpdateModalOpen] = React.useState(false);

    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [count, setCount] = useState(0);
    const [upsertStatus, setUpsertStatus] = useState(false);
    const [products, setProducts] = useState([]);
    const [productDeleteSuccess, setProductDeleteSuccess] = useState(false);
    const [productDeleteError, setProductDeleteError] = useState(false);
    const [delteDialogOpen, setDelteDialogOpen] = useState(false);
    const [idToDelete, setIdToDelete] = useState(null);

    useEffect(() => {
        pageProducts(page, size).then(response => {
            setProducts(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
        pageProducts(page, size).then(response => {
            setProducts(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, [page, size]);

    function pageProductsDefault() {
        pageProducts(0, 5).then(response => {
            setPage(0);
            setProducts(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }

    useEffect(() => {
        if (upsertStatus) {
            pageProductsDefault();
            setUpsertStatus(false);
        }
    }, [upsertStatus]);

    useEffect(() => {
        if (productDeleteSuccess) {
            setTimeout(function () {
                setProductDeleteSuccess(false);
            }, 3000);
        }
    }, [productDeleteSuccess]);

    useEffect(() => {
        if (productDeleteError) {
            setTimeout(function () {
                setProductDeleteError(false);
            }, 3000);
        }
    }, [productDeleteError]);

    const handleDelete = (id) => {
        setDelteDialogOpen(true);
        setIdToDelete(id);
    }

    const handleDeleteAfterConfirm = () => {

        deleteProductById(idToDelete).then(response => {
            pageProductsDefault();
            setProductDeleteSuccess(true);
        }).catch(e => {
            console.log(e);
            setProductDeleteError(true);
        })
        setDelteDialogOpen(false);
    }

    const handleDeleteDialogClose = () => {
        setDelteDialogOpen(false);
    }

    const handleVisible = (id) => {
    }

    const handleUpdate = id => {
    }

    const handleCreateModalClickOpen = () => {
        setCreateModalOpen(true);
    };

    const handleCreateModalClose = () => {
        setCreateModalOpen(false);
    };

    const handleViewModalClose = () => {
        setViewModalOpen(false);
    }

    const handleUpdateModalClose = () => {
        setUpdateModalOpen(false);
    }

    const handleUpdateUpsertStatus = () => {
        setUpsertStatus(true);
    }

    const handlePageChange = page => {
        setPage(page);
    }

    const handleRowsPerPageChange = rowsPerPage => {
        setSize(rowsPerPage);
    }


    return (
        <React.Fragment>
            <CssBaseline/>
            {createConfirmAlert(delteDialogOpen, deleteDialogText, handleDeleteDialogClose, handleDeleteAfterConfirm)}
            <Container maxWidth="xl">
                <div>
                    <Button variant="outlined" color="primary" onClick={handleCreateModalClickOpen}>
                        Ürün Ekle
                    </Button>
                    <AddProductModal open={createModalOpen} handleClose={handleCreateModalClose}
                                     handleUpdateUpsertStatus={handleUpdateUpsertStatus}/>

                </div>
                <div>
                    <h2>Ürünleri Listele</h2>
                </div>
                <CustomTable rows={products}
                             columns={columns}
                             handlePageChange={handlePageChange}
                             handleRowsPerPageChange={handleRowsPerPageChange}
                             isOperation={true}
                             previewImageUrlField={"previewImageUrl"}
                             activePage={page}
                             count={count}
                             handleDelete={handleDelete}
                             handleVisible={handleVisible}
                             handleUpdate={handleUpdate}
                             hiddenIndexes={[3, 4, 6, 7, 8, 9]}
                />
            </Container>
        </React.Fragment>
    )

}

export default Product;