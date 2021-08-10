import React, {useEffect, useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import AddCategoryModal from "./AddCategoryModal";
import CategoryList from "./CategoryList";
import CustomTable from "../../../components/CustomTable";
import {deleteCategoryById, getCategoryById, pageCategoriesByParentId} from "./ProductCategoryService";
import ViewCategoryModal from "./ViewCategoryModal";


const columns = [
    "id","Kategori Adı","Oluşturan Kullanıcı","İşlemler"
]


function ProductCategory(props) {

    const [createModalOpen, setCreateModalOpen] = React.useState(false);
    const [viewModalOpen, setViewModalOpen] = React.useState(false);
    const [activeCategory, setActiveCategory] = useState("");
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [count, setCount] = useState(0);
    const [upsertStatus, setUpsertStatus] = useState(false);
    const [categories, setCategories] = useState([]);
    const [viewCategoryData, setViewCategoryData] = useState([]);

    useEffect(() => {
        pageCategoriesByParentId(activeCategory,page,size).then(response => {
            setCategories(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
        pageCategoriesByParentId(activeCategory,page,size).then(response => {
            setCategories(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, [activeCategory, page, size]);

    function pageCategoriesDefault() {
        pageCategoriesByParentId("", 0, 5).then(response => {
            setCategories(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }

    useEffect(() => {
        if(upsertStatus) {
            pageCategoriesDefault();
            setUpsertStatus(false);
        }
    }, [upsertStatus]);

    const handleDelete = (id) =>{
        deleteCategoryById(id).then(response=> {
            pageCategoriesDefault();
        }).catch(e=> {
            console.log(e);
        })
    }

    const handleVisible = (id) => {
        setViewModalOpen(true);
        getCategoryById(id).then(response=> {
            setViewCategoryData(response.data);
        }).catch(e=> {
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

    const handleUpdateUpsertStatus = () => {
        setUpsertStatus(true);
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
            <Container maxWidth="xl">
                <div>
                    <Button variant="outlined" color="primary" onClick={handleCreateModalClickOpen}>
                        Kategori Ekle
                    </Button>
                    <AddCategoryModal open={createModalOpen} handleClose={handleCreateModalClose} handleUpdateUpsertStatus={handleUpdateUpsertStatus}/>
                </div>
                <div>

                    <ViewCategoryModal open={viewModalOpen}
                                       categoryData={viewCategoryData}
                                       handleClose={handleViewModalClose} />
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
                        handleLastDepthChange={()=>{}}
                        handleActiveDepthChange={()=>{}}
                        active={true}/>
                </div>
                <CustomTable rows={categories}
                             columns={columns}
                             handlePageChange={handlePageChange}
                             handleRowsPerPageChange={handleRowsPerPageChange}
                             isOperation={true}
                             count={count}
                             handleDelete={handleDelete}
                             handleVisible={handleVisible}
                             hiddenIndexes={[3,4]}
                />
            </Container>
        </React.Fragment>
    )

}

export default ProductCategory;