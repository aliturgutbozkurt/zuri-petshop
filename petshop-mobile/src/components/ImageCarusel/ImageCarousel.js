import React, {useState, useCallback} from 'react';
import {
    View,
    Image,
    FlatList,
    StyleSheet,
    useWindowDimensions,
} from 'react-native';

const ImageCarousel = ({images}) => {
    const [activeIndex, setActiveIndex] = useState(0);
    const windowWidth = useWindowDimensions().width;

    const onFlatlistUpdate = useCallback(({viewableItems}) => {
        if (viewableItems.length > 0) {
            setActiveIndex(viewableItems[0].index || 0);
        }
    }, []);

    return (

        <View style={styles.root}>
            <FlatList
                data={images}
                renderItem={({item,index}) => (
                    <Image
                        style={[styles.image, {width: windowWidth - 40}]}
                        source={{uri: item}}
                    />
                )}
                horizontal
                showsHorizontalScrollIndicator={false}
                snapToInterval={windowWidth - 20}
                snapToAlignment={'center'}
                decelerationRate={'fast'}
                viewabilityConfig={{
                    viewAreaCoveragePercentThreshold: 50,
                }}
                onViewableItemsChanged={onFlatlistUpdate}
                keyExtractor={(item, index) => index.toString()}
            />

            <View style={styles.dots}>
                {images.map((image, index) => (
                    <View
                        key={index}
                        style={[
                            styles.dot,
                            {
                                backgroundColor: index === activeIndex ? '#c9c9c9' : '#ededed',
                            },
                        ]}
                    />
                ))}
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    root: {},
    image: {
        margin: 10,
        height: 250,
        resizeMode: 'contain',
    },
    dots: {
        flexDirection: 'row',
        justifyContent: 'center',
    },
    dot: {
        width: 10,
        height: 10,
        borderRadius: 25,
        borderWidth: 1,
        backgroundColor: '#ededed',
        borderColor: '#c9c9c9',
        margin: 5,
    },
});

export default ImageCarousel;