import React, {Fragment, useEffect} from "react";
import { usePromiseTracker } from "react-promise-tracker";

export const Spinner = props => {
  const { promiseInProgress } = usePromiseTracker();

  useEffect(()=>{

    if(promiseInProgress){
       document.body.classList.add("loading-indicator");
    }else{
      document.body.classList.remove("loading-indicator");

    }

  },[promiseInProgress]);

  return (
    <Fragment/>
  );
};
