import './App.css';
import SignIn from "./containers/signIn/SignIn";
import React from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

import http from "./common/axios";
import {requestLogout} from "./actions/loginActions";
import {history} from "./common/history";
import Home from "./containers/home/Home";
import PrivateRoute from "./hoc/PrivateRoute";
import DefaultLayout from "./layouts/DefaultLayout";
import {Redirect} from "react-router";
import ProductCategory from "./containers/product/catgeory/ProductCategory";
import Product from "./containers/product/product/Product";
import User from "./containers/user/User";

import QuestionList  from"./containers/question/QuestionList";
import ParameterList from "./containers/parameter/ParameterList"

function App(props) {
    return (

        <div>
            <Router history={history}>
                <Switch>
                    <PrivateRoute exact path="/" layout={DefaultLayout} component={Home}
                                  loggedIn={props.loggedIn}/>
                    <PrivateRoute exact path="/product-category" layout={DefaultLayout}
                                  component={ProductCategory}
                                  loggedIn={props.loggedIn}/>
                    <PrivateRoute exact path="/product" layout={DefaultLayout}
                                  component={Product}
                                  loggedIn={props.loggedIn}/>
                    <PrivateRoute exact path="/user" layout={DefaultLayout}
                                  component={User} />

                    <PrivateRoute exact path="/questions" layout={DefaultLayout}
                                  component={QuestionList}
                                  loggedIn={props.loggedIn}/>
                    <PrivateRoute exact path="/parameters" layout={DefaultLayout}
                                  component={ParameterList}
                                  loggedIn={props.loggedIn}/>
                    <Route
                        exact
                        path="/login"
                        render={() =>
                            props.loggedIn ?
                                <Redirect
                                    to={{
                                        pathname: "/",
                                        state: {
                                            from: props.location,
                                            componentData: {
                                                title: "Ana Sayfa",
                                                route: "main-page",
                                                id: "main-page",
                                                type: "page"
                                            }
                                        }
                                    }}
                                />
                                : (
                                    <SignIn/>
                                )
                        }
                    />
                    <Route
                        exact
                        path="/logout"
                        render={() =>
                            props.loggedIn ?
                                props.requestLogout() && <Redirect
                                    to={{
                                        pathname: "/",
                                        state: {
                                            from: props.location,
                                            componentData: {
                                                title: "Ana Sayfa",
                                                route: "main-page",
                                                id: "main-page",
                                                type: "page"
                                            }
                                        }
                                    }}
                                />
                                : (
                                    <Redirect
                                        to={{
                                            pathname: "/",
                                            state: {
                                                from: props.location,
                                                componentData: {
                                                    title: "Ana Sayfa",
                                                    route: "main-page",
                                                    id: "main-page",
                                                    type: "page"
                                                }
                                            }
                                        }}
                                    />
                                )
                        }
                    />
                </Switch>
            </Router>
        </div>

    );
}

const mapStateToProps = state => {
    return {
        error: state.loginReducer.error,
        loginResponse: state.loginReducer.login_info,
        loggedIn: state.loginReducer.loggedIn
    };
};

const mapDispatchToProps = dispatch => {
    return bindActionCreators({requestLogout}, dispatch);
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(App, http);
