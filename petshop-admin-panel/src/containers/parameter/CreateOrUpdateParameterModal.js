import {makeStyles} from '@material-ui/core/styles';
import React, {useEffect, useState} from "react";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from '@material-ui/core/DialogTitle'
import DialogContent from '@material-ui/core/DialogContent'
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import Autocomplete from '@material-ui/lab/Autocomplete';
import {getParameterById,createParameter,updateParameter} from './ParameterService';
const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
    },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
}));

export default function CreateOrUpdateParameterModal(props){

    const [value, setValue] = useState("");
    const [key, setKey] = useState("");
    const [parameterTypeId,setParameterTypeId] = useState(0);

    const paramterTypes = [{title:'Dosya',id:1},{title:'Text',id:2}]

    const {isCreate,open, handleClose,parameterId} = props;

    // useEffect(() => {
    //     if (open && isCreate) {
    //         setValue("");
    //         setKey("");
    //     }
    //     if((open && !isCreate)){
    //         getParameterById(parameterId).then(resp => {
    //             setValue(resp.data.value);
    //             setKey(resp.data.key);
    //         })
    //     }
    
    // }, [open]);

    const handleValueChange = (e)=> {
        const value = e.target.value
        setValue(value);
    }

    const handleKeyChange = (e)=> {
        const key = e.target.value
        setKey(key);
    }

    const handleCreateOrUpdate  = ()=> {
        if(isCreate) {
            createParameter(key,value).then(resp =>{
                handleClose();
            })
        }else{
            updateParameter(parameterId,key,value).then(resp=>{
                handleClose();
            })
        }
        
    }
    const onTagChange = (e,value) => {
        if(value){
            console.log(value.id)
        setParameterTypeId(value.id);
        }
        
    }

    const renderParameterInput = () => {
        if(parameterTypeId == 1 ){
            return (<p>Parameter 1</p>)
        }
        return (<p>Parameter 2</p>)
        
    }

    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
          
            <DialogTitle>{isCreate ? "Parametere Ekle" : "Parametre Güncelle"}</DialogTitle>
            <DialogContent>
                
                <TextField
                    autoFocus
                    margin="dense"
                    value={key}
                    onChange={handleKeyChange}
                    label="Key"
                    fullWidth
                />
        <br/>
        <Autocomplete
          options={paramterTypes}
          getOptionLabel={(option) => option.title}
          defaultValue= {paramterTypes[0]}
          style={{ width: 300 }}
          onChange={onTagChange}
          renderInput={(params) => <TextField {...params} label="Tip" variant="outlined" required />}
         />

         
           
            {renderParameterInput()}
            
         
              

            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    İptal
                </Button>
                < Button onClick={handleCreateOrUpdate} color="primary">
                   {isCreate ? "Ekle" : "Güncelle"}
                </Button>
                
            </DialogActions>
        </Dialog>
    );



}
