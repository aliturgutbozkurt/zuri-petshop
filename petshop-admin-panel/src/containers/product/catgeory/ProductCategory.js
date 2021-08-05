import React, {useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import AddCategoryModal from "./AddCategoryModal";
import CategoryList from "./CategoryList";
import CustomTable from "../../../components/CustomTable";


function ProductCategory(props) {

    const [open, setOpen] = React.useState(false);
    const [activeCategory, setActiveCategory] = useState(null);
    const [categories, setCategories] = useState([]);
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        console.log("close");
        setOpen(false);
    };

    const handleActiveCategoryChange = parentId => {
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
                    <AddCategoryModal open={open} handleClose={handleClose}/>
                </div>
                <div>
                    <h2>Ürün Kategorilerini Listele</h2>
                </div>
                <div>
                    <CategoryList
                        handleActiveCategoryChange={handleActiveCategoryChange}
                        handleChangeActiveCategories={handleChangeActiveCategories}
                        active={true}/>
                </div>
                <CustomTable rows={categories} columns={["id","name"]}/>
            </Container>
        </React.Fragment>
    )

}

export default ProductCategory;