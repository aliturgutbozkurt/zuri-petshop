import React, {useEffect, useState} from "react";
import Button from '@material-ui/core/Button';
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import CustomTable from "../../../components/CustomTable";
import AddProductModal from "./AddProductModal";


const columns = [
    "id", "Ürün Adı", "Oluşturan Kullanıcı", "İşlemler"
]


function Product(props) {

    const [createModalOpen, setCreateModalOpen] = React.useState(false);
    const [viewModalOpen, setViewModalOpen] = React.useState(false);
    const [updateModalOpen, setUpdateModalOpen] = React.useState(false);

    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [count, setCount] = useState(0);
    const [upsertStatus, setUpsertStatus] = useState(false);
    const [products, setProducts] = useState([]);

    useEffect(() => {
    }, []);

    useEffect(() => {
    }, [page, size]);

    function pageProductsDefault() {
    }

    useEffect(() => {
        if (upsertStatus) {
            pageProductsDefault();
            setUpsertStatus(false);
        }
    }, [upsertStatus]);

    const handleDelete = (id) => {
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
                             count={count}
                             handleDelete={handleDelete}
                             handleVisible={handleVisible}
                             handleUpdate={handleUpdate}
                             hiddenIndexes={[3, 4, 5]}
                />
            </Container>
        </React.Fragment>
    )

}

export default Product;