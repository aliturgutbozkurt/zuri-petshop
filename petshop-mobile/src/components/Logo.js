import React from 'react'
import {Dimensions, Image, StyleSheet} from 'react-native'

export default function Logo() {
  return <Image source={require('../../assets/logo.png')} style={styles.image} />
}

const styles = StyleSheet.create({
  image: {
    width: Dimensions.get('window').width * 0.8,
    height: Dimensions.get('window').height * 0.15,
    resizeMode: 'contain',
  },
})
