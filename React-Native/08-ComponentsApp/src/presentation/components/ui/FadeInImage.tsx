import {useContext, useState} from 'react';
import {ActivityIndicator, Animated} from 'react-native';
import {ImageStyle, StyleProp, View} from 'react-native';
import {useAnimation} from '../../hooks/useAnimation';
import {ThemeContext} from '../../context/ThemeContext';

interface Props {
  uri: string;
  style?: StyleProp<ImageStyle>;
}

export const FadeInImage = ({uri, style}: Props) => {
  const {colors} = useContext(ThemeContext);

  const {animatedOpacity, fadeIn} = useAnimation();
  const [isLoading, setIsLoading] = useState(true);

  return (
    <View
      style={{
        justifyContent: 'center',
        alignItems: 'center',
      }}>
      {isLoading && (
        <ActivityIndicator
          style={{position: 'absolute'}}
          size={30}
          color={colors.primary}
        />
      )}

      <Animated.Image
        source={{uri}}
        onLoadEnd={() => {
          fadeIn({});
          setIsLoading(false);
        }}
        style={[style, {opacity: animatedOpacity}]}
      />
    </View>
  );
};
