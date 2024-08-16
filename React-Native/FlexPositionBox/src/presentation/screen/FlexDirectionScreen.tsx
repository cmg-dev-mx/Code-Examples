import React from 'react';
import {View, StyleSheet} from 'react-native';

export const FlexDirectionScreen = () => {
  return (
    <View style={styles.container}>
      <View style={[styles.box, styles.box1]} />
      <View style={[styles.box, styles.box2]} />
      <View style={[styles.box, styles.box3]} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'lightgray',
    justifyContent: 'space-between',
    alignItems: 'center',
    flexDirection: 'row',
  },
  box: {
    width: 100,
    height: 100,
  },
  box1: {
    backgroundColor: '#b1af13',
    top: -100,
  },
  box2: {
    backgroundColor: '#84820c',
  },
  box3: {
    backgroundColor: '#626009',
    top: 100,
  },
});
