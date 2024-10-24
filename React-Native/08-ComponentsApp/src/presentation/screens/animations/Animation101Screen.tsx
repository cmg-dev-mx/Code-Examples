import React, {useRef} from 'react';
import {View, Text, StyleSheet, Pressable, Animated} from 'react-native';
import {colors} from '../../../config/theme/theme';

export const Animation101Screen = () => {
  const animatedOpacity = useRef(new Animated.Value(0)).current;

  const fadeIn = () => {
    Animated.timing(animatedOpacity, {
      toValue: 1,
      duration: 300,
      useNativeDriver: true,
    }).start(() => console.log('Animation finished'));
  };

  const fadeOut = () => {
    Animated.timing(animatedOpacity, {
      toValue: 0,
      duration: 300,
      useNativeDriver: true,
    }).start(() => console.log('Animation finished'));
  };

  return (
    <View style={styles.container}>
      <Animated.View style={[styles.purpleBox, {opacity: animatedOpacity}]} />

      <Pressable onPress={fadeIn} style={{marginTop: 10}}>
        <Text>FadeIn</Text>
      </Pressable>
      <Pressable onPress={fadeOut} style={{marginTop: 10}}>
        <Text>FadeOut</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    flex: 1,
    justifyContent: 'center',
  },

  purpleBox: {
    backgroundColor: colors.primary,
    height: 150,
    width: 150,
  },
});
