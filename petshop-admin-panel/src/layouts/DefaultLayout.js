import React, {Fragment} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {makeStyles, useTheme} from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemLink from './ListItemLink';
import MailIcon from '@material-ui/icons/Mail';
import PetsIcon from '@material-ui/icons/Pets';
import CategoryIcon from '@material-ui/icons/Category';
import ArtTrackIcon from '@material-ui/icons/ArtTrack';
import AssignmentIcon from '@material-ui/icons/Assignment';
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import Hidden from "@material-ui/core/Hidden";
import Drawer from "@material-ui/core/Drawer";
import MenuIcon from '@material-ui/icons/Menu';
import Link from "@material-ui/core/Link";
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    drawer: {
        [theme.breakpoints.up('sm')]: {
            width: drawerWidth,
            flexShrink: 0,
        },
    },
    appBar: {
        [theme.breakpoints.up('sm')]: {
            width: `calc(100% - ${drawerWidth}px)`,
            marginLeft: drawerWidth,
        },
    },
    menuButton: {
        marginRight: theme.spacing(2),
        [theme.breakpoints.up('sm')]: {
            display: 'none',
        },
    },
    // necessary for content to be below app bar
    toolbar: theme.mixins.toolbar,
    drawerPaper: {
        width: drawerWidth,
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(3),
    },
}));

const DefaultLayout = ({window: window, component: Component, ...rest}) => {

    const classes = useStyles();
    const theme = useTheme();
    const [mobileOpen, setMobileOpen] = React.useState(false);

    const handleDrawerToggle = () => {
        setMobileOpen(!mobileOpen);
    };

    const CustomLink = props => <Link to={"product-category"} {...props} />;


    const drawer = (
        <div>
            <div className={classes.toolbar}/>
            <Divider/>
            <List>
                <ListItem>
                    <ListItemText>Ürün Yönetimi</ListItemText>
                </ListItem>
                <ListItemLink to="/product-category" primary="Kategoriler" icon={<CategoryIcon/>}/>
                <ListItemLink to="/product" primary="Ürünler" icon={<ArtTrackIcon/>}/>
                <ListItem button key={3}>
                    <ListItemIcon><AssignmentIcon/></ListItemIcon>
                    <ListItemText primary={'Siparişler'}/>
                </ListItem>
            </List>
            <Divider/>
            <List>
                <ListItem>
                    <ListItemText>Sosyal Medya</ListItemText>
                </ListItem>
                <ListItem button key={1}>
                    <ListItemIcon><PetsIcon/></ListItemIcon>
                    <ListItemText primary={'Hayvanlar'}/>
                </ListItem>
                <ListItem button key={2}>
                    <ListItemIcon><MailIcon/></ListItemIcon>
                    <ListItemLink  to="/questions" primary={'Gelen Sorular'}/>
                </ListItem>
            </List>
            <Divider/>
            <List>
                <ListItem>
                    <ListItemText>Kullanıcı Yönetimi</ListItemText>
                </ListItem>
                <ListItem button key={1}>
                    <ListItemIcon><AccountBoxIcon/></ListItemIcon>
                    <ListItemText primary={'Kullanıcılar'}/>
                </ListItem>
            </List>
            <Divider/>
            <List>
                <ListItemLink to="/logout" primary="Çıkış" icon={<ExitToAppIcon/>}/>
            </List>
        </div>
    );

    const container = window !== undefined ? () => window().document.body : undefined;

    return (
        <Fragment>

            <main>
                <div className={classes.root}>
                    <CssBaseline/>
                    <AppBar position="fixed" className={classes.appBar}>
                        <Toolbar>
                            <IconButton
                                color="inherit"
                                aria-label="open drawer"
                                edge="start"
                                onClick={handleDrawerToggle}
                                className={classes.menuButton}
                            >
                                <MenuIcon/>
                            </IconButton>
                            <h4>Zuri PetShop Admin Panel</h4>
                        </Toolbar>
                    </AppBar>
                    <nav className={classes.drawer} aria-label="mailbox folders">
                        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
                        <Hidden smUp implementation="css">
                            <Drawer
                                container={container}
                                variant="temporary"
                                anchor={theme.direction === 'rtl' ? 'right' : 'left'}
                                open={mobileOpen}
                                onClose={handleDrawerToggle}
                                classes={{
                                    paper: classes.drawerPaper,
                                }}
                                ModalProps={{
                                    keepMounted: true, // Better open performance on mobile.
                                }}
                            >
                                {drawer}
                            </Drawer>
                        </Hidden>
                        <Hidden xsDown implementation="css">
                            <Drawer
                                classes={{
                                    paper: classes.drawerPaper,
                                }}
                                variant="permanent"
                                open
                            >
                                {drawer}
                            </Drawer>
                        </Hidden>
                    </nav>
                    <main className={classes.content}>
                        <div className={classes.toolbar}/>
                        <Component {...rest}/>
                    </main>
                </div>

            </main>
        </Fragment>
    );

}

const mapStateToProps = state => {

    return {
        loggedIn: state.loginReducer.loggedIn
    };
};

const mapDispatchToProps = dispatch => {
    return bindActionCreators({}, dispatch);
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(DefaultLayout));