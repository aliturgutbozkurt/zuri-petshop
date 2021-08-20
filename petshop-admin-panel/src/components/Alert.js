import Alert from '@material-ui/lab/Alert';


export const createSuccessAlert = (message) => {
    return (
        <Alert variant="outlined" severity="success">
            {message}
        </Alert>
    );
}

export const createErrorAlert = (message) => {
    return (
        <Alert variant="outlined" severity="error">
            {message}
        </Alert>
    );
}