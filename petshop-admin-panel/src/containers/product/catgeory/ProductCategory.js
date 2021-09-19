import React, {useEffect, useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import AddCategoryModal from "./AddCategoryModal";
import CategoryList from "./CategoryList";
import CustomTable from "../../../components/CustomTable";
import {deleteCategoryById, getCategoryById, pageCategoriesByParentId} from "./ProductCategoryService";
import ViewCategoryModal from "./ViewCategoryModal";
import UpdateCategoryModal from "./UpdateCategoryModal";
import {createConfirmAlert, createErrorAlert, createSuccessAlert} from "../../../components/Alert";


const columns = [
    "id", "Kategori Adı", "Oluşturan Kullanıcı","Kategori Resmi", "İşlemler"
]

const deleteDialogText = "Kategoriyi silmek istediğinizden emin misiniz?"
const createSuccessDialogText = "Kategoriyi oluşturma işlemi başarılı"
const createErrorDialogText = "Kategoriyi oluşturma işlemi başarılı"
const updateSuccessDialogText = "Kategoriyi güncelleme işlemi başarılı"
const updateErrorDialogText = "Kategoriyi güncelleme işlemi başarılı"
const deleteSuccessDialogText = "Kategoriyi güncelleme işlemi başarılı"
const deleteErrorDialogText = "Kategoriyi güncelleme işlemi başarılı"


function ProductCategory(props) {

    const [createModalOpen, setCreateModalOpen] = React.useState(false);
    const [viewModalOpen, setViewModalOpen] = React.useState(false);
    const [updateModalOpen, setUpdateModalOpen] = React.useState(false);
    const [activeCategory, setActiveCategory] = useState("");
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [count, setCount] = useState(0);
    const [upsertStatus, setUpsertStatus] = useState(false);
    const [categories, setCategories] = useState([]);
    const [viewCategoryData, setViewCategoryData] = useState([]);
    const [delteDialogOpen, setDelteDialogOpen] = useState(false);
    const [idToDelete, setIdToDelete] = useState(null);
    const [categoryDeleteSuccess, setCategoryDeleteSuccess] = useState(false);
    const [categoryDeleteError, setCategoryDeleteError] = useState(false);
    const [categoryCreateSuccess, setCategoryCreateSuccess] = useState(false);
    const [categoryCreateError, setCategoryCreateError] = useState(false);
    const [categoryUpdateSuccess, setCategoryUpdateSuccess] = useState(false);
    const [categoryUpdateError, setCategoryUpdateError] = useState(false);

    useEffect(() => {
        pageCategoriesByParentId(activeCategory, page, size).then(response => {
            setCategories(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
        pageCategoriesByParentId(activeCategory, page, size).then(response => {
            setCategories(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, [activeCategory, page, size]);

    useEffect(() => {
        if (categoryDeleteSuccess) {
            setTimeout(function () {
                setCategoryDeleteSuccess(false);
            }, 3000);
        }
    }, [categoryDeleteSuccess]);

    useEffect(() => {
        if (categoryDeleteError) {
            setTimeout(function () {
                setCategoryDeleteError(false);
            }, 3000);
        }
    }, [categoryDeleteError]);

    useEffect(() => {
        if (categoryCreateSuccess) {
            setTimeout(function () {
                setCategoryCreateSuccess(false);
            }, 3000);
        }
    }, [categoryCreateSuccess]);

    useEffect(() => {
        if (categoryCreateError) {
            setTimeout(function () {
                setCategoryCreateError(false);
            }, 3000);
        }
    }, [categoryCreateError]);

    useEffect(() => {
        if (categoryUpdateSuccess) {
            setTimeout(function () {
                setCategoryUpdateSuccess(false);
            }, 3000);
        }
    }, [categoryUpdateSuccess]);

    useEffect(() => {
        if (categoryUpdateError) {
            setTimeout(function () {
                setCategoryUpdateError(false);
            }, 3000);
        }
    }, [categoryUpdateError]);

    function pageCategoriesDefault() {
        pageCategoriesByParentId("", 0, 5).then(response => {
            setPage(0);
            setCategories(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }

    useEffect(() => {
        if (upsertStatus) {
            pageCategoriesDefault();
            setUpsertStatus(false);
        }
    }, [upsertStatus]);

    const handleDelete = (id) => {
        setDelteDialogOpen(true);
        setIdToDelete(id);
    }

    const handleDeleteAfterConfirm = () => {

        deleteCategoryById(idToDelete).then(response => {
            pageCategoriesDefault();
            setCategoryDeleteSuccess(true);
        }).catch(e => {
            console.log(e);
            setCategoryDeleteError(true);
        })
        setDelteDialogOpen(false);
    }

    const handleDeleteDialogClose = () => {
        setDelteDialogOpen(false);
    }

    const handleVisible = (id) => {
        setViewModalOpen(true);
        getCategoryById(id).then(response => {
            setViewCategoryData(response.data);
        }).catch(e => {
            console.log(e);
        })
    }

    const handleUpdate = id => {
        setUpdateModalOpen(true);
        getCategoryById(id).then(response => {
            setViewCategoryData(response.data);
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

    const handleUpdateResult = result => {
        if (result) {
            setCategoryUpdateSuccess(true);
        } else {
            setCategoryUpdateError(true);
        }
    }

    const handleCreateResult = result => {
        if (result) {
            setCategoryCreateSuccess(true);
        } else {
            setCategoryCreateError(true);
        }
    }

    const handlePageChange = page => {
        setPage(page);
    }

    const handleRowsPerPageChange = rowsPerPage => {
        setSize(rowsPerPage);
    }
    const handleActiveCategoryChange = parentId => {
        console.log(parentId);
        setActiveCategory(parentId);
    }


    return (
        <React.Fragment>
            <CssBaseline/>
            {createConfirmAlert(delteDialogOpen, deleteDialogText, handleDeleteDialogClose, handleDeleteAfterConfirm)}
            {categoryCreateSuccess && <div> {createSuccessAlert(createSuccessDialogText)} <br/><br/></div>}
            {categoryUpdateSuccess && <div>  {createSuccessAlert(updateSuccessDialogText)} <br/><br/></div>}
            {categoryDeleteSuccess && <div>  {createSuccessAlert(deleteSuccessDialogText)} <br/><br/></div>}
            {categoryCreateError && <div>  {createErrorAlert(createErrorDialogText)} <br/><br/></div>}
            {categoryUpdateError && <div>  {createErrorAlert(updateErrorDialogText)} <br/><br/></div>}
            {categoryDeleteError && <div>  {createErrorAlert(deleteErrorDialogText)} <br/><br/></div>}

            <Container maxWidth="xl">
                <div>
                    <Button variant="outlined" color="primary" onClick={handleCreateModalClickOpen}>
                        Kategori Ekle
                    </Button>
                    <AddCategoryModal open={createModalOpen}
                                      handleClose={handleCreateModalClose}
                                      handleUpdateUpsertStatus={handleUpdateUpsertStatus}
                                      handleCreateResult={handleCreateResult}
                    />
                    <UpdateCategoryModal open={updateModalOpen}
                                         handleClose={handleUpdateModalClose}
                                         handleUpdateUpsertStatus={handleUpdateUpsertStatus}
                                         categoryData={viewCategoryData}
                                         handleUpdateResult={handleUpdateResult}
                    />
                </div>
                <div>

                    <ViewCategoryModal open={viewModalOpen}
                                       categoryData={viewCategoryData}
                                       handleClose={handleViewModalClose}/>
                </div>
                <div>
                    <h2>Ürün Kategorilerini Listele</h2>
                </div>
                <div>
                    <CategoryList
                        handleActiveCategoryChange={handleActiveCategoryChange}
                        activeCategory={activeCategory}
                        handlePageChange={handlePageChange}
                        upsertStatus={upsertStatus}
                        handleLastDepthChange={() => {
                        }}
                        handleActiveDepthChange={() => {
                        }}
                        active={true}/>
                </div>
                <CustomTable rows={categories}
                             columns={columns}
                             handlePageChange={handlePageChange}
                             handleRowsPerPageChange={handleRowsPerPageChange}
                             isOperation={true}
                             activePage={page}
                             previewImageUrlField={"photoUrl"}
                             count={count}
                             handleDelete={handleDelete}
                             handleVisible={handleVisible}
                             handleUpdate={handleUpdate}
                             hiddenIndexes={[4, 5,6]}
                />
            </Container>
        </React.Fragment>
    )

}

export default ProductCategory;