import React from 'react';
import {View, Text, StyleSheet, useWindowDimensions} from 'react-native';
import {Dimensions} from 'react-native';

// const {width, height} = Dimensions.get('window');

export const DimensionScreen = () => {
  const {width, height} = useWindowDimensions();
  return (
    <View>
      <View style={styles.container}>
        <View
          style={{
            ...styles.purpleBox,
            width: width * 0.6,
          }}
        />
      </View>
      <Text style={styles.title}>
        W: {width}, H: {height}
      </Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    width: 300,
    height: 200,
    backgroundColor: 'red',
  },
  purpleBox: {
    height: '50%',
    width: '50%',
    backgroundColor: '#5856D6',
  },
  title: {
    fontSize: 30,
    textAlign: 'center',
  },
});
