import React, {useEffect} from 'react'
import Background from '../components/Background'
import Logo from '../components/Logo'
import Button from '../components/Button'
import Paragraph from '../components/Paragraph'
import {useDispatch, useSelector} from 'react-redux'
import {getAccessToken, getRefreshToken} from "../common/asyncStorageService";
import {initToken} from "../store/login";

export default function StartScreen({navigation}) {

    const dispatch = useDispatch();
    const {login, loading} = useSelector((state) => state.entities)

    useEffect(() => {

        getAccessToken().then(token => {
            if (token) {
                getRefreshToken().then(refreshToken => {
                    if (refreshToken) {
                        dispatch(
                            {
                                type: initToken.type, payload: {data: {token: token, refreshToken: refreshToken}}
                            })
                    }
                })
            }
        })

    }, [])

    useEffect(() => {
        if (login.loggedIn) {
            navigation.reset({
                index: 0,
                routes: [{name: 'Dashboard'}],
            })
        }
    }, [login.loggedIn])


    return (
        <Background>
            <Logo/>
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
