import React, {useRef} from 'react';
import {
  View,
  Text,
  StyleSheet,
  Pressable,
  Animated,
  Easing,
} from 'react-native';
import {colors} from '../../../config/theme/theme';

export const Animation101Screen = () => {
  const animatedOpacity = useRef(new Animated.Value(0)).current;
  const animatedTop = useRef(new Animated.Value(-100)).current;

  const fadeIn = () => {
    Animated.timing(animatedTop, {
      toValue: 0,
      duration: 300,
      useNativeDriver: true,
      easing: Easing.bounce, //Easing.elastic(2),
    }).start(() => console.log('Animation finished'));

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
    }).start(() => animatedTop.resetAnimation());
  };

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.purpleBox,
          {
            opacity: animatedOpacity,
            transform: [{translateY: animatedTop}],
          },
        ]}
      />

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
