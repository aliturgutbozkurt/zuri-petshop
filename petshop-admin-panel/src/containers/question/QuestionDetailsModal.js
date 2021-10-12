import { render } from "@testing-library/react";
import React, {useState,useEffect} from "react"
import  {getQuestionById} from "./QuestionService"
import {getAnswersByQuestionId} from "./AnswerService"
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from '@material-ui/core/DialogTitle'
import Button from "@material-ui/core/Button";
import { Divider, Avatar, Grid, Paper } from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";

import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';




function QuestionDetailsModal(props){
 

    const {open,handleClose,questionId} = props;
    const {question,setQuestion} = useState();
    const {answers,setAnswers}  = useState();
    useEffect(() => {
        getQuestionById(questionId).then(response => {
           setQuestion(response.data)
        }).catch(e => {
            console.log(e);
        });
    }, [questionId]);

    useEffect(() => {
      getAnswersByQuestionId(questionId).then(response => {
         setAnswers(response.data)
      }).catch(e => {
          console.log(e);
      });
  }, [questionId]);

    return(
        <Dialog open={open} onClose={handleClose}   aria-labelledby="form-dialog-title">
            <DialogTitle style={{ textAlign:'right', marginBottom:'-30px'}}>
            <IconButton  aria-label="close" onClick={handleClose}>
                     <CloseIcon/>
                </IconButton>
            </DialogTitle>
        <DialogContent>
          <Paper style={{ padding: "40px 20px" }}>
        <Grid container wrap="nowrap" spacing={2}>
          <Grid item>
            <Avatar alt="Remy Sharp" src={"https://images.pexels.com/photos/1681010/pexels-photo-1681010.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"} />
          </Grid>
          <Grid justifyContent="left" item xs zeroMinWidth>
            <h4 style={{ margin: 0, textAlign: "left" }}>{question.name}</h4>
            <p style={{ textAlign: "left" }}>
             {dummyData.explanation}{" "}
            </p>
            <p style={{ textAlign: "left", color: "gray" }}>
              posted 1 minute ago
            </p>
          </Grid>
        </Grid>
        <Divider variant="fullWidth" style={{ margin: "30px 0" }} />
        {
          answers.map((index,answer)=>{
            <Grid container  wrap="nowrap" spacing={2}>
          <Grid item>
            <Avatar alt="Remy Sharp" src={"https://images.pexels.com/photos/1681010/pexels-photo-1681010.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"} />
          </Grid>
          <Grid justifyContent="left" item xs zeroMinWidth>
            <h4 style={{ margin: 0, textAlign: "left" }}>{answer.user.UserName}</h4>
            <p style={{ textAlign: "left" }}>
             {answer.explanation}{" "}
            </p>
            <p style={{ textAlign: "left", color: "gray" }}>
              posted 1 minute ago
            </p>
          </Grid>
        </Grid>
          })

        }
        
      </Paper>
      </DialogContent>
        </Dialog>
    )
}
export default QuestionDetailsModal;