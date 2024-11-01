import {StyleProp, Text, View, ViewStyle} from 'react-native';
import {globalStyles, colors} from '../../../config/theme/theme';
import {ReactNode, useContext} from 'react';
import {ThemeContext} from '../../context/ThemeContext';

interface Props {
  style?: StyleProp<ViewStyle>;
  children?: ReactNode;
  margin?: boolean;
}

export const CustomView = ({style, children, margin = false}: Props) => {
  const {colors} = useContext(ThemeContext);

  return (
    <View
      style={[
        margin ? globalStyles.globalMargin : null,
        globalStyles.mainContainer,
        {backgroundColor: colors.background},
        style,
      ]}>
      {children}
    </View>
  );
};
