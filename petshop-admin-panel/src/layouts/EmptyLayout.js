import React, { Fragment } from "react";
import Header from "../components/Header";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { withTranslation } from "react-i18next";

const EmptyLayout = ({ component: Component, ...rest }) => {
  return (
    <Fragment>
      <Component {...rest} />
    </Fragment>
  );
};

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
)(withRouter(withTranslation()(EmptyLayout)));
