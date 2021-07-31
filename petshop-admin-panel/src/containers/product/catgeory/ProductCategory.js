import React from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import AddCategoryModal from "./AddCategoryModal";



function ProductCategory(props) {

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        console.log("close");
        setOpen(false);
    };

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
            </Container>
        </React.Fragment>
    )

}

export default ProductCategory;