import {getAllParameters,updateParameter,updateImage} from './ParameterService';
import {getParameterById} from './ParameterService';
import React, {useEffect, useState} from "react";
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Grid from '@material-ui/core/Grid';
import { esES } from '@material-ui/data-grid';
import Divider from '@material-ui/core/Divider';

export default function ParameterList(){

    const [parameters,setParameters] = useState([]);  
    const [paramterId,setParameterId] = useState(0);
    const [type,setType] = useState(0);
    const [updateVisible,SetUpdateVisible] = useState(false);
    const [parameter,setParameter] = useState({});
    const testParameter = [{}]

    useEffect(() => {
        getAllParameters().then(response => {
          console.log(response)
            setParameters(response.data);
        }).catch(e => {
          console.log(e);
        });
      }, []);

      const onTagChange = (e,value) => {
        if(value){
          setParameter(value)
          console.log(value)
        }  
      }

      const onChangeText = (e,value) => {
        SetUpdateVisible(true)
        const newParameter = {...parameter}
        newParameter["parameterValue"] = e.target.value;
        setParameter(newParameter);
      }

      // useEffect(() => {
      //   setType(1);
      //   // getParameterById(paramterId).then(response => {
      //   //     setParameter(response.data);
      //   //     if(parameter.type === 1){
      //   //       setType(1);
      //   //     }
      //   //     if(parameter.type === 2){
      //   //       setType(2);
      //   //     }
      //   //     else{
      //   //       setType(1);
      //   //     }
      //   // }).catch(e => {
      //   //   console.log(e);
      //   // });
      //   console.log(type)
      // }, [paramterId]);

      const onGetParameter = (e,value ) => {
        setType(parameter.parameterType);
        console.log("param",parameter)
      }
      const updateParameterOnClick = ()=> {
        console.log("newParamter",parameter);
        updateParameter(parameter.id,parameter.parameterKey,parameter.parameterValue,parameter.parameterType).then(resp => {
         console.log("newParamter",parameter);
          getAllParameters().then(response => {
            setParameters(response.data);
        }).catch(e => {
          console.log(e);
        });
        })
      }

      const handlePhotoUrlChange = (e) => {
        e.preventDefault();

        updateImage(e.target.files[0]).then(response => {
          const newParameter = {...parameter}
          newParameter["parameterValue"] = response.data
          setParameter(newParameter);  
        }).catch(e => {
            
        })
    }

    const updateParameterForImage = ()=> {
      console.log("newParamter",parameter);
      updateParameter(parameter.id,parameter.parameterKey,parameter.parameterValue,parameter.parameterType).then(resp => {
       console.log("newParamter",parameter);
        getAllParameters().then(response => {
          setParameters(response.data);
      }).catch(e => {
        console.log(e);
      });
      })
    }

      return(
        <>
    <div className="flex">
      <Grid container spacing={3}>
          <Grid item xs={8}>
          <Autocomplete
            options={parameters}
            disableClearable={true}
            getOptionLabel={(option) => option.parameterKey}
            defaultValue= {testParameter[0]}
            style={{ width: 800 }}
            onChange={onTagChange}
            renderInput={(params) => <TextField {...params} label="Parametre" variant="outlined" required />}
           />
        </Grid>
        <Grid item justifyContent="center"  xs={4}>
          <Button onClick={()=>{onGetParameter()}} variant="contained"  color="primary"  >
            Getir
          </Button>
        </Grid>
      </Grid> 
       </div>
       <Divider/>
       <div  className="flex" style={{marginTop: 20}}>
       <Grid container spacing={3}>
        {type === "1" && 
        <Grid item justifyContent="space-evenly">
          <TextField InputProps={{ readOnly: true}} value = {parameter.parameterKey}  variant="outlined" ></TextField>
          <TextField value = {parameter.parameterValue} variant="outlined" onChange = {onChangeText}/>
          <Button onClick = {()=>{updateParameterOnClick()}} variant="contained"  color="primary" size="large" disabled ={!updateVisible} >
            Güncelle
          </Button>
        </Grid> 
        }
        {
          type === "2" &&
          <Grid item justifyContent="space-evenly">
            <TextField InputProps={{ readOnly: true}} value = {parameter.parameterKey}  variant="outlined" ></TextField>
            <div><img src={parameter.parameterValue}/></div>
            <p>Değiştirmek İçin Tıklayınız</p>
            <input type="file"
                           id="photoUrl" name="photoUrl"
                           accept="image/png, image/jpeg"
                           onChange={handlePhotoUrlChange}
                    />

          <Button onClick = {()=>{updateParameterForImage()}} variant="contained"  color="primary" size="large" disabled ={!updateVisible} >
            Güncelle
          </Button>        
         </Grid> 

        }
  
      </Grid>
      </div>
        

        </>


      );

}