import React, {useEffect, useState} from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import CustomTable from "../../components/CustomTable.js";
import {pageUsers, getUserById, deleteUserById} from "./UserService.js";
import {createConfirmAlert, createErrorAlert, createSuccessAlert} from "../../components/Alert.js";
import ViewUserModal from "./modal/ViewUserModal.js";


const columns = [
    "id", "Ad", "Email", "Telefon", "Lokasyon", "İşlemler"
]

const deleteDialogText = "Kullanıcıyı silmek istediğinizden emin misiniz?"
const deleteSuccessDialogText = "Ürünü güncelleme işlemi başarılı"
const deleteErrorDialogText = "Ürünü güncelleme işlemi başarısız"

function User(props) {

    const [viewModalOpen, setViewModalOpen] = React.useState(false);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [count, setCount] = useState(0);
    const [users, setUsers] = useState([]);
    const [userDeleteSuccess, setUserDeleteSuccess] = useState(false);
    const [userDeleteError, setUserDeleteError] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [idToDelete, setIdToDelete] = useState(null);
    const [viewUserData, setViewUserData] = useState([]);

    useEffect(() => {
        pageUsers(page, size).then(response => {
            setUsers(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
        pageUsers(page, size).then(response => {
            setUsers(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }, [page, size]);

    function pageUsersDefault() {
        pageUsers(0, 5).then(response => {
            setPage(0);
            setUsers(response.data.content);
            setCount(response.data.totalElements);
        }).catch(e => {
            console.log(e);
        });
    }

    useEffect(() => {
        if (userDeleteSuccess) {
            setTimeout(function () {
                setUserDeleteSuccess(false);
            }, 3000);
        }
    }, [userDeleteSuccess]);

    useEffect(() => {
        if (userDeleteError) {
            setTimeout(function () {
                setUserDeleteError(false);
            }, 3000);
        }
    }, [userDeleteError]);

    const handleDelete = (id) => {
        setDeleteDialogOpen(true);
        setIdToDelete(id);
    }

    const handleDeleteAfterConfirm = () => {

        deleteUserById(idToDelete).then(response => {
            pageUsersDefault();
            setUserDeleteSuccess(true);
        }).catch(e => {
            console.log(e);
            setUserDeleteError(true);
        })
        setDeleteDialogOpen(false);
    }

    const handleDeleteDialogClose = () => {
        setDeleteDialogOpen(false);
    }

    const handleVisible = (id) => {
        setViewModalOpen(true);
        getUserById(id).then(response => {
            setViewUserData(response.data);
        }).catch(e => {
            console.log(e);
        })
    }

    const handleViewModalClose = () => {
        setViewModalOpen(false);
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
            {createConfirmAlert(deleteDialogOpen, deleteDialogText, handleDeleteDialogClose, handleDeleteAfterConfirm)}
            {userDeleteSuccess && <div>  {createSuccessAlert(deleteSuccessDialogText)} <br/><br/></div>}
            {userDeleteError && <div>  {createErrorAlert(deleteErrorDialogText)} <br/><br/></div>}
            <Container maxWidth="xl">
                <div>
                    <ViewUserModal open={viewModalOpen}
                                      userData={viewUserData}
                                      categoryData={viewUserData.category}
                                      handleClose={handleViewModalClose}/>

                </div>
                <div>
                    <h2>Kullanıcılar</h2>
                </div>
                <CustomTable rows={users}
                             columns={columns}
                             handlePageChange={handlePageChange}
                             handleRowsPerPageChange={handleRowsPerPageChange}
                             isOperation={true}
                             previewImageUrlField={"previewImageUrl"}
                             activePage={page}
                             count={count}
                             handleDelete={handleDelete}
                             handleVisible={handleVisible}
                             hiddenIndexes={[2,3,7,8,9,10,11,12,13,14,15,16,17,18]}
                />
            </Container>
        </React.Fragment>
    )

}

export default User;