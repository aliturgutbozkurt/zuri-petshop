import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { Route, withRouter } from "react-router-dom";
import React from "react";
import Redirect from "react-router-dom/Redirect";

const PrivateRoute = ({ layout: Layout, component: Component, loggedIn, history, ...rest }) => {

    return (
        <Route
            {...rest}
            render={props =>
                loggedIn ? (
                    <Layout component={Component} {...props} />
                ) : (

                   <Redirect
                        to={{
                            pathname: "/login",
                            state: {from: props.location}
                        }}
                    />
                )
            }
        ></Route>
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
)(withRouter(PrivateRoute));
