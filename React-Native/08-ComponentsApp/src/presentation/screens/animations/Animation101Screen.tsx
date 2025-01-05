import {Text, StyleSheet, Pressable, Animated} from 'react-native';
import {useAnimation} from '../../hooks/useAnimation';
import {Easing} from 'react-native';
import {useContext} from 'react';
import {colors} from '../../../config/theme/theme';
import {ThemeContext} from '../../context/ThemeContext';
import {CustomView} from '../../components/ui/CustomView';
import {Button} from '../../components/ui/Button';

export const Animation101Screen = () => {
  const {colors} = useContext(ThemeContext);
  const {animatedOpacity, animatedTop, fadeIn, fadeOut, startMovinTopPosition} =
    useAnimation();

  return (
    <CustomView style={styles.container}>
      <Animated.View
        style={[
          styles.purpleBox,
          {
            backgroundColor: colors.primary,
          },
          {
            opacity: animatedOpacity,
            transform: [{translateY: animatedTop}],
          },
        ]}
      />

      <Button
        onPress={() => {
          fadeIn({});
          startMovinTopPosition({
            initialPosition: -100,
            easing: Easing.elastic(1),
            duration: 750,
          });
        }}
        style={{marginTop: 10}}
        text="FadeIn"
      />
      <Button
        onPress={() => fadeOut({})}
        style={{marginTop: 10}}
        text="FadeOut"
      />
    </CustomView>
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
