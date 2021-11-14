import React, {useEffect, useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {bindActionCreators} from "redux";
import {DoLogin, requestLogin} from "../../actions/loginActions.js";
import { connect } from "react-redux";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="petshop.com">
                petshop.com
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

function SignIn(props) {
    const classes = useStyles();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [isLoginButtonDisabled,setIsLoginButtonDisabled]=useState(true);

    useEffect(() => {
        if(props.loggedIn){
            props.history.push("/user-profile");
        }
    }, [props.loggedIn]);

    useEffect(() => {
        if (props.error)
            console.log("Hata", "Geçersiz kullanıcı adı veya şifre girdiniz.");
    }, [props.error]);

    const handleEmailChange = e => {
        const value=e.target.value;
        value === "" || password==="" ? setIsLoginButtonDisabled(true) : setIsLoginButtonDisabled(false);
        setEmail(value);
    };

    const handlePasswordChange = e => {
        const value=e.target.value;
        value === "" || email==="" ? setIsLoginButtonDisabled(true) : setIsLoginButtonDisabled(false);
        setPassword(value);
    };

    const handleSubmitLogin = () => {
        props.requestLogin(email, password);
    };

    const handleKeyDown = (event) => {
        if(event.keyCode === 13) {
            handleSubmitLogin();
        }
    };

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Giriş
                </Typography>
                <div className={classes.form} noValidate>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="email"
                        label="Email Adresi"
                        name="email"
                        autoComplete="email"
                        autoFocus
                        onChange={handleEmailChange}
                        onKeyDown={handleKeyDown}

                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Parola"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        onChange={handlePasswordChange}
                        onKeyDown={handleKeyDown}
                    />
                    <FormControlLabel
                        control={<Checkbox value="remember" color="primary" />}
                        label="Beni hatırla"
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={handleSubmitLogin}
                    >
                        GİRİŞ
                    </Button>
                </div>
            </div>
            <Box mt={8}>
                <Copyright />
            </Box>
        </Container>
    );
}

const mapStateToProps = state => {
    return {
        error: state.loginReducer.error,
        loggedIn: state.loginReducer.loggedIn
    };
};

const mapDispatchToProps = dispatch => {
    return bindActionCreators({ DoLogin, requestLogin }, dispatch);
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SignIn);