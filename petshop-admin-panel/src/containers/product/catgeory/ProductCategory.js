import React, {useEffect, useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import AddCategoryModal from "./AddCategoryModal";
import CategoryList from "./CategoryList";
import CustomTable from "../../../components/CustomTable";
import { pageCategoriesByParentId} from "./ProductCategoryService";


const columns = [
    "id","Kategori Adı","İşlemler"
]


function ProductCategory(props) {

    const [open, setOpen] = React.useState(false);
    const [activeCategory, setActiveCategory] = useState("");
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [count, setCount] = useState(0);
    const [upsertStatus, setUpsertStatus] = useState(false);
    const [categories, setCategories] = useState([]);

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

    useEffect(() => {
        if(upsertStatus) {
            pageCategoriesByParentId("", 0, 5).then(response => {
                setCategories(response.data.content);
                setCount(response.data.totalElements);
            }).catch(e => {
                console.log(e);
            });
            setUpsertStatus(false);
        }
    }, [upsertStatus]);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

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

    const handleChangeActiveCategories = categories => {
        setCategories(categories);
    }

    return (
        <React.Fragment>
            <CssBaseline/>
            <Container maxWidth="xl">
                <div>
                    <Button variant="outlined" color="primary" onClick={handleClickOpen}>
                        Kategori Ekle
                    </Button>
                    <AddCategoryModal open={open} handleClose={handleClose} handleUpdateUpsertStatus={handleUpdateUpsertStatus}/>
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
                        active={true}/>
                </div>
                <CustomTable rows={categories}
                             columns={columns}
                             handlePageChange={handlePageChange}
                             handleRowsPerPageChange={handleRowsPerPageChange}
                             isOperation={true}
                            count={count}
                />
            </Container>
        </React.Fragment>
    )

}

export default ProductCategory;