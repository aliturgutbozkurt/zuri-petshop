import React, {useEffect} from 'react'
import Background from '../components/Background'
import Logo from '../components/Logo'
import Header from '../components/Header'
import Button from '../components/Button'
import Paragraph from '../components/Paragraph'
import {useDispatch, useSelector} from 'react-redux'
import entities from "../store/entities";

export default function StartScreen({ navigation }) {


    return (
    <Background>
      <Logo />
      <Paragraph>
        Evcil hayvanlarınızın ihtiyaçlarına ulaşmanın en kolay yolu.
      </Paragraph>
      <Button
        mode="contained"
        onPress={() => navigation.navigate('LoginScreen')}
      >
        Giriş
      </Button>
      <Button
        mode="outlined"
        onPress={() => navigation.navigate('RegisterScreen')}
      >
        Kaydol
      </Button>
    </Background>
  )
}
