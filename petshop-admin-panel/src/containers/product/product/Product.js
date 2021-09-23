import React, {useEffect, useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import CustomTable from "../../../components/CustomTable";
import AddProductModal from "./modals/AddProductModal";
import {deleteProductById, getProductById, pageProducts} from "./ProductService";
import {createConfirmAlert, createErrorAlert, createSuccessAlert} from "../../../components/Alert";
import ViewProductModal from "./modals/ViewProductModal";
import UpdateCategoryModal from "../catgeory/modals/UpdateCategoryModal";
import UpdateProductModal from "./modals/UpdateProductModal";
import {getCategoryById} from "../catgeory/ProductCategoryService";


const columns = [
    "id", "Ürün Adı", "Oluşturan Kullanıcı", "Fiyat", "Ürün Resmi", "İşlemler"
]

const deleteDialogText = "Ürünü silmek istediğinizden emin misiniz?"
const createSuccessDialogText = "Ürünü oluşturma işlemi başarılı"
const createErrorDialogText = "Ürünü oluşturma işlemi başarısız"
const updateSuccessDialogText = "Ürünü güncelleme işlemi başarılı"
const updateErrorDialogText = "Ürünü güncelleme işlemi başarısız"
const deleteSuccessDialogText = "Ürünü güncelleme işlemi başarılı"
const deleteErrorDialogText = "Ürünü güncelleme işlemi başarısız"

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
    const [productUpdateSuccess, setProductUpdateSuccess] = useState(false);
    const [productUpdateError, setProductUpdateError] = useState(false);
    const [productCreateSuccess, setProductCreateSuccess] = useState(false);
    const [productCreateError, setProductCreateError] = useState(false);
    const [delteDialogOpen, setDelteDialogOpen] = useState(false);
    const [idToDelete, setIdToDelete] = useState(null);
    const [viewProductData, setViewProductData] = useState([]);

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
        if (productCreateSuccess) {
            setTimeout(function () {
                setProductCreateSuccess(false);
            }, 3000);
        }
    }, [productCreateSuccess]);

    useEffect(() => {
        if (productCreateError) {
            setTimeout(function () {
                setProductCreateError(false);
            }, 3000);
        }
    }, [productCreateError]);

    useEffect(() => {
        if (productUpdateSuccess) {
            setTimeout(function () {
                setProductUpdateSuccess(false);
            }, 3000);
        }
    }, [productUpdateSuccess]);

    useEffect(() => {
        if (productUpdateError) {
            setTimeout(function () {
                setProductUpdateError(false);
            }, 3000);
        }
    }, [productUpdateError]);

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

    const handleUpdateResult = result => {
        if (result) {
            setProductUpdateSuccess(true);
        } else {
            setProductUpdateError(true);
        }
    }

    const handleCreateResult = result => {
        if (result) {
            setProductCreateSuccess(true);
        } else {
            setProductCreateError(true);
        }
    }

    const handleDeleteDialogClose = () => {
        setDelteDialogOpen(false);
    }

    const handleVisible = (id) => {
        setViewModalOpen(true);
        getProductById(id).then(response => {
            setViewProductData(response.data);
        }).catch(e => {
            console.log(e);
        })
    }

    const handleUpdate = id => {
        setUpdateModalOpen(true);
        getProductById(id).then(response => {
            setViewProductData(response.data);
        }).catch(e => {
            console.log(e);
        })
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
            {productCreateSuccess && <div> {createSuccessAlert(createSuccessDialogText)} <br/><br/></div>}
            {productUpdateSuccess && <div>  {createSuccessAlert(updateSuccessDialogText)} <br/><br/></div>}
            {productDeleteSuccess && <div>  {createSuccessAlert(deleteSuccessDialogText)} <br/><br/></div>}
            {productCreateError && <div>  {createErrorAlert(createErrorDialogText)} <br/><br/></div>}
            {productUpdateError && <div>  {createErrorAlert(updateErrorDialogText)} <br/><br/></div>}
            {productDeleteError && <div>  {createErrorAlert(deleteErrorDialogText)} <br/><br/></div>}
            <Container maxWidth="xl">
                <div>
                    <Button variant="outlined" color="primary" onClick={handleCreateModalClickOpen}>
                        Ürün Ekle
                    </Button>
                    <AddProductModal open={createModalOpen} handleClose={handleCreateModalClose}
                                     handleUpdateUpsertStatus={handleUpdateUpsertStatus}
                                     handleCreateResult={handleCreateResult}
                    />
                    <UpdateProductModal open={updateModalOpen}
                                         handleClose={handleUpdateModalClose}
                                         handleUpdateUpsertStatus={handleUpdateUpsertStatus}
                                         categoryData={viewProductData.category}
                                         productData={viewProductData}
                                         handleUpdateResult={handleUpdateResult}
                    />
                    <ViewProductModal open={viewModalOpen}
                                      productData={viewProductData}
                                      categoryData={viewProductData.category}
                                      handleClose={handleViewModalClose}/>

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